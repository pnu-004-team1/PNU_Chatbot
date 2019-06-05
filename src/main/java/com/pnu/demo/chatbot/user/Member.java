package com.pnu.demo.chatbot.user;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document(collection = "member")
public class Member implements UserDetails {

    private String username;
    private String password;
    private String name;

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    private List<GrantedAuthority> authorities;

//    private  boolean active;

    public Member() {

    }

    public Member(String username, String password, String name) {
        String authorities[] = {"USER"};

        this.username = username;
        this.password = password;
        this.name = name;
        this.authorities = AuthorityUtils.createAuthorityList(authorities);
    }

    public Member(String username, String password, String name, String[] authorities) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.authorities = AuthorityUtils.createAuthorityList(authorities);
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

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setAuthorities(String[] authorities) {
        List<GrantedAuthority> auth = AuthorityUtils.createAuthorityList(authorities);
        this.authorities = auth;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    //    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }

}
