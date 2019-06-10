package com.pnu.demo.chatbot.readingRoom;

import org.springframework.stereotype.Component;

@Component
public class ReadingRoomInfo {
    private String place;
    private String seat;
    private String residual;
    private String condition;

    public ReadingRoomInfo insert(String inputPlace, String inputSeat, String inputResidual, String inputCondition) {
        place = inputPlace;
        seat = inputSeat;
        residual = inputResidual;
        condition = inputCondition;

        return this;
    }

    public String string() {
        return "위치:    " + place + "\n좌석:    " + seat + "\n잔여좌석: "
                + residual + "\n운영상태: " + condition + "\n";
    }
    public boolean checking(String library) {
        String substr = this.place.split("]")[0];
        boolean check = false;

        switch(library){
            case "새벽벌":
                if(substr.equals("[새벽벌"))
                    check = true;
                break;
            case "미리내":
                if(substr.equals("[건설관"))
                    check = true;
                break;
            case "나노생명" :
                if(substr.equals("[나노생명과학도서관"))
                    check = true;
                break;
            case "all":
                check = true;
                break;
            default:
                System.out.println("Could not find library");
                break;
        }
        return check;
    }
}