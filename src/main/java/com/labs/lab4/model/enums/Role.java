package com.labs.lab4.model.enums;

public enum Role {

    SELLER("Продавец"),
    SUPERVISOR("Старший смены"),
    ADMINISTRATOR("Администратор");

    private final String id;

    Role(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static Role fromId(String id) {
        for (Role role : Role.values()) {
            if (role.getId().equals(id)) {
                return role;
            }
        }
        return null;
    }
}
