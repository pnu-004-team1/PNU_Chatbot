package com.pnu.demo.chatbot.libInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

enum openHourType {
    TABLE1, TABLE2, REFERENCEROOM1, READINGROOM1, READINGROOM2,
    READINGROOM3, READINGROOM4, READINGROOM5
}

enum eachStairsType {
    FIRST, SECOND, THIRD, FOURTH
}

@Component
public class DawnLibCrawler  extends LibraryCrawler{
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

    public DawnLibCrawler() throws IOException {
        setJsoupInfos();
    }


    public DawnLibCrawler(String url) throws IOException {
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

    private void setJsoupInfos() throws IOException {
        libDoc = Jsoup.connect("https://lib.pusan.ac.kr/intro/plot-plan/lib2-open-plot/").get();

        title = libDoc.select("h1.entry-title").text();

        // 개관시간
        for (Element element:libDoc.select("div.mps tr")){
            openHourAllByCol.add(element.text());
        }

        eachStairs.add("1층");
        eachStairs.add("2층");
        eachStairs.add("3층");
        eachStairs.add("4층");

        // 층별안내
        for (Element element:libDoc.select("div.mps-content img")){
            eachStairsImg.add(element.attr("src"));
        }

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

    public String getResult(String paramStr) throws IOException {

        String resultStr = "실패";

        return resultStr;
    }

    public String getResult() throws Exception {

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



    public static void main(String[] args) throws IOException {


        DawnLibCrawler dawnLibCrawler = new DawnLibCrawler();

        try {
            dawnLibCrawler.getResult();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
