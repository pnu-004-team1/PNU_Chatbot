package com.pnu.demo.chatbot.controller;

import com.mongodb.MongoClient;
import com.pnu.demo.chatbot.bookInfo.BookInfoManager;
import com.pnu.demo.chatbot.service.ChatbotService;
import com.pnu.demo.chatbot.user.Authentication.AuthenticationRequest;
import com.pnu.demo.chatbot.user.Authentication.AuthenticationToken;
import com.pnu.demo.chatbot.user.Member;
import com.pnu.demo.chatbot.user.MemberRepository;
import com.pnu.demo.chatbot.user.UserService;
import com.pnu.demo.chatbot.vo.JSONChatbotVO;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "*")
@RestController
public class SampleController {

    @Autowired
    MemberRepository repository;

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    private ChatbotService service = new ChatbotService();

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/chatbot")
    public JSONChatbotVO chatbotmessage(@RequestParam String query) {
        //System.out.println(query);
        JSONChatbotVO vo = new JSONChatbotVO();
        JSONObject data = new JSONObject();
        data.put("answer", service.getAnswer(query));
        return vo;
    }

    @GetMapping("/bookInfo")
    public JSONChatbotVO bookinfomessage(@RequestParam String query) {
        //System.out.println(query);
        JSONObject data = new JSONObject();
        JSONChatbotVO vo = new JSONChatbotVO();
        BookInfoManager bookInfoManager = new BookInfoManager();
        data.put("type", "bookinfo");
        data.put("data", bookInfoManager.getStringResult(query));
        vo.data = data;
        return vo;
    }

    @PostMapping("/join")
    public Member joinMember(@RequestBody Member member) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String username = member.getUsername();
        String password = passwordEncoder.encode(member.getPassword());
        String name = member.getName();

        Member _member = new Member(username, password, name);
        repository.save(_member);

        return _member;
    }

//    @PostMapping("/login")
//    public String loginMember(@RequestBody AuthenticationRequest authenticationRequest
//            , HttpSession session) {
//
//        String username = authenticationRequest.getUsername();
//        String password = authenticationRequest.getPassword();
//
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//        Authentication authentication = authenticationManager.authenticate(token);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
//                SecurityContextHolder.getContext());
//
//        Member member = userService.readMember(username);
//        return session.getId();
//    }

    @PostMapping("/login")
    public AuthenticationToken loginMember(@RequestBody AuthenticationRequest authenticationRequest
                                            , HttpSession session) {

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        Member member = userService.readMember(username);
        return new AuthenticationToken(member.getUsername(), member.getAuthorities(), session.getId());
    }

    @GetMapping("/logout")
    public String logout() {
        return null;
    }
}

