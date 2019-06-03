package com.pnu.demo.chatbot.service;

import com.pnu.demo.chatbot.nlp.*;
import com.pnu.demo.chatbot.libInfo.*;
import com.pnu.demo.chatbot.readingRoom.*;
import com.pnu.demo.chatbot.academicCalendar.*;
import com.pnu.demo.chatbot.eArtical.*;
import com.pnu.demo.chatbot.counselingConnection.*;
import com.pnu.demo.chatbot.bookInfo.*;
import net.minidev.json.JSONArray;

//import org.json.simple.JSONObject;

public class ChatbotService implements ChatbotServiceDelegate {

    private TextClassifier classifer = new TextClassifier();

    public String getAnswer(String questionText) {
        return classifer.classify(this, questionText);
    }

//    @Override // 담당자: 구민규
//    public JSONArray getBookInfo(String query) {
//        BookInfoManager bookInfoManager = new BookInfoManager();
//        JSONArray result = bookInfoManager.getStringResult(query);
//        return result;
//    }

    @Override // 담당자: 구민규
    public String getEArtical(String query) {
        EArticalInfoManager infoManager = new EArticalInfoManager();
        String result = infoManager.getStringResult(query);
        return result;
    }

    @Override // 담당자: 류강현
    public String getLibraryInfo(String libName, String category) {
        LibraryInfoManager infoManager = new LibraryInfoManager();
        String result = infoManager.getStringResult(libName, category);
        return result;
    }

    @Override // 담당자: 류강현
    public String getLibraryContactInfo(String libName) {
        LibraryContactInfoManager infoManager = new LibraryContactInfoManager();
        String result = infoManager.getStringResult(libName);
        return result;
    }

    @Override // 담당자: 안재우
    public String getLibrarySeatingInfo(String query) {
        LibraryStudyRoomInfoManager infoManager = new LibraryStudyRoomInfoManager();
        String result = infoManager.getStringResult(query);
        return result;
    }

    @Override // 담당자: 안재우
    public String getUniversityCalendar() {
        AcademicCalendarInfoManager infoManager = new AcademicCalendarInfoManager();
        String result = infoManager.getStringResult("학사 일정");
        return result;
    }

//    @Override // 담당자: 안재우
//    public String getCounselingInfo() {
//        CounselingConnection infoManager = new CounselingConnection();
//        String result = infoManager.getStringResult(1,"상담 내용");
//        return result;
//    }

    @Override
    public String getExceptionMessage() {
        return "잘 모르겠네요...";
    }
}
