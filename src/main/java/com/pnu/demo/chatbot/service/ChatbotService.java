package com.pnu.demo.chatbot.service;

import com.pnu.demo.chatbot.nlp.*;
import com.pnu.demo.chatbot.libInfo.*;
import com.pnu.demo.chatbot.readingRoom.*;
import com.pnu.demo.chatbot.academicCalendar.*;
import com.pnu.demo.chatbot.eArtical.*;

//import org.json.simple.JSONObject;

public class ChatbotService implements ChatbotServiceDelegate {

    private TextClassifier classifer = new TextClassifier();

    public String getAnswer(String questionText) {
        return classifer.classify(this, questionText);
    }

    @Override // 담당자: 구민규
    public String getBookInfo(String query) {
        EAritical  aAritical = new EAritical();
        String result = aAritical.parsing(query);
        return result;
    }

    @Override // 담당자: 류강현
    public String getLibraryInfo(String libName, String category) {
        LibraryCrawler lawLibCrawler = new LibraryCrawler();
        String result = lawLibCrawler.getLibraryOfficeHours(libName, category);
        return result;
    }

    @Override // 담당자: 안재우
    public String getLibrarySeatingInfo(String query) {
        ReadingRoomParsing crawler = new ReadingRoomParsing();
        String result = crawler.getResult(query);
        return result;
    }

    @Override // 담당자: 안재우
    public String getUniversityCalendar() {
        AcademicCalendarParsing Crawler = new AcademicCalendarParsing();
        String result = Crawler.getResult("올해 휴일");
        return result;
    }

    @Override
    public String getExceptionMessage() {
        return "잘 모르겠네요...";
    }
}
