package com.labs.lab4.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "lab4$UserAuth")
@Table(name = "LAB4_USER_AUTH")
public class UserAuth extends AbstractPersistable<UUID> {

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "DATE_TIME")
    private LocalDateTime dateTime;

    @Column(name = "SUCCESSFUL")
    private Boolean successful;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }
}
