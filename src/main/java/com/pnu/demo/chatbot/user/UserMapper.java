package com.pnu.demo.chatbot.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface UserMapper {
    public Member readMember(String username);
    public List<GrantedAuthority> readAuthority(String username);
    public void createMember(Member member);
    public void createAuthority(Member member);
    public void deleteMember(String username);
    public void deleteAuthority(String username);
}
