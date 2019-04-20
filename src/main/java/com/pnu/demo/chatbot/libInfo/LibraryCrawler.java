package com.pnu.demo.chatbot.libInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class LibraryCrawler {
    private Document libDoc;
    private String title;
    private String url;
    private String category;
    private ArrayList<String> openHourAllByCol = new ArrayList<String>();
    private String openHourAllInfos;
    private String starisGuideAllInfos;
    private ArrayList<String> eachStairs = new ArrayList<String>();
    private ArrayList<String> eachStairsImg = new ArrayList<String>();

    public LibraryCrawler() {

    }

    public LibraryCrawler(String url) {
        this.url = url;
    }

    private void setJsoupInfos(String URL, ArrayList<String> eachStairs)  {

        try {
            libDoc = Jsoup.connect(URL).get();
        }catch (IOException e) {
            System.out.println (e.toString());
        }

        title = libDoc.select("h1.entry-title").text();

        // 개관시간
        for (Element element:libDoc.select("div.mps tr")){
            openHourAllByCol.add(element.text());
        }

        openHourAllInfos = openHourAllByCol.toString();

        this.eachStairs = eachStairs;

        eachStairs.add("4층");
        eachStairs.add("5층");

        // 층별안내
        for (Element element:libDoc.select("div.mps-content img")){
            eachStairsImg.add(element.attr("src"));
        }

        starisGuideAllInfos = eachStairsImg.toString();


    }

    public String getLibraryOfficeHours(String libName, String classify) {
        ArrayList<String> temp =  new ArrayList<String>();

        String resultStr = "";
        switch(libName) {
            case "중앙":
            {
                temp.add("1층"); temp.add("2층 문학예술자료관");
                temp.add("3층 인문사회과학자료관"); temp.add("4층 과학기술자료관");
                setJsoupInfos("https://lib.pusan.ac.kr/intro/plot-plan/lib1-open-plot/",temp);
                switch(classify) {
                    case "학기중" :
                        resultStr = libDoc.select("div.mps tr.row-3").text();
                        break;
                    case "방학중" :
                        resultStr += "모든 요일 " +  libDoc.select("div.mps tr.row-4").text();
                        break;
                    default:
                        resultStr = "해당 개관시간이 존재하지 않습니다.";
                }
                break;
            }
            case "새벽":
            {
                temp.add("1층"); temp.add("2층");
                temp.add("3층"); temp.add("4층");
                setJsoupInfos("https://lib.pusan.ac.kr/intro/plot-plan/lib2-open-plot/",temp);
                // 개관시간
                for (Element element:libDoc.select("div.mps tr")){
                    openHourAllByCol.add(element.text());
                }
                switch(classify) {
                    case "자료실" :
                        resultStr = openHourAllByCol.get(1) + "\n" +  openHourAllByCol.get(2);
                        break;
                    case "열람실" :
                        resultStr = openHourAllByCol.get(3) + openHourAllByCol.get(4) + openHourAllByCol.get(5)
                        + openHourAllByCol.get(6) + openHourAllByCol.get(7);
                        break;
                    default:
                        resultStr = "해당 개관시간이 존재하지 않습니다.";
                }
                break;
            }
            case "법학":
            {
                temp.add("4층"); temp.add("5층");
                setJsoupInfos("https://lib.pusan.ac.kr/intro/plot-plan/law-open-plot/",temp);
                // 개관시간
                for (Element element:libDoc.select("div.mps tr")){
                    openHourAllByCol.add(element.text());
                }
                switch(classify) {
                    case "자료실" :
                        resultStr = openHourAllByCol.get(2) + "\n" + openHourAllByCol.get(3);
                        break;
                    case "열람실" :
                        resultStr = openHourAllByCol.get(3) + openHourAllByCol.get(4) + openHourAllByCol.get(5)
                                + openHourAllByCol.get(6) + openHourAllByCol.get(7);
                        break;
                    default:
                        resultStr = "해당 개관시간이 존재하지 않습니다.";
                }
                break;
            }
            default: System.out.println("도서관 이름이 존재하지 않습니다.");
        }

        return resultStr;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    private void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
