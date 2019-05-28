package com.pnu.demo.chatbot.service;

import com.pnu.demo.chatbot.academicCalendar.AcademicCalendarInfoManager;
import com.pnu.demo.chatbot.counselingConnection.CounselingConnection;
import com.pnu.demo.chatbot.eArtical.EArticalInfoManager;
import com.pnu.demo.chatbot.libInfo.LibraryContactInfoManager;
import com.pnu.demo.chatbot.libInfo.LibraryInfoManager;
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
        sut = service.getAnswer("중앙도서관 학기중 정보 알려줘");
        // then
        assertEquals(sut, result);


        // "중앙도서관 방학중 정보 알려줘"
        // given
        result = infoManager.getStringResult("중앙", "방학중");
        // when
        sut = service.getAnswer("중앙도서관 방학중 정보 알려줘");
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
        sut = service.getAnswer("새벽도서관 자료실 정보 알려줘");
        // then
        assertEquals(sut, result);


        // "새벽도서관 열람실 정보 알려줘"
        // given
        result = infoManager.getStringResult("새벽", "열람실");
        // when
        sut = service.getAnswer("새벽도서관 열람실 정보 알려줘");
        // then
        assertEquals(sut, result);
    }

//    @Test
//    public void testLibraryInfoManager_buphack() {
//        String result;
//        String sut;
//
//        LibraryInfoManager infoManager = new LibraryInfoManager();
//
//
//
//        // "법학도서관 자료실 정보 알려줘"
//        // given
//        result = infoManager.getStringResult("법학", "자료실");
//        // when
//        sut = service.getAnswer("법학도서관 자료실 정보 알려줘");
//        // then
//        assertEquals(sut, result);
//
//        // "법학도서관 열람실 정보 알려줘"
//        // given
//        result = infoManager.getStringResult("법학", "열람실");
//        // when
//        sut = service.getAnswer("법학도서관 열람실 정보 알려줘");
//        // then
//        assertEquals(sut, result);
//    }
//
//    @Test
//    public void testLibraryInfoManager_mirinae() {
//        String result;
//        String sut;
//
//        LibraryInfoManager infoManager = new LibraryInfoManager();
//
//
//        // "미리내도서관 열람실 정보 알려줘"
//        // given
//        result = infoManager.getStringResult("미리내", "평소");
//        // when
//        sut = service.getAnswer("미리내도서관 평소 정보 알려줘");
//        // then
//        assertEquals(sut, result);
//
//        // "미리내도서관 열람실 정보 알려줘"
//        // given
//        result = infoManager.getStringResult("미리내", "시험기간");
//        // when
//        sut = service.getAnswer("미리내도서관 시험기간 정보 알려줘");
//        // then
//        assertEquals(sut, result);
//    }

    @Test
    public void testLibraryInfoManager_sangmyeong() {
        String result;
        String sut;

        LibraryInfoManager infoManager = new LibraryInfoManager();


        // "의생명도서관 열람실 정보 알려줘"
        // given
        result = infoManager.getStringResult("의생명", "학기중");
        // when
        sut = service.getAnswer("의생명도서관 학기중 정보 알려줘");
        // then
        assertEquals(sut, result);

        // "의생명도서관 열람실 정보 알려줘"
        // given
        result = infoManager.getStringResult("의생명", "방학중");
        // when
        sut = service.getAnswer("의생명도서관 방학중 정보 알려줘");
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
        sut = service.getAnswer("나노생명도서관 자료실 정보 알려줘");
        // then
        assertEquals(sut, result);

        // "나노생명도서관 열람실 정보 알려줘"
        // given
        result = infoManager.getStringResult("나노생명", "열람실");
        // when
        sut = service.getAnswer("나노생명도서관 열람실 정보 알려줘");
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
        sut = service.getAnswer("도서:인문");
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
        sut = service.getAnswer("법학도서관 연락처");
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
        result = infoManager.getStringResult("올해 휴일");
        // when
        sut = service.getAnswer("올해 학교 공휴일 알려줘");
        // then
        assertEquals(sut, result);
    }

//    @Test
//    public void testGetCounselingInfo() {
//        String result;
//        String sut;
//
//        CounselingConnection infoManager = new CounselingConnection();
//
//        // "법학도서관 연락처"
//        // given
//        result = infoManager.getStringResult();
//        // when
//        sut = service.getAnswer("상담원 연락처 알려줘");
//        // then
//        assertEquals(sut, result);
//    }

    @Test
    public void testGetExceptionMessage() {
        String result;
        String sut;


        // "법학도서관 연락처"
        // given
        result = "잘 모르겠네요...";
        // when
        sut = service.getAnswer("날씨 알려줘");
        // then
        assertEquals(sut, result);
    }
}