package com.pnu.demo.chatbot.bookInfo;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BookInfo {
    private final String USER_AGENT = "Mozilla/5.0";

    public JSONArray getString(String title){
        JSONArray result = null;
        BookInfo http = new BookInfo();
        try {
            result = http.sendGet("https://lib.pusan.ac.kr/resource/?query="+title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // HTTP GET request
    private JSONArray sendGet(String targetUrl) throws Exception {
        JSONArray book_array = new JSONArray();
        try {

            // 1. HTML 가져오기
            Connection conn = Jsoup
                    .connect(targetUrl)
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .userAgent(USER_AGENT)
                    .method(Connection.Method.GET)
                    .ignoreContentType(true);

            Document doc = conn.get();

            // 2. 가져온 HTML Document 를 확인하기
            //System.out.println(doc.toString());

            Element Books = doc.select(".rd-result-list").get(0);
            for (Element e : Books.select(".item")) {
                String thumbnail = e.select(".thumbnail img").attr("src");
                String title = e.select(".title").text();
                String author = e.select("dd.author").text();
                String publication = e.select("dd.publication").text();
                JSONArray bookStatusArray = new JSONArray();
                Element rd_list = e.select(".rd-list.inline.location").get(0);
                for (Element rd : rd_list.select("dt")) {
                    JSONObject bookLocation = new JSONObject();
                    bookLocation.put("location", rd.text());
                    bookStatusArray.add(bookLocation);
                }
                Elements rd_status = rd_list.select("dd span");
                for (int index = 0; index < rd_status.size(); index++) {
                    JSONObject bookStatus = (JSONObject) bookStatusArray.get(index);
                    bookStatus.put("status", rd_status.get(index).text().replace("[", "").replace(" ]", ""));
                }
                JSONObject bookObject = new JSONObject();
                bookObject.put("thumbnail", thumbnail);
                bookObject.put("title", title);
                bookObject.put("author", author);
                bookObject.put("publication", publication);
                bookObject.put("status_list", bookStatusArray);
                book_array.add(bookObject);
            }
        } catch (IOException e) {
            // Exp : Connection Fail
            e.printStackTrace();
        }
        return book_array;
    }
}
