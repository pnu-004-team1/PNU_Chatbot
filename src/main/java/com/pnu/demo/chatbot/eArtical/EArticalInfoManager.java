package com.pnu.demo.chatbot.eArtical;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class EArticalInfoManager {
    private final String USER_AGENT = "Mozilla/5.0";

    public String getStringResult(String searchInput) {
        EArticalInfoManager http = new EArticalInfoManager();

        String result = null;
        try {
            result = http.sendGet("https://lib.pusan.ac.kr/wp-json/eds/v1/articles/?post_per_page=3&query=&q_ti=" + searchInput +"&q_au=&q_pu=&field_code=ALL&_=1554280931831");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // HTTP GET request
    private String sendGet(String targetUrl) throws Exception {

        URL url = new URL(targetUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET"); // optional default is GET
        con.setRequestProperty("User-Agent", USER_AGENT); // add request header
        con.setRequestProperty("Content-type", "application/json");

        int responseCode = con.getResponseCode();

//        System.out.println("HTTP 응답 코드 : " + responseCode);
        if (responseCode != 200) return null;
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        String inputLine;
        StringBuffer response = new StringBuffer();
        Boolean flag = false;
        while ((inputLine = in.readLine()) != null) {
            if (!flag && inputLine.contains("</script>")) {
                flag = true;
                continue;
            }
            if (flag) {
                response.append(inputLine);
            }
        }
        in.close();

        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject)parser.parse(response.toString());
        JSONArray jsonArray = new JSONArray();
        jsonArray = (JSONArray) jsonObj.get("records");
        String result = null;
        for (int idx = 0; idx < jsonArray.size(); idx++) {
            JSONObject item = (JSONObject)jsonArray.get(idx);
            result += ("[" + String.valueOf(idx + 1) + "]" +
                    "저자: " + item.get("record_authors") +
                    "제목: " + item.get("record_authors") +
                    "출판사: " + item.get("publisher") +
                    "\n");
//            System.out.println("저자: " + item.get("record_authors"));
//            System.out.println("제목: " + item.get("record_title"));
//            System.out.println("출판사: " + item.get("publisher"));
//            System.out.println("\n");
        }
        return result;
    }
}
