package com.labs.lab4.model;

import com.labs.lab4.model.enums.Role;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity(name = "lab4$User")
@Table(name = "LAB4_USER")
public class User extends AbstractPersistable<Integer> {

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IMG_URL")
    private String imgUrl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role.getId();
    }

    public void setRole(String role) {
        this.role = Role.fromId(role);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
