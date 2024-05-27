package com.labs.lab4.model.enums;

public enum Status {

    NEW("Новый"),
    RENTED("В прокате"),
    CLOSED("Закрыт");

    private final String id;

    Status(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static Status fromId(String id) {
        for (Status status : Status.values()) {
            if (status.getId().equals(id)) {
                return status;
            }
        }
        return null;
    }
}
