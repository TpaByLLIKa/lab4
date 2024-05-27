package com.labs.lab4.dto.response;

public class AuthResponse {

    private String token;
    private String name;
    private String imgUrl;

    public AuthResponse(String token, String name, String imgUrl) {
        this.token = token;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
