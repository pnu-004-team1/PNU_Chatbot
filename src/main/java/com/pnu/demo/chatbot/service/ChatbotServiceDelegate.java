package com.pnu.demo.chatbot.service;

public interface ChatbotServiceDelegate {
    public String getBookInfo(String query);
    public String getLibraryInfo(String libName, String category);
    public String getLibrarySeatingInfo(String query);
    public String getUniversityCalendar();
    public String getExceptionMessage();
}
