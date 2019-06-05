package com.pnu.demo.chatbot.academicCalendarInfoManager;

import com.pnu.demo.chatbot.academicCalendar.AcademicCalendarInfoManager;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class AcademicCalendarInfoInfoManagerTest {

    private static final Logger logger = Logger.getLogger(AcademicCalendarInfoInfoManagerTest.class.getName());

    AcademicCalendarInfoManager service;

    @Before
    public void setUp() throws Exception {
        service = new AcademicCalendarInfoManager();
    }

    @Test
    public void testAcademicCalendarInfoManager_fail() {
        String result;
        String sut;

        // 잘못 된 정보 입력
        // given
        result = "";
        // when
        sut = service.getStringResult("잘못 된 입력");
        // then
        assertEquals(sut, result);

    }

    @Test
    public void testAcademicCalendarInfoManager_date() {
        String result;
        String sut;

        // 이번 달 학사 일정
        // given
        result = "2019.05.30 - 2019.05.31   여름계절수업 수강대상자 복학신청\n" +
                "2019.05.30 - 2019.05.31   여름계절수업 수강정정\n";
        // when
        sut = service.getStringResult("이번 달");
        // then
        assertEquals(sut, result);

        // 이번 주 학사 일정
        // given
        result = "";
        // when
        sut = service.getStringResult("이번 주");
        // then
        assertEquals(sut, result);

    }

    @Test
    public void testAcademicCalendarInfoManager_event() {
        String result;
        String sut;

        // 올해 휴일 정보
        // given
        result = "2019.06.21 - 2019.06.21   여름휴가 시작\n" +
                "2019.12.21 - 2019.12.21   겨울휴가 시작\n";
        // when
        sut = service.getStringResult("휴일");
        // then
        assertEquals(sut, result);

    }

}