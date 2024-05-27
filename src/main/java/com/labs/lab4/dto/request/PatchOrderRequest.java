package com.labs.lab4.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

public class PatchOrderRequest {

    @NotEmpty(message = "Services must not be empty")
    private Collection<Integer> serviceIds;
    @NotNull(message = "Client id must not be null")
    private Integer clientId;

    public PatchOrderRequest(Collection<Integer> serviceIds, Integer clientId) {
        this.serviceIds = serviceIds;
        this.clientId = clientId;
    }

    public Collection<Integer> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(Collection<Integer> serviceIds) {
        this.serviceIds = serviceIds;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
