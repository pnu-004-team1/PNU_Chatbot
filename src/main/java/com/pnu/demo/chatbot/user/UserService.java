package com.pnu.demo.chatbot.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

public interface UserService extends UserDetailsService {
    public Member readMember(String username);
    public void createMember(Member member);
    public void deleteMember(String username);
    public PasswordEncoder passwordEncoder();

}
