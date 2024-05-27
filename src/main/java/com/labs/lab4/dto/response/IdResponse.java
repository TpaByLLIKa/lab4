package com.labs.lab4.dto.response;

import java.math.BigInteger;

public class IdResponse {

    private BigInteger id;

    public IdResponse(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
