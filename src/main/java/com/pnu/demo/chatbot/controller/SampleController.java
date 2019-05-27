package com.pnu.demo.chatbot.controller;

import com.pnu.demo.chatbot.bookInfo.BookInfo;
import com.pnu.demo.chatbot.service.ChatbotService;
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
        JSONObject data = new JSONObject();
        data.put("answer", service.getAnswer(query));
        return vo;
    }

    @GetMapping("/bookInfo")
    public JSONChatbotVO bookinfomessage(@RequestParam String query) {
        System.out.println(query);
        JSONObject data = new JSONObject();
        JSONChatbotVO vo = new JSONChatbotVO();
        BookInfo bookInfo = new BookInfo();
        data.put("type", "bookinfo");
        data.put("data", bookInfo.getString(query));
        vo.data = data;
        return vo;
    }


}
