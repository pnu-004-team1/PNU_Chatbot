package com.pnu.demo.chatbot.counselingConnection;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class CounselingConnection {
    private static LocalTime beginTime = LocalTime.of(10, 0);
    private static LocalTime endTime = LocalTime.of(17, 0);
    private int WaitingStaff = CallWaitingStaff();

    public String connect() {
        if(!checkWorkTime())
            return "지금은 근무시간이 아닙니다.\n근무시간은 " + beginTime.toString() + " - " + endTime.toString() + " 입니다.";
        else if(!checkWaitStaff())
            return "현재 상담 가능 인원이 없어 연결이 지연됩니다.\n연결 예상 시간은 " + EstimatedTime().toString() + " 입니다.";
        return "접수";
    }

    private boolean checkWorkTime() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.isAfter(beginTime) && currentTime.isBefore(endTime);
    }

    private boolean checkWaitStaff() {
        return WaitingStaff > 0;
    }

    private int CallWaitingStaff() {
        return 0;
    }

    private void StartCounseling() {

    }

    private void EndCounseling() {

    }

    private LocalTime EstimatedTime() {
        LocalTime estimatedTime = LocalTime.of(11, 0);
        return estimatedTime.plusMinutes(15);
    }
}
