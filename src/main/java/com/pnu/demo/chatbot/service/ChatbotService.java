package com.pnu.demo.chatbot.service;
//import com.pnu.demo.chatbot.readingRoom;

import com.pnu.demo.chatbot.nlp.*;
import com.pnu.demo.chatbot.libInfo.*;

//import org.json.simple.JSONObject;

public class ChatbotService implements ChatbotServiceDelegate {

    private TextClassifier classifer = new TextClassifier();

    public String getAnswer(String questionText) {
        return classifer.classify(this, questionText);
    }

    @Override
    public String getCafeteriaMenu() {
        return "[금정회관 중식 - 19.04.10(수)]\n" +
                "정식-4,000원\n" +
                "백미밥 \n" +
                "들깨시락국 \n" +
                "함박st+치즈 \n" +
                "짜장 \n" +
                "오징어참나물무침 \n" +
                "무말랭이지 \n" +
                "포기김치";
    }

    @Override
    public String getLibraryInfo() {
        LibraryCrawler lawLibCrawler = new LibraryCrawler();;
        String result = lawLibCrawler.getLibraryOfficeHours("중앙", "학기중");
        return result;
    }

    @Override
    public String getLibrarySeatingInfo() {
        return "위치: [새벽벌] 2층 1열람실\n" +
                "좌석: 357\n" +
                "잔여좌석: 357\n" +
                "운영상태: 자유이용\n" +
                "\n" +
                "위치: [건설관] 3층 제1열람실\n" +
                "좌석: 168\n" +
                "잔여좌석: 163\n" +
                "운영상태: 운영중\n" +
                "\n" +
                "위치: [나노생명과학도서관] 제 1열람실\n" +
                "좌석: 108\n";
    }

    @Override
    public String getUniversityCalendar() {
        return "[학사일정]\n" +
                "후기 학위청구 심사용 논문 제출\n" +
                "2019.04.09 - 2019.04.09\n" +
                "1학기 중간고사\n" +
                "2019.04.15 - 2019.04.20\n" +
                "1학기 수업일수 1/2선\n" +
                "2019.04.25 - 2019.04.25\n" +
                "여름계절수업 수강대상자 복학신청\n" +
                "2019.05.02 - 2019.05.09\n" +
                "1학기 수업일수 2/3선\n" +
                "2019.05.13 - 2019.05.13\n" +
                "개교기념일\n" +
                "2019.05.15 - 2019.05.15\n" +
                "여름계절수업 희망과목담기\n" +
                "2019.05.16 - 2019.05.17\n" +
                "여름계절수업 수강신청\n" +
                "2019.05.20 - 2019.05.22\n" +
                "여름계절수업 수강정정\n" +
                "2019.05.28 - 2019.05.29";
    }

    @Override
    public String getExceptionMessage() {
        return "잘 모르겠네요...";
    }
}
