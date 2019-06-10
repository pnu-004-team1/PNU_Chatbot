package com.pnu.demo.chatbot.user.Authentication;

import java.util.List;

public class AuthenticationToken {

    private String username;
    private List authorities;
    private String token;

    public AuthenticationToken(String username, List list, String token) {
        this.username = username;
        this.authorities = list;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List authorities) {
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
