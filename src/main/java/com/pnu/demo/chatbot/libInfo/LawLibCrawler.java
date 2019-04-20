package com.pnu.demo.chatbot.libInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class LawLibCrawler  extends LibraryCrawler{
    private Document libDoc;
    private String title;
    private ArrayList<String> openHourAllByCol = new ArrayList<String>();
    private String openHourAllInfos;
    private String stairsGuide;
    private String starisGuideAllInfos;
    private ArrayList<String> eachStairs = new ArrayList<String>();
    private ArrayList<String> eachStairsImg = new ArrayList<String>();
    private Integer eachStairsIter = -1;
    private Integer eachStairsImgIter= -1;

    public LawLibCrawler()  {
        setJsoupInfos();
    }

    public LawLibCrawler(String url) {
        super(url);
        setJsoupInfos();
    }

    private void setLibDoc(Document libDoc) {
        this.libDoc = libDoc;
    }

    private void setTitle(String title) {
        this.title = title;
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

    private void setJsoupInfos()  {

        try {
            libDoc = Jsoup.connect("https://lib.pusan.ac.kr/intro/plot-plan/law-open-plot/").get();
        }catch (IOException e) {
            System.out.println (e.toString());
        }

        title = libDoc.select("h1.entry-title").text();

        // 개관시간
        for (Element element:libDoc.select("div.mps tr")){
            openHourAllByCol.add(element.text());
        }

        openHourAllInfos = openHourAllByCol.toString();

        eachStairs.add("4층");
        eachStairs.add("5층");

        // 층별안내
        for (Element element:libDoc.select("div.mps-content img")){
            eachStairsImg.add(element.attr("src"));
        }

        starisGuideAllInfos = eachStairsImg.toString();

        // 층별안내 URL Index 초기화
        setStairsIterDefault();

    }

    public Document getLibDoc() {
        return libDoc;
    }

    public String getTitle() {
        return title;
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

    public String getResult(String paramStr) {

        String resultStr = "실패";
        switch(paramStr) {
            case "도서관 이름": resultStr  = "법학도서관";
                break;
            case "방학 유무": resultStr = "True";
                break;
            case "제목": resultStr = title;
                break;
            case "개관 시간": resultStr = openHourAllInfos;
                break;
            case "층별 안내": resultStr = starisGuideAllInfos;
                break;
            default : resultStr = "해당되는 정보가 없습니다.";
                break;
        }

        return resultStr;
    }

    public String getResult() {

        String resultStr = title + "\n개관시간" + "\n";


        // 개관시간
        for(String col : openHourAllByCol) {
            resultStr += col + "\n";
        }

        resultStr += "층별안내\n";

        // 층별안내
        for(String col : eachStairsImg) {
            resultStr += getEachStairs() + "\n";
            resultStr += col + "\n";
        }

        System.out.println(resultStr);

        return resultStr;
    }



    public static void main(String[] args) {


        LawLibCrawler lawLibCrawler = new LawLibCrawler();

        try {
            lawLibCrawler.getResult();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
