package com.pnu.demo.chatbot.libInfo;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class CentralLibCrawler extends LibraryCrawler {
    private Document libDoc;
    private String title;
    private String openHour;
    private String openHourAllInfos;
    private String semesterDaysAll;
    private String semesterWeekAll;
    private String vacationDaysAll;
    private String vacationWeekAll;
    private String eachStairsAll_1;
    private String eachStairsAll_2;
    private String eachStairsAll_3;
    private String eachStairsAll_4;
    private String note;
    private String noteAllInfos;
    private String semesterDaysOpenHour;
    private String semesterWeekOpenHour;
    private String vacationDaysOpenHour;
    private String vacationWeekOpenHour;
    private String noteInfo;
    private String stairsGuide;
    private String starisGuideAllInfos;
    private ArrayList<String> eachStairs = new ArrayList<String>();
    private ArrayList<String> eachStairsImg = new ArrayList<String>();
    private Integer eachStairsIter = -1;
    private Integer eachStairsImgIter= -1;

    public CentralLibCrawler() throws IOException {
        setJsoupInfos();
    }


    public CentralLibCrawler(String url) throws IOException {
        super(url);
        libDoc = Jsoup.connect(url).get();
        setJsoupInfos();
    }

    private void setLibDoc(Document libDoc) {
        this.libDoc = libDoc;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setOpenHour(String openHour) {
        this.openHour = openHour;
    }

    private void setNote(String note) {
        this.note = note;
    }

    private void setNoteInfo(String noteInfo) {
        this.noteInfo = noteInfo;
    }

    private void setSemesterDaysOpenHour(String semesterDaysOpenHour) {
        this.semesterDaysOpenHour = semesterDaysOpenHour;
    }

    private void setSemesterWeekOpenHour(String semesterWeekOpenHour) {
        this.semesterWeekOpenHour = semesterWeekOpenHour;
    }

    private void setVacationDaysOpenHour(String vacationDaysOpenHour) {
        this.vacationDaysOpenHour = vacationDaysOpenHour;
    }

    private void setVacationWeekOpenHour(String vacationWeekOpenHour) {
        this.vacationWeekOpenHour = vacationWeekOpenHour;
    }

    private void setStairsGuide(String stairsGuide) {
        this.stairsGuide = stairsGuide;
    }

    private void setEachStairs(String eachStair) {
        this.eachStairs.add(eachStair);
    }

    private void setEachStairsImg(String eachStairImg) {
        this.eachStairsImg.add(eachStairImg);
    }

    private void setStairsIterDefault() {
        eachStairsIter = -1;
        eachStairsImgIter = -1;
    }

    private void setJsoupInfos() throws IOException {
        libDoc = Jsoup.connect("https://lib.pusan.ac.kr/intro/plot-plan/lib1-open-plot/").get();

        // 개관시간
        title = libDoc.select("h1.entry-title").text();
        openHour = libDoc.select("header.section-title").first().text();
        note = libDoc.select("tr.row-1 th.column-4").text();
        noteInfo = libDoc.select("tr.row-2 td.column-4").text();
        semesterDaysOpenHour = libDoc.select("tr.row-3 td.column-2").text();
        semesterWeekOpenHour = libDoc.select("tr.row-3 td.column-3").text();
        vacationDaysOpenHour = libDoc.select("tr.row-4 td.column-2").text();
        vacationWeekOpenHour = libDoc.select("tr.row-3 td.column-3").text();
        // 층별안내
        for (Element element:libDoc.select("h3")){
            eachStairs.add(element.text());
        }
        for (Element element:libDoc.select("h3 img")){
            eachStairsImg.add(element.attr("src"));
        }

        // 개관시간 모든 정보
        openHourAllInfos = "학기중  : " + "평일 (월~금): " + semesterDaysOpenHour + "\n토요일: " + semesterWeekOpenHour
                + "\n방학중: " + "평일 (월~금): " + vacationDaysOpenHour + "\n토요일: " + vacationWeekOpenHour;

        // 층별안내 모든 정보
        starisGuideAllInfos = "층별안내 " + " 1층: "
                + getEachStairsImg() + "\n2층 문학예술자료관: " + getEachStairsImg() + "\n3층 인문사회과학자료관: " + getEachStairsImg()
                + "\n4층 과학기술자료관: " + getEachStairsImg();

        noteAllInfos = note + ":" + noteInfo;


        // 층별안내 URL Index 초기화
        setStairsIterDefault();

    }

    public Document getLibDoc() {
        return libDoc;
    }

    public String getTitle() {
        return title;
    }

    public String getOpenHour() {
        return openHour;
    }

    public String getNote() {
        return note;
    }

    public String getNoteInfo() {
        return noteInfo;
    }

    public String getSemesterDaysOpenHour() {
        return semesterDaysOpenHour;
    }

    public String getSemesterWeekOpenHour() {
        return semesterWeekOpenHour;
    }

    public String getVacationDaysOpenHour() {
        return vacationDaysOpenHour;
    }

    public String getVacationWeekOpenHour() {
        return vacationWeekOpenHour;
    }

    public String getStairsGuide() {
        return stairsGuide;
    }

    public String getEachStairs() {
        eachStairsIter++;
        return eachStairs.get(eachStairsIter);
    }

    public String getEachStairsImg() {
        eachStairsImgIter++;
        return eachStairsImg.get(eachStairsImgIter);
    }

    public String getResult(String paramStr) throws IOException {

        String resultStr = "실패";
        switch(paramStr) {
            case "제목": resultStr = title;
                break;
            case "개관시간": resultStr = openHourAllInfos;
                break;
            case "층별안내": resultStr = starisGuideAllInfos;
                break;
        }

        return resultStr;
    }

    public String getResult() throws Exception {

        String resultStr = title + "\n" + openHourAllInfos + "\n" + starisGuideAllInfos + "\n" + noteAllInfos;


        System.out.println(resultStr);

        return resultStr;
    }



    public static void main(String[] args) throws IOException {


        CentralLibCrawler centLibCrawler = new CentralLibCrawler();

        try {
            centLibCrawler.getResult();
            System.out.print("\n\n\n");
            System.out.print(centLibCrawler.getResult("제목"));
            System.out.print("\n----------------------------\n");
            System.out.print(centLibCrawler.getResult("개관시간"));
            System.out.print("\n----------------------------\n");
            System.out.print(centLibCrawler.getResult("층별안내"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}