package BOOK;

import java.util.Scanner;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

public class BookInfo {
	
	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) {
		BookInfo http = new BookInfo();
		
		String bookName;
        Scanner scan = new Scanner(System.in);      // 문자 입력을 인자로 Scanner 생성
        System.out.println("책 제목을 입력하세요:");
        
        bookName = scan.nextLine();            // 키보드 문자 입력
		
		try {
			http.sendGet("https://lib.pusan.ac.kr/wp-json/eds/v1/articles/?post_per_page=3&query=&q_ti=" + bookName +"&q_au=&q_pu=&field_code=ALL&_=1554280931831");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// HTTP GET request
	private void sendGet(String targetUrl) throws Exception {

		URL url = new URL(targetUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET"); // optional default is GET
		con.setRequestProperty("User-Agent", USER_AGENT); // add request header
		con.setRequestProperty("Content-type", "application/json");		
        
		int responseCode = con.getResponseCode();
		
		System.out.println("HTTP 응답 코드 : " + responseCode);
		if (responseCode != 200) return;
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
		for (int idx = 0; idx < jsonArray.size(); idx++) {
			JSONObject item = (JSONObject)jsonArray.get(idx);
			System.out.println("저자: " + item.get("record_authors"));
			System.out.println("제목: " + item.get("record_title"));
			System.out.println("출판사: " + item.get("publisher"));
			System.out.println("\n");
		}
		
	}
}