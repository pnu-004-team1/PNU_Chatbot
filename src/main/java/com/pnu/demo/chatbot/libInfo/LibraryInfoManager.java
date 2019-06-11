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



    public String getStringResult(String libName, String classify) throws NullPointerException {
        ArrayList<String> temp =  new ArrayList<String>();

        if (libName == null) libName = "all";
        else if(classify == null) classify = "all";
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
                    case "all" :
                        resultStr = "중앙도서관\n" + libDoc.select("div.mps tr.row-3").text() + "\n모든 요일 " +  libDoc.select("div.mps tr.row-4").text();
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
                    case "all" :
                        resultStr = "새벽벌도서관\n" + openHourAllByCol.get(1) + "\n" +  openHourAllByCol.get(2) + "\n"
                                + openHourAllByCol.get(3) + openHourAllByCol.get(4) + openHourAllByCol.get(5)
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
                    case "all" :
                        resultStr = "법학도서관\n" + openHourAllByCol.get(2) + "\n" + openHourAllByCol.get(3) + "\n" +
                                openHourAllByCol.get(3) + openHourAllByCol.get(4) + openHourAllByCol.get(5)
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
                }
                switch (classify) {
                    case "평소":
                        resultStr = "미리내 도서관\n" + openHourAllByCol.get(1);
                        break;
                    case "시험기간":
                        resultStr = "미리내 도서관\n" + openHourAllByCol.get(2);
                        break;
                    case "all" :
                        resultStr = "미리내 도서관\n" + openHourAllByCol.get(1) + "\n" + openHourAllByCol.get(2);
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
                    case "all" :
                        resultStr = "의생명도서관\n" + openHourAllByCol.get(1).substring(10) + "\n" + openHourAllByCol.get(1).substring(0,8)
                                + "," + openHourAllByCol.get(2) + "," + openHourAllByCol.get(3) + "\n" + openHourAllByCol.get(4).substring(10) + "\n"
                                + openHourAllByCol.get(4).substring(0,8)  + "," + openHourAllByCol.get(5);
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
                    case "all" :
                        resultStr = "나노생명도서관\n" + openHourAllByCol.get(1) + "\n" + openHourAllByCol.get(2) + "\n" + openHourAllByCol.get(3);
                        break;
                    default:
                        resultStr = officeHourNotExist;
                }
                break;
            }
            case "all" :
                resultStr = "중앙도서관\n" + libDoc.select("div.mps tr.row-3").text() + "\n모든 요일 " +  libDoc.select("div.mps tr.row-4").text()
                        + "\n새벽벌도서관\n" + openHourAllByCol.get(1) + "\n" +  openHourAllByCol.get(2) + "\n" + openHourAllByCol.get(3) + openHourAllByCol.get(4) + openHourAllByCol.get(5)
                        + openHourAllByCol.get(6) + openHourAllByCol.get(7) + "\n법학도서관\n" + openHourAllByCol.get(2) + "\n" + openHourAllByCol.get(3) + "\n" +
                        openHourAllByCol.get(3) + openHourAllByCol.get(4) + openHourAllByCol.get(5)
                        + openHourAllByCol.get(6) + openHourAllByCol.get(7) + "\n미리내 도서관\n" + openHourAllByCol.get(1) + "\n" + openHourAllByCol.get(2)
                        + "\n의생명도서관\n" + openHourAllByCol.get(1).substring(10) + "\n" + openHourAllByCol.get(1).substring(0,8)
                        + "," + openHourAllByCol.get(2) + "," + openHourAllByCol.get(3) + "\n" + openHourAllByCol.get(4).substring(10) + "\n"
                        + openHourAllByCol.get(4).substring(0,8)  + "," + openHourAllByCol.get(5)
                        + "\n나노생명도서관\n" + openHourAllByCol.get(1) + "\n" + openHourAllByCol.get(2) + "\n" + openHourAllByCol.get(3);
                break;
            default: resultStr = "도서관 이름이 존재하지 않습니다.";
        }

        openHourAllByCol.clear();

        return resultStr;
    }



    public JSONObject getStringResultJson(String libName, String classify) throws JSONException {
        ArrayList<String> temp =  new ArrayList<String>();

        JSONObject libOfficeHour = new JSONObject();
        JSONObject libOfficeHours = new JSONObject();
        if (libName == null) libName = "all";
        else if(classify == null) classify = "all";
        switch(libName) {
            case "중앙":
            {
                setJsoupInfos("https://lib.pusan.ac.kr/intro/plot-plan/lib1-open-plot/",temp);
                switch(classify) {
                    case "학기중" :
                        libOfficeHours.put("centLibrary", "중앙도서관");
                        libOfficeHours.put("centSemesterOpenHour", libDoc.select("div.mps tr.row-3").text());
                        break;
                    case "방학중" :
                        libOfficeHours.put("centLibrary", "중앙도서관");
                        libOfficeHours.put("centVacationOpenHour", "모든 요일 " +  libDoc.select("div.mps tr.row-4").text());
                        break;
                    case "all":
                        libOfficeHours.put("centLibrary", "중앙도서관");
                        libOfficeHours.put("centSemesterOpenHour", libDoc.select("div.mps tr.row-3").text());
                        libOfficeHours.put("centVacationOpenHour", "모든 요일 " +  libDoc.select("div.mps tr.row-4").text());
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
                        libOfficeHours.put("saebyukLibrary", "새벽벌도서관");
                        libOfficeHours.put("saebyukDataOpenHour", openHourAllByCol.get(1) + "\n" +  openHourAllByCol.get(2));
                        break;
                    case "열람실" :
                        libOfficeHours.put("saebyukLibrary", "새벽벌도서관");
                        libOfficeHours.put("saebyukReadingOpenHour", openHourAllByCol.get(3) + openHourAllByCol.get(4) + openHourAllByCol.get(5)
                                + openHourAllByCol.get(6) + openHourAllByCol.get(7));
                        break;
                    case "all":
                        libOfficeHours.put("saebyukLibrary", "새벽벌도서관");
                        libOfficeHours.put("saebyukDataOpenHour", openHourAllByCol.get(1) + "\n" +  openHourAllByCol.get(2));
                        libOfficeHours.put("saebyukReadingOpenHour", openHourAllByCol.get(3) + openHourAllByCol.get(4) + openHourAllByCol.get(5)
                                + openHourAllByCol.get(6) + openHourAllByCol.get(7));
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
                        libOfficeHours.put("lawLibrary", "법학도서관");
                        libOfficeHours.put("lawDataOpenHour", openHourAllByCol.get(2) + "\n" +  openHourAllByCol.get(3));
                        break;
                    case "열람실" :
                        libOfficeHours.put("lawLibrary", "법학도서관");
                        libOfficeHours.put("lawReadingOpenHour", openHourAllByCol.get(3) + openHourAllByCol.get(4) + openHourAllByCol.get(5)
                                + openHourAllByCol.get(6) + openHourAllByCol.get(7));
                        break;
                    case "all":
                        libOfficeHours.put("lawLibrary", "법학도서관");
                        libOfficeHours.put("lawDataOpenHour", openHourAllByCol.get(2) + "\n" +  openHourAllByCol.get(3));
                        libOfficeHours.put("lawReadingOpenHour", openHourAllByCol.get(3) + openHourAllByCol.get(4) + openHourAllByCol.get(5)
                                + openHourAllByCol.get(6) + openHourAllByCol.get(7));
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
                        libOfficeHours.put("mirinaeLibrary", "미리내 도서관");
                        libOfficeHours.put("mirinaeUsualOpenHour", openHourAllByCol.get(1));
                        break;
                    case "시험기간":
                        libOfficeHours.put("mirinaeLibrary", "미리내 도서관");
                        libOfficeHours.put("mirinaeExamOpenHour", openHourAllByCol.get(2));
                        break;
                    case "all":
                        libOfficeHours.put("mirinaeLibrary", "미리내 도서관");
                        libOfficeHours.put("mirinaeUsualOpenHour", openHourAllByCol.get(1));
                        libOfficeHours.put("mirinaeExamOpenHour", openHourAllByCol.get(2));
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
                        libOfficeHours.put("bioLibrary", "의생명도서관");
                        libOfficeHours.put("bioSemesterOpenHour", openHourAllByCol.get(1).substring(10) + "\n" + openHourAllByCol.get(1).substring(0,8)
                                + "," + openHourAllByCol.get(2) + "," + openHourAllByCol.get(3));
                        break;
                    case "방학중":
                        libOfficeHours.put("bioLibrary", "의생명도서관");
                        libOfficeHours.put("bioVacationOpenHour", openHourAllByCol.get(4).substring(10) + "\n" + openHourAllByCol.get(4).substring(0,8)
                                + "," + openHourAllByCol.get(5));
                        break;
                    case "all":
                        libOfficeHours.put("bioLibrary", "의생명도서관");
                        libOfficeHours.put("bioSemesterOpenHour", openHourAllByCol.get(1).substring(10) + "\n" + openHourAllByCol.get(1).substring(0,8)
                                + "," + openHourAllByCol.get(2) + "," + openHourAllByCol.get(3));
                        libOfficeHours.put("bioVacationOpenHour", openHourAllByCol.get(4).substring(10) + "\n" + openHourAllByCol.get(4).substring(0,8)
                                + "," + openHourAllByCol.get(5));
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
                        libOfficeHours.put("nanoLibrary", "나노생명도서관");
                        libOfficeHours.put("nanoDataOpenHour", openHourAllByCol.get(1));
                        break;
                    case "열람실":
                        libOfficeHours.put("nanoLibrary", "나노생명도서관");
                        libOfficeHours.put("nanoReadingOpenHour", openHourAllByCol.get(2) + "\n" + openHourAllByCol.get(3));
                        break;
                    case "all":
                        libOfficeHours.put("nanoLibrary", "나노생명도서관");
                        libOfficeHours.put("nanoDataOpenHour", openHourAllByCol.get(1));
                        libOfficeHours.put("nanoReadingOpenHour", openHourAllByCol.get(2) + "\n" + openHourAllByCol.get(3));
                    default:
                        libOfficeHours.put("library","Not Found");
                        libOfficeHours.put("openHour","Not Found");
                }
                break;
            }
            case "all":
                libOfficeHours.put("centLibrary", "중앙도서관");
                libOfficeHours.put("centSemesterOpenHour", libDoc.select("div.mps tr.row-3").text());
                libOfficeHours.put("centVacationOpenHour", "모든 요일 " +  libDoc.select("div.mps tr.row-4").text());
                libOfficeHours.put("saebyukLibrary", "새벽벌도서관");
                libOfficeHours.put("saebyukDataOpenHour", openHourAllByCol.get(1) + "\n" +  openHourAllByCol.get(2));
                libOfficeHours.put("saebyukReadingOpenHour", openHourAllByCol.get(3) + openHourAllByCol.get(4) + openHourAllByCol.get(5)
                        + openHourAllByCol.get(6) + openHourAllByCol.get(7));
                libOfficeHours.put("lawLibrary", "법학도서관");
                libOfficeHours.put("lawDataOpenHour", openHourAllByCol.get(2) + "\n" +  openHourAllByCol.get(3));
                libOfficeHours.put("lawReadingOpenHour", openHourAllByCol.get(3) + openHourAllByCol.get(4) + openHourAllByCol.get(5)
                        + openHourAllByCol.get(6) + openHourAllByCol.get(7));
                libOfficeHours.put("mirinaeLibrary", "미리내 도서관");
                libOfficeHours.put("mirinaeUsualOpenHour", openHourAllByCol.get(1));
                libOfficeHours.put("mirinaeExamOpenHour", openHourAllByCol.get(2));
                libOfficeHours.put("bioLibrary", "의생명도서관");
                libOfficeHours.put("bioSemesterOpenHour", openHourAllByCol.get(1).substring(10) + "\n" + openHourAllByCol.get(1).substring(0,8)
                        + "," + openHourAllByCol.get(2) + "," + openHourAllByCol.get(3));
                libOfficeHours.put("bioVacationOpenHour", openHourAllByCol.get(4).substring(10) + "\n" + openHourAllByCol.get(4).substring(0,8)
                        + "," + openHourAllByCol.get(5));
                libOfficeHours.put("nanoLibrary", "나노생명도서관");
                libOfficeHours.put("nanoDataOpenHour", openHourAllByCol.get(1));
                libOfficeHours.put("nanoReadingOpenHour", openHourAllByCol.get(2) + "\n" + openHourAllByCol.get(3));
                break;
            default: libOfficeHours.put("library","도서관 이름이 존재하지 않습니다.");
        }

        openHourAllByCol.clear();
        libOfficeHours.put("type","libOfficeHour");
        libOfficeHour.put("data",libOfficeHours);

        return libOfficeHour;
    }


}
