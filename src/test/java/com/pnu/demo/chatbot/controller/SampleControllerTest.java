package com.pnu.demo.chatbot.controller;

import com.pnu.demo.chatbot.bookInfo.BookInfoManager;
import com.pnu.demo.chatbot.nlp.ClassificationResult;
import com.pnu.demo.chatbot.service.ChatbotService;
import com.pnu.demo.chatbot.vo.JSONBookInfoVO;
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

    @Test
    public void bookinfomessage() {
        String query = "책";

        // given
        JSONBookInfoVO expectedValue = new JSONBookInfoVO();
        BookInfoManager bookInfoManager = new BookInfoManager();
        expectedValue.type = "bookinfo";
        expectedValue.data = bookInfoManager.getStringResult(query);

        // when
        JSONBookInfoVO sut = controller.bookinfomessage(query);

        // then
        assertEquals(expectedValue.data, sut.data);
        assertEquals(expectedValue.type, sut.type);
    }
}