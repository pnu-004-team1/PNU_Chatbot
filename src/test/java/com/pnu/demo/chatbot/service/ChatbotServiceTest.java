package com.pnu.demo.chatbot.service;

import com.pnu.demo.chatbot.academicCalendar.AcademicCalendarInfoManager;
import com.pnu.demo.chatbot.counselingConnection.CounselingConnection;
import com.pnu.demo.chatbot.eArtical.EArticalInfoManager;
import com.pnu.demo.chatbot.libInfo.LibraryContactInfoManager;
import com.pnu.demo.chatbot.libInfo.LibraryInfoManager;
import com.pnu.demo.chatbot.readingRoom.LibraryStudyRoomInfoManager;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.*;
import static org.junit.Assert.*;

public class ChatbotServiceTest {

    private static final Logger logger = Logger.getLogger(ChatbotServiceTest.class.getName());

    ChatbotService service;

    @Before
    public void setUp() throws Exception {
        service = new ChatbotService();
    }

    @Test
    public void testLibraryInfoManager_jungang() {
        String result;
        String sut;

        LibraryInfoManager infoManager = new LibraryInfoManager();

        // "중앙도서관 학기중 정보 알려줘"
        // given
        result = infoManager.getStringResult("중앙", "학기중");
        // when
        sut = service.getAnswer("중앙도서관 학기중 정보 알려줘").answer;
        // then
        assertEquals(sut, result);


        // "중앙도서관 방학중 정보 알려줘"
        // given
        result = infoManager.getStringResult("중앙", "방학중");
        // when
        sut = service.getAnswer("중앙도서관 방학중 정보 알려줘").answer;
        // then
        assertEquals(sut, result);


//        // "중앙도서관 뭔가 정보 알려줘"
//        // given
//        result = infoManager.getStringResult("중앙", null);
//        // when
//        sut = service.getAnswer("중앙도서관 뭔가 정보 알려줘");
//        // then
//        assertEquals(sut, result);
    }

    @Test
    public void testLibraryInfoManager_saebyuck() {
        String result;
        String sut;

        LibraryInfoManager infoManager = new LibraryInfoManager();

        // "새벽도서관 자료실 정보 알려줘"
        // given
        result = infoManager.getStringResult("새벽", "자료실");
        // when
        sut = service.getAnswer("새벽도서관 자료실 정보 알려줘").answer;
        // then
        assertEquals(sut, result);
    }

    @Test
    public void testLibraryInfoManager_sangmyeong() {
        String result;
        String sut;

        LibraryInfoManager infoManager = new LibraryInfoManager();


        // "의생명도서관 열람실 정보 알려줘"
        // given
        result = infoManager.getStringResult("의생명", "학기중");
        // when
        sut = service.getAnswer("의생명도서관 학기중 정보 알려줘").answer;
        // then
        assertEquals(sut, result);

        // "의생명도서관 열람실 정보 알려줘"
        // given
        result = infoManager.getStringResult("의생명", "방학중");
        // when
        sut = service.getAnswer("의생명도서관 방학중 정보 알려줘").answer;
        // then
        assertEquals(sut, result);
    }

    @Test
    public void testLibraryInfoManager_nano() {
        String result;
        String sut;

        LibraryInfoManager infoManager = new LibraryInfoManager();


        // "나노생명도서관 열람실 정보 알려줘"
        // given
        result = infoManager.getStringResult("나노생명", "자료실");
        // when
        sut = service.getAnswer("나노생명도서관 자료실 정보 알려줘").answer;
        // then
        assertEquals(sut, result);
    }


    @Test
    public void testGetBookInfo() {
        String result;
        String sut;

        EArticalInfoManager infoManager = new EArticalInfoManager();


        // "도서:인문"
        // given
        result = infoManager.getStringResult("인문");
        // when
        sut = service.getAnswer("논문:인문").answer;
        // then
        assertEquals(sut, result);
    }

    @Test
    public void testGetLibraryContactInfo() {
        String result;
        String sut;

        LibraryContactInfoManager infoManager = new LibraryContactInfoManager();


        // "법학도서관 연락처"
        // given
        result = infoManager.getStringResult("법학");
        // when
        sut = service.getAnswer("법학도서관 연락처").answer;
        // then
        assertEquals(sut, result);
    }

    @Test
    public void testGetUniversityCalendar() {
        String result;
        String sut;

        AcademicCalendarInfoManager infoManager = new AcademicCalendarInfoManager();

        // "법학도서관 연락처"
        // given
        result = infoManager.getStringResult("학사 일정");
        // when
        sut = service.getAnswer("학사 일정 알려줘").answer;
        // then
        assertEquals(sut, result);
    }

    @Test
    public void testGetLibrarySeatingInfo() {
        String result;
        String sut;

        LibraryStudyRoomInfoManager infoManager = new LibraryStudyRoomInfoManager();

        // "법학도서관 연락처"
        // given
        result = infoManager.getStringResult("새벽벌");
        // when
        sut = service.getAnswer("새벽벌 열람실 좌석 정보!!").answer;
        // then
        assertEquals(sut, result);
    }

    @Test
    public void testGetExceptionMessage() {
        String result;
        String sut;

        // "법학도서관 연락처"
        // given
        result = "잘 모르겠네요...";
        // when
        sut = service.getAnswer("날씨 알려줘").answer;
        // then
        assertEquals(sut, result);
    }
}