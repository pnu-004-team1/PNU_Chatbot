package com.pnu.demo.chatbot.libInfo;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class LibraryContactInfoManager {
    private Document libDoc;
    private String resultStr = "";
    private ArrayList<String> telsAllByCol = new ArrayList();
    private ArrayList<String> emailsAllByCol = new ArrayList();
    private String getTextByCols = "tr a";


    private void setJsoupInfos() {
        try {
            libDoc = Jsoup.connect("https://lib.pusan.ac.kr/community/ask-tel/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 연락처 정보 파싱
        for (Element element:libDoc.select("tr")){
            telsAllByCol.add(element.text());
        }

        // 이메일 파싱
        Elements Link = libDoc.select(getTextByCols);
        for(Element link : Link) {
            emailsAllByCol.add(link.attr("abs:href"));
        }
    }


    public String getStringResult(String libName) {

        setJsoupInfos();

        resultStr = "";
        switch(libName) {
            case "부산": {
                resultStr += "부산캠퍼스\n" + telsAllByCol.get(0) + "\n";
                for(int i = 1; i < 10; ++i)
                    resultStr += telsAllByCol.get(i) + " " + emailsAllByCol.get(i-1) + "\n";

                resultStr += telsAllByCol.get(11) + "\n";

                for(int i = 12; i < 27; ++i)
                    resultStr += telsAllByCol.get(i) + " " + emailsAllByCol.get(i-2) + "\n";
                break;
            }
            case "법학": {
                resultStr += "법학캠퍼스\n" + telsAllByCol.get(27) + "\n";;
                for(int i = 28; i < 30; ++i)
                    resultStr += telsAllByCol.get(i) + " " + emailsAllByCol.get(i-3) + "\n";
                break;
            }
            case "양산": {
                resultStr += "양산캠퍼스\n" + telsAllByCol.get(30) + "\n";;
                for(int i = 31; i < 32; ++i)
                    resultStr += telsAllByCol.get(i) + " " + emailsAllByCol.get(i-4) + "\n";
                break;
            }
            case "아미": {
                resultStr += "아미캠퍼스\n" + telsAllByCol.get(32) + "\n";;
                for(int i = 33; i < 34; ++i)
                    resultStr += telsAllByCol.get(i) + " " + emailsAllByCol.get(i-5) + "\n";
                break;
            }
            case "밀양": {
                resultStr += "밀양캠퍼스\n" + telsAllByCol.get(34) + "\n";;
                for(int i = 35; i < 36; ++i)
                    resultStr += telsAllByCol.get(i) + " " + emailsAllByCol.get(i-6) + "\n";
                break;
            }
            default: System.out.println("도서관 이름이 존재하지 않습니다.");
        }

        telsAllByCol.clear();
        emailsAllByCol.clear();

        return resultStr;
    }

    public JSONObject getLibTelNumsJson(String libName) throws IOException, JSONException {

        setJsoupInfos();

        JSONObject libLibTelNum = new JSONObject();
        JSONObject libLibTelNums = new JSONObject();
        switch(libName) {
            case "부산": {
                libLibTelNums.put("library", "부산캠퍼스");
                resultStr += telsAllByCol.get(0) + "\n";
                for(int i = 1; i < 10; ++i)
                    resultStr += telsAllByCol.get(i) + " " + emailsAllByCol.get(i-1) + "\n";

                resultStr += telsAllByCol.get(11) + "\n";

                for(int i = 12; i < 27; ++i)
                    resultStr += telsAllByCol.get(i) + " " + emailsAllByCol.get(i-2) + "\n";

                libLibTelNums.put("telNums", resultStr);
                break;
            }
            case "법학": {
                libLibTelNums.put("library", "법학캠퍼스");
                resultStr += telsAllByCol.get(27) + "\n";
                for(int i = 28; i < 30; ++i)
                    resultStr += telsAllByCol.get(i) + " " + emailsAllByCol.get(i-3) + "\n";

                libLibTelNums.put("telNums", resultStr);
                break;
            }
            case "양산": {
                libLibTelNums.put("library", "양산캠퍼스");
                resultStr += telsAllByCol.get(30) + "\n";
                for(int i = 31; i < 32; ++i)
                    resultStr += telsAllByCol.get(i) + " " + emailsAllByCol.get(i-4) + "\n";

                libLibTelNums.put("telNums", resultStr);
                break;
            }
            case "아미": {
                libLibTelNums.put("library", "아미캠퍼스");
                resultStr += telsAllByCol.get(32) + "\n";
                for(int i = 33; i < 34; ++i)
                    resultStr += telsAllByCol.get(i) + " " + emailsAllByCol.get(i-5) + "\n";

                libLibTelNums.put("telNums", resultStr);
                break;
            }
            case "밀양": {
                libLibTelNums.put("library", "밀양캠퍼스");
                resultStr += telsAllByCol.get(34) + "\n";
                for(int i = 35; i < 36; ++i)
                    resultStr += telsAllByCol.get(i) + " " + emailsAllByCol.get(i-6) + "\n";

                libLibTelNums.put("telNums", resultStr);
                break;
            }
            default: System.out.println("도서관 이름이 존재하지 않습니다.");
        }

        telsAllByCol.clear();
        emailsAllByCol.clear();
        resultStr = "";

        libLibTelNums.put("type","libTelNum");
        libLibTelNum.put("data",libLibTelNums);

        return libLibTelNum;
    }
}
