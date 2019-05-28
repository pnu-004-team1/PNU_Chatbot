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
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getStringResult() {
        String result;
        String sut;

        EArticalInfoManager eArticalInfoManager = new EArticalInfoManager();

        // "도서: 컴퓨터비전"
        // given
        result = eArticalInfoManager.getStringResult("컴퓨터비전");
        // when
        sut = service.getAnswer("도서: 컴퓨터비전");
        // then
        assertEquals(sut, result);
    }
}