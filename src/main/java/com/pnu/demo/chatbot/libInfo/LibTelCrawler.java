package com.pnu.demo.chatbot.libInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class LibTelCrawler {
    private Document libDoc;

    private void setJsoupInfos(String URL)  {

        try {
            libDoc = Jsoup.connect(URL).get();
        }catch (IOException e) {
            System.out.println (e.toString());
        }


    }

    public String getLibTelNums(String libName) {
        String resultStr = "";
        ArrayList<String> telsAllByCol = new ArrayList();
        ArrayList<String> emailsAllByCol = new ArrayList();
        String getTextByCols = "tr a";
        try {
            libDoc = Jsoup.connect("https://lib.pusan.ac.kr/community/ask-tel/").get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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


        return resultStr;
    }
}
