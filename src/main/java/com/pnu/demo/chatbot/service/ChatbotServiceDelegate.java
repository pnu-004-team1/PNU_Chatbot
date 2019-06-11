package com.pnu.demo.chatbot.service;

import net.minidev.json.JSONArray;

public interface ChatbotServiceDelegate {
    public String getBookInfo(String query);
    public String getEArtical(String query);
    public String getLibraryInfo(String libName, String category);
    public String getLibrarySeatingInfo(String query);
    public String getLibraryContactInfo(String libName);
    public String getUniversityCalendar();
    public String getFoodCafeInfo(String restaurant, String time);
//    public String getCounselingInfo();
    public String getExceptionMessage();
}
