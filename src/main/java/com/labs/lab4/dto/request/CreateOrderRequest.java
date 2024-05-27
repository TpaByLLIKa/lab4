package com.labs.lab4.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;

public class CreateOrderRequest {

    @NotNull(message = "ID must not be null")
    private BigInteger id;
    @NotNull(message = "Duration must not be null")
    private Integer duration;
    public CreateOrderRequest(BigInteger id, Integer duration) {
        this.id = id;
        this.duration = duration;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
