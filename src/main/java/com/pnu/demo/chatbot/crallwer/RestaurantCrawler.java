package com.pnu.demo.chatbot.crallwer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class RestaurantCrawler {
    private final String RESTAURANT_URL = "http://www.pusan.ac.kr/kor/CMS/MenuMgr/menuListOnWeekly.do?mCode=MN203";
    private final String PATH = "=======================================================";
    private final String[] MEAL = {"아침", "점심", "저녁"};
    private Document pnu;
    private int thisMeal = 0;

    public void get() {
        try {
            pnu = Jsoup.connect(RESTAURANT_URL).get();
            Elements restaurants = getRestaurant();
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
                    System.out.println();
                });
                System.out.println();
                System.out.println();
            });
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public Elements getRestaurant() {
        Elements elements = pnu.select("table tbody tr");
        return elements;
    }


}
