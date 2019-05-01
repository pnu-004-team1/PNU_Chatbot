package com.pnu.demo.chatbot.service;

public interface ChatbotServiceDelegate {
    public String getBookInfo(String query);
    public String getCafeteriaMenu();
    public String getLibraryInfo();
    public String getLibrarySeatingInfo();
    public String getUniversityCalendar();
    public String getExceptionMessage();
}
