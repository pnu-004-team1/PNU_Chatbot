package com.pnu.demo.chatbot.restaurant;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class RestaurantCrawler {
    private final String RESTAURANT_URL = "http://www.pusan.ac.kr/kor/CMS/MenuMgr/menuListOnWeekly.do?mCode=MN203";
    private final String URL_ERROR_MENT = "URL 요청 오류";
    private final String[] MEAL = {"아침", "점심", "저녁"};
    private Document pnu;
    private ArrayList<String> restaurantsAllByCol = new ArrayList<String>();
    private int thisMeal = 0;

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

    public String getStringResult(String restaurantName) {

        conntectURL();

        // 개관시간
        for (Element element : pnu.select("table tbody tr")) {
            restaurantsAllByCol.add(element.text());
        }

        String resultStr = "";
        switch (restaurantName) {
            case "금정":
                resultStr = restaurantsAllByCol.get(0) + "\n" + restaurantsAllByCol.get(1);
                break;
            case "문창":
                resultStr = restaurantsAllByCol.get(2) + "\n" + restaurantsAllByCol.get(3);
                break;
            case "샛벌":
                resultStr = restaurantsAllByCol.get(4);
                break;
            case "학생":
                resultStr = restaurantsAllByCol.get(5) + "\n" + restaurantsAllByCol.get(6);
                break;
            default:
                resultStr = "해당 식당이 존재하지 않습니다";
        }

        restaurantsAllByCol.clear();
        return resultStr;
    }

    public JSONObject getStringResultJson(String restaurantName) throws JSONException {

        conntectURL();

        // 개관시간
        for (Element element : pnu.select("td")) {
            restaurantsAllByCol.add(element.text());
        }

        JSONObject restaurantMenu = new JSONObject();
        JSONObject restaurantMenus = new JSONObject();
        switch (restaurantName) {
            case "금정":
                restaurantMenus.put("restaurantStaff", "금정회관 교직원 식당");
                restaurantMenus.put("menusStaff", restaurantsAllByCol.get(0)  + restaurantsAllByCol.get(1) + restaurantsAllByCol.get(2));
                restaurantMenus.put("restaurantStudent", "금정회관 학생 식당");
                restaurantMenus.put("menusStudent", restaurantsAllByCol.get(3) + restaurantsAllByCol.get(4) + restaurantsAllByCol.get(5));
                break;
            case "문창":
                restaurantMenus.put("restaurantStaff", "문창회관 교직원 식당");
                restaurantMenus.put("menusStaff", restaurantsAllByCol.get(6) + restaurantsAllByCol.get(7)  + restaurantsAllByCol.get(8));
                restaurantMenus.put("restaurantStudent", "문창회관 학생 식당");
                restaurantMenus.put("menusStudent", restaurantsAllByCol.get(9)  + restaurantsAllByCol.get(10)  + restaurantsAllByCol.get(11));
                break;
            case "샛벌":
                restaurantMenus.put("restaurantStaff", "샛벌회관 교직원 식당");
                restaurantMenus.put("menusStaff", restaurantsAllByCol.get(12)  + restaurantsAllByCol.get(13)  + restaurantsAllByCol.get(14));
                break;
            case "학생":
                restaurantMenus.put("restaurantStaff", "학생회관 교직원 식당");
                restaurantMenus.put("menusStaff", restaurantsAllByCol.get(15)  + restaurantsAllByCol.get(16)  + restaurantsAllByCol.get(17));
                restaurantMenus.put("restaurantStudent", "학생회관 학생 식당");
                restaurantMenus.put("menusStudent", restaurantsAllByCol.get(18)  + restaurantsAllByCol.get(19)  + restaurantsAllByCol.get(20));
                break;
            default:
                restaurantMenus.put("restaurant", "해당 식당이 존재하지 않습니다");
        }

        restaurantsAllByCol.clear();
        restaurantMenus.put("type", "restaurantMenu");
        restaurantMenu.put("data", restaurantMenus);

        return restaurantMenu;
    }
}