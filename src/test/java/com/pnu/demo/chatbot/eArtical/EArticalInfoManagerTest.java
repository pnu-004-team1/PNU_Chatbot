package com.pnu.demo.chatbot.eArtical;

import com.pnu.demo.chatbot.service.ChatbotService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EArticalInfoManagerTest {

    ChatbotService service;

    @Before
    public void setUp() throws Exception {
        service = new ChatbotService();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testEArticalInfoManager() {
        String result;
        String sut;

        EArticalInfoManager eArticalInfoManager = new EArticalInfoManager();

        // "논문:컴퓨터비전"
        // given
        result = eArticalInfoManager.getStringResult("컴퓨터비전");
        // when
        sut = service.getAnswer("논문:컴퓨터비전").answer;
        // then
        assertEquals(sut, result);

        // "도서: 컴퓨터비전"
        // given
        result = eArticalInfoManager.getStringResult("컴퓨터비전");
        // when
        sut = service.getAnswer("논문:  컴퓨터비전").answer;
        // then
        assertEquals(sut, result);

        // "도서:컴퓨터 비전"
        // given
        result = eArticalInfoManager.getStringResult("컴퓨터비전");
        // when
        sut = service.getAnswer("논문:컴퓨터 비전").answer;
        // then
        assertEquals(sut, result);
    }
    @Test
    public void testEArticalInfoManager_catch() {
        String result;
        String sut;

        EArticalInfoManager eArticalInfoManager = new EArticalInfoManager();

        // "논문:컴퓨터비전"
        // given
        result = eArticalInfoManager.getStringResult("");
        // when
        sut = service.getAnswer("논문:").answer;
        // then
        assertEquals(sut, result);
    }
}