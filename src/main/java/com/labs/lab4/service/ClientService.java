package com.labs.lab4.service;

import com.labs.lab4.dto.request.ClientRequest;
import com.labs.lab4.exception.IdNotFoundException;
import com.labs.lab4.model.Client;
import com.labs.lab4.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findById(Integer id) throws IdNotFoundException {
        return clientRepository.findById(id).orElseThrow(() -> new IdNotFoundException(
                String.format("Client with id %s not found", id)
        ));
    }

    public ResponseEntity<?> create(ClientRequest clientRequest) {
        Client client = new Client();
        client.setId(clientRequest.getId());
        client.setEmail(clientRequest.getEmail());
        client.setName(clientRequest.getName());
        client.setAddress(clientRequest.getAddress());
        client.setBirthDate(clientRequest.getBirthDate());
        client.setPassportDetails(client.getPassportDetails());
        client.setPhone(clientRequest.getPhone());
        clientRepository.save(client);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> find(ClientRequest clientRequest) {
        List<Client> clients;

        if (clientRequest.getId() != null)
            clients = clientRepository.findAllById(clientRequest.getId());
        else if (clientRequest.getEmail() != null)
            clients = clientRepository.findAllByEmail(clientRequest.getEmail());
        else if (clientRequest.getName() != null)
            clients = clientRepository.findAllByName(clientRequest.getName());
        else if (clientRequest.getAddress() != null)
            clients = clientRepository.findAllByAddress(clientRequest.getAddress());
        else if (clientRequest.getBirthDate() != null)
            clients = clientRepository.findAllByBirthDate(clientRequest.getBirthDate());
        else if (clientRequest.getPassportDetails() != null)
            clients = clientRepository.findAllByPassportDetails(clientRequest.getPassportDetails());
        else if (clientRequest.getPhone() != null)
            clients = clientRepository.findAllByPhone(clientRequest.getPhone());
        else
            clients = Collections.emptyList();

        return ResponseEntity.ok(clients);
    }
}
