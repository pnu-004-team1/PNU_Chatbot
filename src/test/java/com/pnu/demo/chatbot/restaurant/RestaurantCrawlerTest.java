package com.pnu.demo.chatbot.restaurant;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class RestaurantCrawlerTest {

    RestaurantCrawler infoManager;

    private final String RESTAURANT_URL = "http://www.pusan.ac.kr/kor/CMS/MenuMgr/menuListOnWeekly.do?mCode=MN203";
    private final String URL_ERROR_MENT = "URL 요청 오류";
    private final String[] MEAL = {"아침", "점심", "저녁"};
    private Document pnu;
    private ArrayList<String> restaurantsAllByCol = new ArrayList<String>();
    private int thisMeal = 0;

    @Before
    public void setUp() throws Exception {
        infoManager = new RestaurantCrawler ();
        conntectURL();
    }

    @Test
    public void getStringResult() {
        String expectedValue;
        String expectedValue2;
        String expectedValue3;
        String sut;
        String sut2;
        String sut3;
        String query;
        String query2;

        for (Element element : pnu.select("td")) {
            restaurantsAllByCol.add(element.text());
        }

        // given
        expectedValue = "금정회관 교직원 식당\n" + restaurantsAllByCol.get(0) + "금정회관 학생 식당\n"
                + restaurantsAllByCol.get(3);
        expectedValue2 = "금정회관 교직원 식당\n" + restaurantsAllByCol.get(1) + "금정회관 학생 식당\n"
                + restaurantsAllByCol.get(4);
        expectedValue3 = "금정회관 교직원 식당\n" + restaurantsAllByCol.get(2) + "금정회관 학생 식당\n"
                + restaurantsAllByCol.get(5);
        // when
        query = "금정";
        query2 = "조식";
        sut = infoManager.getStringResult(query, query2);
        query2 = "중식";
        sut2 = infoManager.getStringResult(query, query2);
        query2 = "석식";
        sut3 = infoManager.getStringResult(query, query2);
        // then
        assertEquals(expectedValue, sut);
        assertEquals(expectedValue2, sut2);
        assertEquals(expectedValue3, sut3);

        // given
        expectedValue = "문창회관 교직원 식당\n" + restaurantsAllByCol.get(6) + "문창회관 학생 식당\n"
                + restaurantsAllByCol.get(9);
        expectedValue2 = "문창회관 교직원 식당\n" + restaurantsAllByCol.get(7) + "문창회관 학생 식당\n"
                + restaurantsAllByCol.get(10);
        expectedValue3 = "문창회관 교직원 식당\n" + restaurantsAllByCol.get(8) + "문창회관 학생 식당\n"
                + restaurantsAllByCol.get(11);
        // when
        query = "문창";
        query2 = "조식";
        sut = infoManager.getStringResult(query, query2);
        query2 = "중식";
        sut2 = infoManager.getStringResult(query, query2);
        query2 = "석식";
        sut3 = infoManager.getStringResult(query, query2);
        // then
        assertEquals(expectedValue, sut);
        assertEquals(expectedValue2, sut2);
        assertEquals(expectedValue3, sut3);

        // given
        expectedValue = "샛벌회관 식당\n" + restaurantsAllByCol.get(12);
        expectedValue2 = "샛벌회관 식당\n" + restaurantsAllByCol.get(13);
        expectedValue3 = "샛벌회관 식당\n" + restaurantsAllByCol.get(14);
        // when
        query = "샛벌";
        query2 = "조식";
        sut = infoManager.getStringResult(query, query2);
        query2 = "중식";
        sut2 = infoManager.getStringResult(query, query2);
        query2 = "석식";
        sut3 = infoManager.getStringResult(query, query2);
        // then
        assertEquals(expectedValue, sut);
        assertEquals(expectedValue2, sut2);
        assertEquals(expectedValue3, sut3);

        // given
        expectedValue = "학생회관 교직원 식당\n" + restaurantsAllByCol.get(15) + "학생회관 학생 식당\n"
                + restaurantsAllByCol.get(18);
        expectedValue2 = "학생회관 교직원 식당\n" + restaurantsAllByCol.get(16) + "학생회관 학생 식당\n"
                + restaurantsAllByCol.get(19);
        expectedValue3 = "학생회관 교직원 식당\n" + restaurantsAllByCol.get(17) + "학생회관 학생 식당\n"
                + restaurantsAllByCol.get(20);
        // when
        query = "학생";
        query2 = "조식";
        sut = infoManager.getStringResult(query, query2);
        query2 = "중식";
        sut2 = infoManager.getStringResult(query, query2);
        query2 = "석식";
        sut3 = infoManager.getStringResult(query, query2);
        // then
        assertEquals(expectedValue, sut);
        assertEquals(expectedValue2, sut2);
        assertEquals(expectedValue3, sut3);

        // given
        expectedValue = "금정회관 교직원 식당\n" + restaurantsAllByCol.get(0) + "\n"
                + restaurantsAllByCol.get(1) + "\n" + restaurantsAllByCol.get(2) + "금정회관 학생 식당\n"
                + restaurantsAllByCol.get(3) + "\n" + restaurantsAllByCol.get(4) + "\n" + restaurantsAllByCol.get(5)
                + "\n문창회관 교직원 식당\n" + restaurantsAllByCol.get(6) + "\n"
                + restaurantsAllByCol.get(7) + "\n" + restaurantsAllByCol.get(8) + "문창회관 학생 식당\n"
                + restaurantsAllByCol.get(9) + "\n" + restaurantsAllByCol.get(10) + "\n" + restaurantsAllByCol.get(11)
                + "\n샛벌회관 식당\n" + restaurantsAllByCol.get(12) + "\n" + restaurantsAllByCol.get(13) + "\n" + restaurantsAllByCol.get(14)
                + "\n학생회관 교직원 식당\n" + restaurantsAllByCol.get(15) + "\n"
                + restaurantsAllByCol.get(16) + "\n" + restaurantsAllByCol.get(17) + "학생회관 학생 식당\n"
                + restaurantsAllByCol.get(18) + "\n" + restaurantsAllByCol.get(19) + "\n" + restaurantsAllByCol.get(20);
        // when
        query = "all";
        query2 = "all";
        sut = infoManager.getStringResult(query, query2);
        // then
        assertEquals(expectedValue, sut);
    }

    @Test
    public void getStringResultJson() {
        // TODO
        
    }


    private Document conntectURL() {
        try {
            pnu = Jsoup.connect(RESTAURANT_URL).get();
            return pnu;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(URL_ERROR_MENT);
        }
    }

    private Elements getRestaurant(Document document) {
        Elements elements = document.select("table tbody tr");
        return elements;
    }

    private Elements parseEachRestaurant(Elements restaurants) {

        restaurants.forEach(restaurant -> {
            thisMeal = 0;
            String restaurantName = restaurant.select("th").text(); // 식당 이름
            Elements restaurantMenus = restaurant.select("td");
            System.out.println("식당 이름 : " + restaurantName);
            restaurantMenus.forEach(restaurantMenu -> {
                System.out.println(MEAL[thisMeal]);
                if (restaurantMenu.text().equals("")) {
                    System.out.println("X");
                } else {
                    Elements eachMenu = restaurantMenu.select("ul li");
                    eachMenu.forEach(element -> {
                        System.out.println(element.text());
                    });
                }
                thisMeal++;
            });
        });
        return null;
    }
}