package com.pnu.demo.chatbot.readingRoom;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class ReadingRoomParsing {
    public static String getResult(String library) {
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

    private static void connecting(List<ReadingRoomInfo> structure) throws IOException {
        String temp[] = new String[4];
        int ElementNum;
        int i = 0;

        Connection.Response response = Jsoup.connect("https://seat.pusan.ac.kr/Clicker/k/")
                .method(Connection.Method.GET)
                .execute();
        Document document = response.parse();
        Elements textE = document.select("td");
        ElementNum = textE.size();

        while(i<ElementNum) {
            ReadingRoomInfo roomTemp = new ReadingRoomInfo();

            temp[0] = textE.get(i++).text();
            temp[1] = textE.get(i++).text();
            temp[2] = textE.get(i++).text();
            i++;
            temp[3] = textE.get(i++).text();
            i++;
            roomTemp.insert(temp[0],temp[1],temp[2],temp[3]);
            structure.add(roomTemp);
        }
    }
}


