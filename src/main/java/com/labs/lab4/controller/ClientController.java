package com.labs.lab4.controller;

import com.labs.lab4.dto.request.ClientRequest;
import com.labs.lab4.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody ClientRequest clientRequest) {
        return clientService.create(clientRequest);
    }

    @GetMapping("/")
    public ResponseEntity<?> find(@RequestBody ClientRequest clientRequest) {
        return clientService.find(clientRequest);
    }
}
