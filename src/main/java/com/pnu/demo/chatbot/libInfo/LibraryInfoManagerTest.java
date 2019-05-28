package com.pnu.demo.chatbot.libInfo;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class LibraryInfoManagerTest {

    LibraryInfoManager libraryInfoManager = new LibraryInfoManager();

    @Test
    public void testLibraryInfoManager_central() throws Exception {


        String libOfficeHour = libraryInfoManager.getStringResult("중앙","학기중");

        String libOfficeHour2 = libraryInfoManager.getStringResult("중앙","방학중");

        Assert.assertThat(libOfficeHour, CoreMatchers.is("중앙도서관\n" +
                "학기중 월~목 09:00 ~ 21:00 금 09:00 ~ 18:00 09:00 ~ 13:00"));

        Assert.assertThat(libOfficeHour2, CoreMatchers.is("중앙도서관\n" +
                " 모든 요일 방학중 09:00 ~ 18:00"));
    }

    @Test
    public void testLibraryInfoManager_dawn() throws Exception {
        String libOfficeHour3 = libraryInfoManager.getStringResult("새벽","자료실");

        String libOfficeHour4 = libraryInfoManager.getStringResult("새벽","열람실");

        Assert.assertThat(libOfficeHour3, CoreMatchers.is("새벽벌도서관\n" +
                "학기중 방학중 토요일 시험기간\n" +
                "자료실 iCOMMONS 보존서고 월-목 09:00 ~ 21:00 금 09:00 ~ 18:00 09:00~18:00 09:00~13:00 09:00~21:00 일요일, 공휴일 휴실, 설/추석 연휴, 개교기념일(5월15일)"));

        Assert.assertThat(libOfficeHour4, CoreMatchers.is("새벽벌도서관\n" +
                "열람실 1열람실 24시간(자유석) 일요일, 공휴일 이용가능 (단, 설/추석 당일 이용자 감소시 에너지 절약을 위해 3열람실 휴실)2열람실 1노트북열람실 06:00~23:00 24시간3열람실 2노트북열람실 장애인열람실 대학원열람실 05:00~24:001~2박사캐럴실 06:00~23:00그룹스터디룸 09:00~21:00 09:00~18:00 09:00~13:00 09:00~21:00 일요일, 공휴일 휴실"));

    }

    @Test
    public void testLibraryInfoManager_law() throws Exception {
        String libOfficeHour5 = libraryInfoManager.getStringResult("법학","자료실");

        String libOfficeHour6 = libraryInfoManager.getStringResult("법학","열람실");

        Assert.assertThat(libOfficeHour5, CoreMatchers.is("법학도서관\n" +
                "자료실 제2법학관 4층 월-목 09:00 ~ 21:00 금 09:00 ~ 18:00 09:00 ~ 18:00 09:00 ~ 13:00 일요일, 공휴일, 설/추석 연휴 개교기념일(5월15일) 휴관\n" +
                "제2법학관 5층 09:00 ~ 18:00 휴실"));

        Assert.assertThat(libOfficeHour6, CoreMatchers.is("법학도서관\n" +
                "제2법학관 5층 09:00 ~ 18:00 휴실열람실 제1법학관 4층 (418호) 06:00 ~ 24:00 연중 무휴(법전원 운영 및 법전원 학생 전용)구분 개관시간 비고학기중 방학중 토요일자료실 제2법학관 4층 월-목 09:00 ~ 21:00 금 09:00 ~ 18:00 09:00 ~ 18:00 09:00 ~ 13:00 일요일, 공휴일, 설/추석 연휴 개교기념일(5월15일) 휴관"));

    }

    @Test
    public void testLibraryInfoManager_mirinae() throws Exception {
        String libOfficeHour7 = libraryInfoManager.getStringResult("미리내","평소");

        String libOfficeHour8 = libraryInfoManager.getStringResult("미리내","시험기간");

        Assert.assertThat(libOfficeHour7, CoreMatchers.is("미리내 도서관\n" +
                "평소 06:00 ~ 23:00 대학원/시간강사 열람실은 건설관 4층에 위치"));

        Assert.assertThat(libOfficeHour8, CoreMatchers.is("미리내 도서관\n" +
                "시험기간 05:00 ~ 24:00"));
    }

    @Test
    public void testLibraryInfoManager_life() throws Exception {
        String libOfficeHour9 = libraryInfoManager.getStringResult("의생명","학기중");

        String libOfficeHour10 = libraryInfoManager.getStringResult("의생명","방학중");

        Assert.assertThat(libOfficeHour9, CoreMatchers.is("의생명도서관\n" +
                "09:00 ~ 21:00 토, 일요일, 공휴일, 설/추석 연휴, 개교기념일(5월15일) 휴관\n" +
                "4층 단행본자료,3층 연속간행물자료실,1층 대출반납데스크"));

        Assert.assertThat(libOfficeHour10, CoreMatchers.is("의생명도서관\n" +
                "08:00 ~ 24:00 설/추석 연휴, 개교기념일(5월15일) 휴관\n" +
                "2층 그룹스터디,1층 iCOMMONS"));
    }

    @Test
    public void testLibraryInfoManager_nano() throws Exception {
        String libOfficeHour11 = libraryInfoManager.getStringResult("나노생명","자료실");

        String libOfficeHour12 = libraryInfoManager.getStringResult("나노생명","열람실");


        Assert.assertThat(libOfficeHour11, CoreMatchers.is("나노생명도서관\n" +
                "자료실 평일 (월~금) 09:00 ~ 18:00 토요일, 공휴일, 설/추석 연휴, 개교기념일(5월15일) 휴관"));

        Assert.assertThat(libOfficeHour12, CoreMatchers.is("나노생명도서관\n" +
                "열람실 평소 07:00 ~ 23:00 연중 무휴\n" +
                "시험기간 24시간 개방"));
    }

}
