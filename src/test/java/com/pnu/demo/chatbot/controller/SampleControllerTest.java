package com.pnu.demo.chatbot.controller;

import com.pnu.demo.chatbot.bookInfo.BookInfoManager;
import com.pnu.demo.chatbot.nlp.ClassificationResult;
import com.pnu.demo.chatbot.service.ChatbotService;
import com.pnu.demo.chatbot.vo.JSONChatbotDataVO;
import com.pnu.demo.chatbot.vo.JSONChatbotVO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SampleControllerTest {

    SampleController controller;

    @Before
    public void setUp() throws Exception {
        controller = new SampleController();
    }

    @Test
    public void hello() {
        // given
        String expectedValue = "hello";

        // when
        String sut = controller.hello();

        // then
        assertEquals(expectedValue, sut);
    }

    @Test
    public void chatbotmessage() {
        String query = "열람실";

        // given
        JSONChatbotVO expectedValue = new JSONChatbotVO();
        JSONChatbotDataVO data = new JSONChatbotDataVO();
        ChatbotService service = new ChatbotService();
        ClassificationResult result = service.getAnswer(query);
        data.answer = result.answer;
        data.type = result.type;
        expectedValue.data = data;

        // when
        JSONChatbotVO sut = controller.chatbotmessage(query);

        // then
        assertEquals(expectedValue.data.answer, sut.data.answer);
        assertEquals(expectedValue.data.type, sut.data.type);
        assertEquals(expectedValue.message, sut.message);
        assertEquals(expectedValue.error, sut.error);
        assertEquals(expectedValue.status, sut.status);
    }
}