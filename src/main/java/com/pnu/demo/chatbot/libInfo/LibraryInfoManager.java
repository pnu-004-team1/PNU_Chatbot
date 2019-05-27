package com.pnu.demo.chatbot.libInfo;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class LibraryInfoManager {
    private Document libDoc;
    private String title;
    private String url;
    private String category;
    private ArrayList<String> openHourAllByCol = new ArrayList<String>();
    private ArrayList<String> eachStairs = new ArrayList<String>();
    private ArrayList<String> eachStairsImg = new ArrayList<String>();
    private String openHourAllInfos;
    private String starisGuideAllInfos;
    private String getTextByCols = "div.mps tr";
    private String officeHourNotExist = "해당 개관시간이 존재하지 않습니다.";

    public LibraryInfoManager() {

    }

    public LibraryInfoManager(String url) {
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
        for (Element element:libDoc.select(getTextByCols)){
            openHourAllByCol.add(element.text());
        }

        openHourAllInfos = openHourAllByCol.toString();

        this.eachStairs = eachStairs;

        // 층별안내
        for (Element element:libDoc.select("div.mps-content img")){
            eachStairsImg.add(element.attr("src"));
        }

        starisGuideAllInfos = eachStairsImg.toString();


    }



    public String getStringResult(String libName, String classify) {
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
                        resultStr = "중앙도서관\n" + libDoc.select("div.mps tr.row-3").text();
                        break;
                    case "방학중" :
                        resultStr = "중앙도서관\n" + " 모든 요일 " +  libDoc.select("div.mps tr.row-4").text();
                        break;
                    default:
                        resultStr = officeHourNotExist;
                }
                break;
            }
            case "새벽":
            {
                temp.add("1층"); temp.add("2층");
                temp.add("3층"); temp.add("4층");
                setJsoupInfos("https://lib.pusan.ac.kr/intro/plot-plan/lib2-open-plot/",temp);
                // 개관시간
                for (Element element : libDoc.select(getTextByCols)){
                    openHourAllByCol.add(element.text());
                }
                switch(classify) {
                    case "자료실" :
                        resultStr = "새벽벌도서관\n" + openHourAllByCol.get(1) + "\n" +  openHourAllByCol.get(2);
                        break;
                    case "열람실" :
                        resultStr = "새벽벌도서관\n" + openHourAllByCol.get(3) + openHourAllByCol.get(4) + openHourAllByCol.get(5)
                        + openHourAllByCol.get(6) + openHourAllByCol.get(7);
                        break;
                    default:
                        resultStr = officeHourNotExist;
                }
                break;
            }
            case "법학":
            {
                temp.add("4층"); temp.add("5층");
                setJsoupInfos("https://lib.pusan.ac.kr/intro/plot-plan/law-open-plot/",temp);
                // 개관시간
                for (Element element : libDoc.select(getTextByCols)){
                    openHourAllByCol.add(element.text());
                }
                switch(classify) {
                    case "자료실" :
                        resultStr = "법학도서관\n" + openHourAllByCol.get(2) + "\n" + openHourAllByCol.get(3);
                        break;
                    case "열람실" :
                        resultStr = "법학도서관\n" + openHourAllByCol.get(3) + openHourAllByCol.get(4) + openHourAllByCol.get(5)
                                + openHourAllByCol.get(6) + openHourAllByCol.get(7);
                        break;
                    default:
                        resultStr = officeHourNotExist;
                }
                break;
            }
            case "미리내": {
                setJsoupInfos("https://lib.pusan.ac.kr/intro/plot-plan/archi-open-plot/", temp);
                // 개관시간
                for (Element element : libDoc.select(getTextByCols)) {
                    openHourAllByCol.add(element.text());
                    System.out.println("미리내 파싱중");
                    System.out.println(element.text());
                }
                switch (classify) {
                    case "평소":
                        resultStr = "미리내 도서관\n" + openHourAllByCol.get(1);
                        break;
                    case "시험기간":
                        resultStr = "미리내 도서관\n" + openHourAllByCol.get(2);
                        break;
                    default:
                        resultStr = officeHourNotExist;
                }
                break;
            }
            case "의생명": {
                setJsoupInfos("https://lib.pusan.ac.kr/intro/plot-plan/bio-open-plot/", temp);
                // 개관시간
                for (Element element : libDoc.select(getTextByCols)) {
                    openHourAllByCol.add(element.text());
                }
                switch (classify) {
                    case "학기중":
                        resultStr = "의생명도서관\n" + openHourAllByCol.get(1).substring(10) + "\n" + openHourAllByCol.get(1).substring(0,8)
                                + "," + openHourAllByCol.get(2) + "," + openHourAllByCol.get(3);
                        break;
                    case "방학중":
                        resultStr = "의생명도서관\n" + openHourAllByCol.get(4).substring(10) + "\n" + openHourAllByCol.get(4).substring(0,8)
                                + "," + openHourAllByCol.get(5);
                        break;
                    default:
                        resultStr = officeHourNotExist;
                }
                break;
            }
            case "나노생명": {
                setJsoupInfos("https://lib.pusan.ac.kr/intro/plot-plan/nano-open-plot/", temp);
                // 개관시간
                for (Element element : libDoc.select(getTextByCols)) {
                    openHourAllByCol.add(element.text());
                }
                switch (classify) {
                    case "자료실":
                        resultStr = "나노생명도서관\n" + openHourAllByCol.get(1);
                        break;
                    case "열람실":
                        resultStr = "나노생명도서관\n" + openHourAllByCol.get(2) + "\n" + openHourAllByCol.get(3);
                        break;
                    default:
                        resultStr = officeHourNotExist;
                }
                break;
            }
            default: System.out.println("도서관 이름이 존재하지 않습니다.");
        }

        openHourAllByCol.clear();

        return resultStr;
    }



    public JSONObject getLibraryOfficeHoursJson(String libName, String classify) throws JSONException {
        ArrayList<String> temp =  new ArrayList<String>();

        JSONObject libOfficeHour = new JSONObject();
        JSONObject libOfficeHours = new JSONObject();
        switch(libName) {
            case "중앙":
            {
                setJsoupInfos("https://lib.pusan.ac.kr/intro/plot-plan/lib1-open-plot/",temp);
                switch(classify) {
                    case "학기중" :
                        libOfficeHours.put("library", "중앙도서관");
                        libOfficeHours.put("openHour", libDoc.select("div.mps tr.row-3").text());
                        break;
                    case "방학중" :
                        libOfficeHours.put("library", "중앙도서관");
                        libOfficeHours.put("openHour", "모든 요일 " +  libDoc.select("div.mps tr.row-4").text());
                        break;
                    default:
                        libOfficeHours.put("library","Not Found");
                        libOfficeHours.put("openHour","Not Found");
                }
                break;
            }
            case "새벽":
            {
                setJsoupInfos("https://lib.pusan.ac.kr/intro/plot-plan/lib2-open-plot/",temp);
                // 개관시간
                for (Element element : libDoc.select(getTextByCols)){
                    openHourAllByCol.add(element.text());
                }
                switch(classify) {
                    case "자료실" :
                        libOfficeHours.put("library", "새벽벌도서관");
                        libOfficeHours.put("openHour", openHourAllByCol.get(1) + "\n" +  openHourAllByCol.get(2));
                        break;
                    case "열람실" :
                        libOfficeHours.put("library", "새벽벌도서관");
                        libOfficeHours.put("openHour", openHourAllByCol.get(3) + openHourAllByCol.get(4) + openHourAllByCol.get(5)
                                + openHourAllByCol.get(6) + openHourAllByCol.get(7));
                        break;
                    default:
                        libOfficeHours.put("library","Not Found");
                        libOfficeHours.put("openHour","Not Found");
                }
                break;
            }
            case "법학":
            {
                temp.add("4층"); temp.add("5층");
                setJsoupInfos("https://lib.pusan.ac.kr/intro/plot-plan/law-open-plot/",temp);
                // 개관시간
                for (Element element : libDoc.select(getTextByCols)){
                    openHourAllByCol.add(element.text());
                }
                switch(classify) {
                    case "자료실" :
                        libOfficeHours.put("library", "법학도서관");
                        libOfficeHours.put("openHour", openHourAllByCol.get(2) + "\n" +  openHourAllByCol.get(3));
                        break;
                    case "열람실" :
                        libOfficeHours.put("library", "법학도서관");
                        libOfficeHours.put("openHour", openHourAllByCol.get(3) + openHourAllByCol.get(4) + openHourAllByCol.get(5)
                                + openHourAllByCol.get(6) + openHourAllByCol.get(7));
                        break;
                    default:
                        libOfficeHours.put("library","Not Found");
                        libOfficeHours.put("openHour","Not Found");
                }
                break;
            }
            case "미리내": {
                setJsoupInfos("https://lib.pusan.ac.kr/intro/plot-plan/archi-open-plot/", temp);
                // 개관시간
                for (Element element : libDoc.select(getTextByCols)) {
                    openHourAllByCol.add(element.text());
                }
                switch (classify) {
                    case "평소":
                        libOfficeHours.put("library", "미리내 도서관");
                        libOfficeHours.put("openHour", openHourAllByCol.get(1));
                        break;
                    case "시험기간":
                        libOfficeHours.put("library", "미리내 도서관");
                        libOfficeHours.put("openHour", openHourAllByCol.get(2));
                        break;
                    default:
                        libOfficeHours.put("library","Not Found");
                        libOfficeHours.put("openHour","Not Found");
                }
                break;
            }
            case "의생명": {
                setJsoupInfos("https://lib.pusan.ac.kr/intro/plot-plan/bio-open-plot/", temp);
                // 개관시간
                for (Element element : libDoc.select(getTextByCols)) {
                    openHourAllByCol.add(element.text());
                }
                switch (classify) {
                    case "학기중":
                        libOfficeHours.put("library", "의생명도서관");
                        libOfficeHours.put("openHour", openHourAllByCol.get(1).substring(10) + "\n" + openHourAllByCol.get(1).substring(0,8)
                                + "," + openHourAllByCol.get(2) + "," + openHourAllByCol.get(3));
                        break;
                    case "방학중":
                        libOfficeHours.put("library", "의생명도서관");
                        libOfficeHours.put("openHour", openHourAllByCol.get(4).substring(10) + "\n" + openHourAllByCol.get(4).substring(0,8)
                                + "," + openHourAllByCol.get(5));
                        break;
                    default:
                        libOfficeHours.put("library","Not Found");
                        libOfficeHours.put("openHour","Not Found");
                }
                break;
            }
            case "나노생명": {
                setJsoupInfos("https://lib.pusan.ac.kr/intro/plot-plan/nano-open-plot/", temp);
                // 개관시간
                for (Element element : libDoc.select(getTextByCols)) {
                    openHourAllByCol.add(element.text());
                }
                switch (classify) {
                    case "자료실":
                        libOfficeHours.put("library", "나노생명도서관");
                        libOfficeHours.put("openHour", openHourAllByCol.get(1));
                        break;
                    case "열람실":
                        libOfficeHours.put("library", "나노생명도서관");
                        libOfficeHours.put("openHour", openHourAllByCol.get(2) + "\n" + openHourAllByCol.get(3));
                        break;
                    default:
                        libOfficeHours.put("library","Not Found");
                        libOfficeHours.put("openHour","Not Found");
                }
                break;
            }
            default: System.out.println("도서관 이름이 존재하지 않습니다.");
        }

        openHourAllByCol.clear();
        libOfficeHours.put("type","libOfficeHour");
        libOfficeHour.put("data",libOfficeHours);

        return libOfficeHour;
    }


}
