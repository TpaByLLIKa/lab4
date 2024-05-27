package com.labs.lab4.dto.request;

import java.time.LocalDate;

public class ClientRequest {

    private Integer id;
    private String email;
    private String name;
    private String address;
    private LocalDate birthDate;
    private String passportDetails;
    private String phone;

    public ClientRequest(Integer id, String email, String name, String address, LocalDate birthDate, String passportDetails, String phone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.passportDetails = passportDetails;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassportDetails() {
        return passportDetails;
    }

    public void setPassportDetails(String passportDetails) {
        this.passportDetails = passportDetails;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
