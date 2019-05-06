package com.pnu.demo.chatbot.bookInfo;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Component
public class BookInfo {
    private final String USER_AGENT = "Mozilla/5.0";

    public String getString(String title){
        String result = null;
        BookInfo http = new BookInfo();
        try {
            result = http.sendGet("https://lib.pusan.ac.kr/resource/?query="+title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // HTTP GET request
    private String sendGet(String targetUrl) throws Exception {
        String result = "";
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
            for(Element e : Books.select(".item")) {
                result += (e.select(".title a").text() +
                        e.select(".publication").text() +
                        e.select(".rd-list dt").text() +
                        e.select(".rd-list dd").text() +
                        "\n");
            }
        } catch (IOException e) {
            // Exp : Connection Fail
            e.printStackTrace();
        }
        return result;
    }
}
