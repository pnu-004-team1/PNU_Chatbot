package com.pnu.demo.chatbot.controller;

import com.pnu.demo.chatbot.bookInfo.BookInfoManager;
import com.pnu.demo.chatbot.nlp.ClassificationResult;
import com.pnu.demo.chatbot.service.ChatbotService;
import com.pnu.demo.chatbot.vo.JSONChatbotDataVO;
import com.pnu.demo.chatbot.vo.JSONChatbotVO;
import net.minidev.json.JSONObject;
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
}
