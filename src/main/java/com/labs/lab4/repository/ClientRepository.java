package com.labs.lab4.repository;

import com.labs.lab4.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findAllById(Integer id);
    List<Client> findAllByEmail(String email);
    List<Client> findAllByName(String name);
    List<Client> findAllByAddress(String address);
    List<Client> findAllByBirthDate(LocalDate birthDate);
    List<Client> findAllByPassportDetails(String passportDetails);
    List<Client> findAllByPhone(String phone);
}
