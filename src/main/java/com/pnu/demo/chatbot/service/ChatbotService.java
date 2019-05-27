package com.pnu.demo.chatbot.service;

import com.pnu.demo.chatbot.nlp.*;
import com.pnu.demo.chatbot.libInfo.*;
import com.pnu.demo.chatbot.readingRoom.*;
import com.pnu.demo.chatbot.academicCalendar.*;
import com.pnu.demo.chatbot.eArtical.*;
import com.pnu.demo.chatbot.counselingConnection.*;

//import org.json.simple.JSONObject;

public class ChatbotService implements ChatbotServiceDelegate {

    private TextClassifier classifer = new TextClassifier();

    public String getAnswer(String questionText) {
        return classifer.classify(this, questionText);
    }

    @Override // 담당자: 구민규
    public String getBookInfo(String query) {
        EAriticalInoManager infoManager = new EAriticalInoManager();
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
        String result = infoManager.getStringResult("올해 휴일");
        return result;
    }

    @Override // 담당자: 안재우
    public String getCounselingInfo() {
        CounselingConnection infoManager = new CounselingConnection();
        String result = infoManager.getStringResult();
        return result;
    }

    @Override
    public String getExceptionMessage() {
        return "잘 모르겠네요...";
    }
}
