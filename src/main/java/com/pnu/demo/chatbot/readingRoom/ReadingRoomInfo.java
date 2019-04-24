package com.pnu.demo.chatbot.readingRoom;

import org.springframework.stereotype.Component;

@Component
public class ReadingRoomInfo {
    private String Place;
    private String Seat;
    private String Residual;
    private String Condition;

    public ReadingRoomInfo insert(String P, String S, String R, String C) {
        Place = P;
        Seat = S;
        Residual = R;
        Condition = C;

        return this;
    }

    public String string() {
        return "위치:    " + Place + "\n좌석:    " + Seat + "\n잔여좌석: "
                + Residual + "\n운영상태: " + Condition + "\n";
    }
    public boolean checking(String libaray) {
        String substr = this.Place.split("]")[0];
        boolean check = false;

        switch(libaray){
            case "새벽벌":
                if(substr.equals("[새벽벌"))
                    check = true;
                break;
            case "건설관":
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