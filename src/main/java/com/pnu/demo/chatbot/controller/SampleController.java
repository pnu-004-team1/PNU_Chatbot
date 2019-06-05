package com.pnu.demo.chatbot.controller;

import com.pnu.demo.chatbot.bookInfo.BookInfoManager;
import com.pnu.demo.chatbot.service.ChatbotService;
import com.pnu.demo.chatbot.user.Member;
import com.pnu.demo.chatbot.user.MemberRepository;
import com.pnu.demo.chatbot.vo.JSONChatbotVO;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin(origins = "*")
@RestController
public class SampleController {

    @Autowired
    MemberRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;

    private ChatbotService service = new ChatbotService();

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/chatbot")
    public JSONChatbotVO chatbotmessage(@RequestParam String query) {
        System.out.println(query);
        JSONChatbotVO vo = new JSONChatbotVO();
        JSONObject data = new JSONObject();
        data.put("answer", service.getAnswer(query));
        return vo;
    }

    @GetMapping("/bookInfo")
    public JSONChatbotVO bookinfomessage(@RequestParam String query) {
        System.out.println(query);
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
        String name= member.getName();

        Member _member = new Member(username, password, name);
        repository.save(_member);

        return _member;
    }

    @PostMapping("/login")
    public Member loginMember(@RequestBody Member member) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String rawPassword = member.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println(encodedPassword);

//        Query query = new Query(new Criteria("username").is(member.getUsername())
//                        .and("password").is(encodedPassword));
//
//        Member _member = mongoTemplate.find(query,Member.class, "member").get(0);
//        System.out.println("login successed");
//        return _member;
        return null;
    }


}
