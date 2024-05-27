package com.labs.lab4.repository;

import com.labs.lab4.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserAuthRepository extends JpaRepository<UserAuth, UUID> {

}
