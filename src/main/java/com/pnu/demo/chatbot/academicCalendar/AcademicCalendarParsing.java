package com.pnu.demo.chatbot.academicCalendar;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class AcademicCalendarParsing {
    public static void main(String[] args) {
        getResult();
    }

    public static void getResult() {
        String html = "https://www.pusan.ac.kr/kor/CMS/Haksailjung/PopupView.do";
        Elements termE;
        Elements textE;

        try {
            termE = connecting(html, "th");
            textE = connecting(html, "td");
            termE.remove(0);		//delete useless information
            termE.remove(0);

            for( Element element : termE) {
                System.out.println(element.text());	//나중에 DB입력으로 바꿀 것.
            }
            for( Element element : textE) {
                System.out.println(element.text()); //나중에 DB입력으로 바꿀 것.
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Elements connecting(String html, String type) throws IOException {
        Connection.Response response = Jsoup.connect(html)
                .method(Connection.Method.GET)
                .execute();
        Document googleDocument = response.parse();
        return googleDocument.select(type);
    }
}

