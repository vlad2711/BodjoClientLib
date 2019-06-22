package com.bodjo.client_lib.pojo;

public class ConnectToServerModel {
    private String type;
    private String username;
    private String token;
    private String role;

    public ConnectToServerModel(String type, String username, String token, String role) {
        this.type = type;
        this.username = username;
        this.token = token;
        this.role = role;
    }

    public String getRole() {

        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
