package com.pnu.demo.chatbot.controller;

import com.pnu.demo.chatbot.service.ChatbotService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class SampleController {

    private ChatbotService service = new ChatbotService();

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/chatbot")
    public String chatbotmessage(@RequestParam String query) {
        System.out.println(query);

        return service.getAnswer(query);
    }
}
