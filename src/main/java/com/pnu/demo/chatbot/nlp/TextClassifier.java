package com.pnu.demo.chatbot.nlp;

import com.pnu.demo.chatbot.service.ChatbotServiceDelegate;

public class TextClassifier {
    public String classify(ChatbotServiceDelegate delegate, String inputText) {
        if (inputText.contains("금정") || inputText.contains("학식")) {
            return delegate.getCafeteriaMenu();
        } else if (inputText.contains("도서관") && inputText.contains("좌석")) {
            return delegate.getLibrarySeatingInfo();
        } else if (inputText.contains("학사") && inputText.contains("일정")) {
            return delegate.getUniversityCalendar();
        } else if (inputText.contains("도서관")) {
            return delegate.getLibraryInfo();
        } else {
            return delegate.getExceptionMessage();
        }
    }
}
