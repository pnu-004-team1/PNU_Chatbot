package com.pnu.demo.chatbot.controller;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.pnu.demo.chatbot.bookInfo.BookInfoManager;
import com.pnu.demo.chatbot.nlp.ClassificationResult;
import com.pnu.demo.chatbot.service.ChatbotService;
import com.pnu.demo.chatbot.user.Authentication.AuthenticationRequest;
import com.pnu.demo.chatbot.user.Authentication.AuthenticationToken;
import com.pnu.demo.chatbot.user.Member;
import com.pnu.demo.chatbot.user.MemberRepository;
import com.pnu.demo.chatbot.user.UserService;
import com.pnu.demo.chatbot.vo.JSONChatbotDataVO;
import com.pnu.demo.chatbot.vo.JSONChatbotVO;
import net.minidev.json.JSONObject;
import org.bson.Document;
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
    public String hello(){
        return "hello";
    }

    @GetMapping("/chatbot")
    public JSONChatbotVO chatbotmessage(@RequestParam String query) {
        System.out.println(query);
        JSONChatbotVO vo = new JSONChatbotVO();
        JSONChatbotDataVO data = new JSONChatbotDataVO();
        ClassificationResult result;
        try {
            result = service.getAnswer(query);
            data.answer = result.answer;
            data.type = result.type;
        } catch (Exception e) {
            data = null;
            vo.message = e.getLocalizedMessage();
            vo.error = "Exception on SampleController.java";
            vo.status = 500;
        }
        vo.data= data;
        return vo;
    }

    @PostMapping("/join")
    public String joinMember(@RequestBody Member member) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String username = member.getUsername();
        String password = passwordEncoder.encode(member.getPassword());
        String name = member.getName();

        MongoDatabase database = mongoClient.getDatabase("chatbot");
        MongoCollection<Document> collection = database.getCollection("member");

        Document document = collection.find(Filters.eq("username", username)).first();

        if (document != null) {
            return "error";
        }
        else {
            Member _member = new Member(username, password, name);
            repository.save(_member);

            return "success";
        }
    }

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
}
