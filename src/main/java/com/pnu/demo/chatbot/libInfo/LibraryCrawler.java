package com.pnu.demo.chatbot.libInfo;

public class LibraryCrawler {
    private String url;
    private String category;

    enum openHourType {
        TABLE1, TABLE2, REFERENCEROOM1, READINGROOM1, READINGROOM2,
        READINGROOM3, READINGROOM4, READINGROOM5
    }

    enum eachStairsType {
        FIRST, SECOND, THIRD, FOURTH
    }

    public LibraryCrawler() {

    }

    public LibraryCrawler(String url) {
        this.url = url;
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
