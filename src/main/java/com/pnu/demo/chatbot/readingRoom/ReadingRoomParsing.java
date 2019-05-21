package com.pnu.demo.chatbot.readingRoom;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class ReadingRoomParsing {
    private JSONArray readingRoomArray;

    public String getResult(String library) {
        List<ReadingRoomInfo> structure = new LinkedList<>();
        String result = new String();

        try {
            connecting(structure);

            for(ReadingRoomInfo I : structure) {
                if(I.checking(library))					//출력 도서관 확인
                    result = result + I.string();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void connecting(List<ReadingRoomInfo> structure) throws IOException {
        String temp[] = new String[4];
        int ElementNum;
        int i = 0;

        this.readingRoomArray = new JSONArray();

        Connection.Response response = Jsoup.connect("https://seat.pusan.ac.kr/Clicker/k/")
                .method(Connection.Method.GET)
                .execute();
        Document document = response.parse();
        Elements textE = document.select("td");
        ElementNum = textE.size();

        while(i<ElementNum) {
            ReadingRoomInfo roomTemp = new ReadingRoomInfo();
            JSONObject readingRoomObject = new JSONObject();

            temp[0] = textE.get(i++).text();
            temp[1] = textE.get(i++).text();
            temp[2] = textE.get(i++).text();
            i++;
            temp[3] = textE.get(i++).text();
            i++;

            readingRoomObject.put("위치", temp[0]);
            readingRoomObject.put("좌석", temp[1]);
            readingRoomObject.put("잔여좌석", temp[2]);
            readingRoomObject.put("운영상태", temp[3]);
            this.readingRoomArray.add(readingRoomObject);

            roomTemp.insert(temp[0],temp[1],temp[2],temp[3]);
            structure.add(roomTemp);
        }
    }
}


