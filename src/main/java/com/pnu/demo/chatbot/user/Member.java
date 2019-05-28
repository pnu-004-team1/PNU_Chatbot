package com.pnu.demo.chatbot.user;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "member")
public class Member {

    private String username;
    private String password;
    private String name;
    private  boolean active;

    public Member() {

    }

    public Member(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Member [username="+username+", password="+password+", name="+name+", active="+active+"]";
    }
}
