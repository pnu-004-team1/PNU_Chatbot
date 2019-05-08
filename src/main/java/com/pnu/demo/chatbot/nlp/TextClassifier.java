package com.pnu.demo.chatbot.nlp;

import com.pnu.demo.chatbot.service.ChatbotServiceDelegate;

public class TextClassifier {
    public String classify(ChatbotServiceDelegate delegate, String inputText) {
        if (inputText.startsWith("도서:")) {
            String query = inputText.replace("도서:", "");
            return delegate.getBookInfo(query);
        } else if (inputText.contains("열람실") || inputText.contains("좌석")) {
            if (inputText.contains("새벽벌")) {
                return delegate.getLibrarySeatingInfo("새벽벌");
            } else if (inputText.contains("건설관")) {
                return delegate.getLibrarySeatingInfo("건설관");
            } else if (inputText.contains("나노생명")) {
                return delegate.getLibrarySeatingInfo("나노생명");
            } else {
                return delegate.getLibrarySeatingInfo("all");
            }
        } else if (inputText.contains("학사") && inputText.contains("일정")) {
            return delegate.getUniversityCalendar();
        } else if (inputText.contains("도서관")) {
            return delegate.getLibraryInfo();
        } else {
            return delegate.getExceptionMessage();
        }
    }
}
