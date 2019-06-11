package com.pnu.demo.chatbot.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = userMapper.readMember(username);
        member.setAuthorities(userMapper.readAuthority(username));

        return member;
    }

    @Override
    public Member readMember(String username) {
        Member member = userMapper.readMember(username);
        member.setAuthorities(userMapper.readAuthority(username));

        return member;
    }

    @Override
    public void createMember(Member member) {
        String rawPassword = member.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
        member.setPassword(encodedPassword);

        userMapper.createMember(member);
        userMapper.createAuthority(member);
    }

    @Override
    public void deleteMember(String username) {
        userMapper.deleteMember(username);
        userMapper.deleteAuthority(username);
    }


    @Override
    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
}
