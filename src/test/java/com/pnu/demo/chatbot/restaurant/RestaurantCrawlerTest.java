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
        String sut;
        String query;

        for (Element element : pnu.select("table tbody tr")) {
            restaurantsAllByCol.add(element.text());
        }

        // given
        expectedValue = restaurantsAllByCol.get(0) + "\n" + restaurantsAllByCol.get(1);
        // when
        query = "금정";
        sut = infoManager.getStringResult(query);
        // then
        assertEquals(expectedValue, sut);

        // given
        expectedValue = restaurantsAllByCol.get(2) + "\n" + restaurantsAllByCol.get(3);
        // when
        query = "문창";
        sut = infoManager.getStringResult(query);
        // then
        assertEquals(expectedValue, sut);

        // given
        expectedValue = restaurantsAllByCol.get(4);
        // when
        query = "샛벌";
        sut = infoManager.getStringResult(query);
        // then
        assertEquals(expectedValue, sut);

        // given
        expectedValue = restaurantsAllByCol.get(5) + "\n" + restaurantsAllByCol.get(6);
        // when
        query = "학생";
        sut = infoManager.getStringResult(query);
        // then
        assertEquals(expectedValue, sut);

        // given
        expectedValue = restaurantsAllByCol.get(0) + "\n" + restaurantsAllByCol.get(1) + restaurantsAllByCol.get(2) + "\n" + restaurantsAllByCol.get(3)
                + restaurantsAllByCol.get(4) + "\n" + restaurantsAllByCol.get(5) + "\n" + restaurantsAllByCol.get(6);
        // when
        query = "나머지";
        sut = infoManager.getStringResult(query);
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