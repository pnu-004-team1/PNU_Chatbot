package com.pnu.demo.chatbot.restaurant;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestaurantCrawler {
    private final String RESTAURANT_URL = "http://www.pusan.ac.kr/kor/CMS/MenuMgr/menuListOnWeekly.do?mCode=MN203";
    private final String URL_ERROR_MENT = "URL 요청 오류";
    private final String[] MEAL = {"아침", "점심", "저녁"};

    private int thisMeal = 0;

    public void get() {
        try {
            Document pnuRestaurant = conntectURL();
            Elements restaurants = getRestaurant(pnuRestaurant);
            parseEachRestaurant(restaurants);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Document conntectURL() {
        try {
            Document pnu = Jsoup.connect(RESTAURANT_URL).get();
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
//                System.out.println("식당 이름 : " + restaurantName);
            restaurantMenus.forEach(restaurantMenu -> {
//                System.out.println(MEAL[thisMeal]);
                if (restaurantMenu.text().equals("")) {
//                        System.out.println("X");
                } else {
                    Elements eachMenu = restaurantMenu.select("ul li");
                    eachMenu.forEach(element -> {
//                            System.out.println(element.text());
                    });
                }
                thisMeal++;
            });
        });
        return null;
    }


}
