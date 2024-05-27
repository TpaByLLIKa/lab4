package com.labs.lab4.service;

import com.labs.lab4.dto.response.AppError;
import com.labs.lab4.dto.request.SignInRequest;
import com.labs.lab4.dto.response.AuthResponse;
import com.labs.lab4.model.User;
import com.labs.lab4.model.UserAuth;
import com.labs.lab4.repository.UserAuthPagingRepository;
import com.labs.lab4.repository.UserAuthRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthService {

    private final UserService userService;
    private final UserAuthRepository userAuthRepository;
    private final UserAuthPagingRepository userAuthPagingRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserService userService, UserAuthRepository userAuthRepository, UserAuthPagingRepository userAuthPagingRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.userAuthRepository = userAuthRepository;
        this.userAuthPagingRepository = userAuthPagingRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public ResponseEntity<?> signIn(SignInRequest signInRequest) throws AuthenticationException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signInRequest.getUsername(),
                    signInRequest.getPassword())
            );

            User user = userService.findByUsername(signInRequest.getUsername());
            String token = jwtService.generateToken(user);

            addToHistory(signInRequest.getUsername(), StringUtils.hasText(token));

            return ResponseEntity.ok(new AuthResponse(token, user.getName(), user.getImgUrl()));

        } catch (Exception e) {
            addToHistory(signInRequest.getUsername(), false);

            return new ResponseEntity<>(
                    new AppError(e.getMessage()),
                    List.of(
                            BadCredentialsException.class,
                            UsernameNotFoundException.class
                    ).contains(e.getClass())
                            ? HttpStatus.BAD_REQUEST
                            : HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public void addToHistory(String username, boolean successful) {
        UserAuth userAuth = new UserAuth();
        userAuth.setUsername(username);
        userAuth.setSuccessful(successful);
        userAuth.setDateTime(LocalDateTime.now());

        userAuthRepository.save(userAuth);
    }

    public ResponseEntity<?> getHistoryPagesNum() {
        return ResponseEntity.ok((int) Math.ceil((double) userAuthRepository.count() / (double) 50));
    }

    public ResponseEntity<?> getHistoryPage(Integer page) {
        return ResponseEntity.ok(userAuthPagingRepository.findAll(PageRequest.of(page, 50, Sort.by("dateTime").descending())));
    }
}
