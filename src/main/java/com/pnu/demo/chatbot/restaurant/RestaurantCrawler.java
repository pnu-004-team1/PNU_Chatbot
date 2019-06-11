package com.pnu.demo.chatbot.restaurant;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class RestaurantCrawler {
    private final String RESTAURANT_URL = "http://www.pusan.ac.kr/kor/CMS/MenuMgr/menuListOnWeekly.do?mCode=MN203";
    private final String URL_ERROR_MENT = "URL 요청 오류";
    private Document pnu;
    private ArrayList<String> restaurantsAllByCol = new ArrayList<String>();

    private Document conntectURL() {
        try {
            pnu = Jsoup.connect(RESTAURANT_URL).get();
            return pnu;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(URL_ERROR_MENT);
        }
    }

    public String getStringResult(String restaurantName) throws NullPointerException {

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
            case "all":
                resultStr = restaurantsAllByCol.get(0) + "\n" + restaurantsAllByCol.get(1) + restaurantsAllByCol.get(2) + "\n" + restaurantsAllByCol.get(3)
                        + restaurantsAllByCol.get(4) + "\n" + restaurantsAllByCol.get(5) + "\n" + restaurantsAllByCol.get(6);
                break;
            default:
                resultStr = "해당 식당명이 존재하지 않습니다.";
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
                restaurantMenus.put("GumJeongRestaurantStaff", "금정회관 교직원 식당");
                restaurantMenus.put("GumJeongMenusStaff", restaurantsAllByCol.get(0) + "\n"  + restaurantsAllByCol.get(1) + "\n"  + restaurantsAllByCol.get(2));
                restaurantMenus.put("GumJeongRestaurantStudent", "금정회관 학생 식당");
                restaurantMenus.put("GumJeongMenusStudent", restaurantsAllByCol.get(3) + "\n"  + restaurantsAllByCol.get(4) + "\n"  + restaurantsAllByCol.get(5));
                break;
            case "문창":
                restaurantMenus.put("MonChangRestaurantStaff", "문창회관 교직원 식당");
                restaurantMenus.put("MonChangMenusStaff", restaurantsAllByCol.get(6) + "\n"  + restaurantsAllByCol.get(7)  + "\n"  + restaurantsAllByCol.get(8));
                restaurantMenus.put("MonChangRestaurantStudent", "문창회관 학생 식당");
                restaurantMenus.put("MonChangMenusStudent", restaurantsAllByCol.get(9)  + "\n"  + restaurantsAllByCol.get(10)  + "\n"  + restaurantsAllByCol.get(11));
                break;
            case "샛벌":
                restaurantMenus.put("SetBelRestaurantStaff", "샛벌회관 교직원 식당");
                restaurantMenus.put("SetBelMenusStaff", restaurantsAllByCol.get(12)  + "\n"  + restaurantsAllByCol.get(13)  + "\n"  + restaurantsAllByCol.get(14));
                break;
            case "학생":
                restaurantMenus.put("HakSengRestaurantStaff", "학생회관 교직원 식당");
                restaurantMenus.put("HakSengMenusStaff", restaurantsAllByCol.get(15)  + "\n"  + restaurantsAllByCol.get(16)  + "\n"  + restaurantsAllByCol.get(17));
                restaurantMenus.put("HakSengRestaurantStudent", "학생회관 학생 식당");
                restaurantMenus.put("HakSengMenusStudent", restaurantsAllByCol.get(18)  + "\n"  + restaurantsAllByCol.get(19)  + "\n"  + restaurantsAllByCol.get(20));
                break;
            case "all":
                restaurantMenus.put("GumJeongRestaurantStaff", "금정회관 교직원 식당");
                restaurantMenus.put("GumJeongMenusStaff", restaurantsAllByCol.get(0)  + "\n"  + restaurantsAllByCol.get(1) + "\n"  + restaurantsAllByCol.get(2));
                restaurantMenus.put("GumJeongRestaurantStudent", "금정회관 학생 식당");
                restaurantMenus.put("GumJeongMenusStudent", restaurantsAllByCol.get(3) + "\n"  + restaurantsAllByCol.get(4) + "\n"  + restaurantsAllByCol.get(5));
                restaurantMenus.put("MonChangRestaurantStaff", "문창회관 교직원 식당");
                restaurantMenus.put("MonChangMenusStaff", restaurantsAllByCol.get(6) + "\n"  + restaurantsAllByCol.get(7)  + "\n"  + restaurantsAllByCol.get(8));
                restaurantMenus.put("MonChangRestaurantStudent", "문창회관 학생 식당");
                restaurantMenus.put("MonChangMenusStudent", restaurantsAllByCol.get(9)  + "\n"  + restaurantsAllByCol.get(10)  + "\n"  + restaurantsAllByCol.get(11));
                restaurantMenus.put("SetBelRestaurantStaff", "샛벌회관 교직원 식당");
                restaurantMenus.put("SetBelMenusStaff", restaurantsAllByCol.get(12)  + "\n"  + restaurantsAllByCol.get(13)  + "\n"  + restaurantsAllByCol.get(14));
                restaurantMenus.put("HakSengRestaurantStaff", "학생회관 교직원 식당");
                restaurantMenus.put("HakSengMenusStaff", restaurantsAllByCol.get(15)  + "\n"  + restaurantsAllByCol.get(16)  + "\n"  + restaurantsAllByCol.get(17));
                restaurantMenus.put("HakSengRestaurantStudent", "학생회관 학생 식당");
                restaurantMenus.put("HakSengMenusStudent", restaurantsAllByCol.get(18)  + "\n"  + restaurantsAllByCol.get(19)  + "\n"  + restaurantsAllByCol.get(20));
                break;
            default:
                restaurantMenus.put("NotExist", "True");
        }

        restaurantsAllByCol.clear();
        restaurantMenus.put("type", "restaurantMenu");
        restaurantMenu.put("data", restaurantMenus);

        return restaurantMenu;
    }
}