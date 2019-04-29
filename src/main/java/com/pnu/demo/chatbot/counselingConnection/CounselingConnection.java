package com.pnu.demo.chatbot.counselingConnection;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class CounselingConnection {
    private static LocalTime beginTime = LocalTime.of(10, 0);
    private static LocalTime endTime = LocalTime.of(17, 0);

    public String connect() {
        if(!checkWorkTime())
            return "지금은 근무시간이 아닙니다.\n근무시간은 " + beginTime.toString() + " - " + endTime.toString() + " 입니다.";
        return "";
    }

    private boolean checkWorkTime() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.isAfter(beginTime) && currentTime.isBefore(endTime);
    }

}
