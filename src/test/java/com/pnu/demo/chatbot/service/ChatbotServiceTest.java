package com.pnu.demo.chatbot.service;

import org.junit.Test;

import java.util.logging.*;
import static org.junit.Assert.*;

public class ChatbotServiceTest {

    private static final Logger logger = Logger.getLogger(ChatbotServiceTest.class.getName());

    @Test
    public void test() {
        ChatbotService service = new ChatbotService();

        String question;

        question = "학사 일정 알려줘";
        print(question, service.getAnswer(question));

        question = "오늘 학식이 뭐야?";
        print(question, service.getAnswer(question));

        question = "중간고사 언제야?";
        print(question, service.getAnswer(question));

        question = "부산대 정보";
        print(question, service.getAnswer(question));

        question = "도서관 정보";
        print(question, service.getAnswer(question));

        question = "정보컴퓨터공학부 홈페이지";
        print(question, service.getAnswer(question));
    }

    private void print(String question, String answer) {
        System.out.println("\nQ.\n" + question);
        System.out.println("\nA.\n" + answer + "\n");
    }
}