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

    public String getStringResult(String restaurantName, String menu) throws NullPointerException {

        conntectURL();

        // 개관시간
        for (Element element : pnu.select("td")) {
            restaurantsAllByCol.add(element.text());
        }

        String resultStr = "";
        switch (restaurantName) {
            case "금정": {
                switch (menu) {
                    case "조식":
                        resultStr = "금정회관 교직원 식당\n" + restaurantsAllByCol.get(0) + "금정회관 학생 식당\n"
                                + restaurantsAllByCol.get(3);
                        break;
                    case "중식":
                        resultStr = "금정회관 교직원 식당\n" + restaurantsAllByCol.get(1) + "금정회관 학생 식당\n"
                                + restaurantsAllByCol.get(4);
                        break;
                    case "석식":
                        resultStr = "금정회관 교직원 식당\n" + restaurantsAllByCol.get(2) + "금정회관 학생 식당\n"
                                + restaurantsAllByCol.get(5);
                        break;
                    case "all":
                        resultStr = "금정회관 교직원 식당\n" + restaurantsAllByCol.get(0) + "\n"
                        + restaurantsAllByCol.get(1) + "\n" + restaurantsAllByCol.get(2) + "금정회관 학생 식당\n"
                        + restaurantsAllByCol.get(3) + "\n" + restaurantsAllByCol.get(4) + "\n" + restaurantsAllByCol.get(5);
                        break;
                    default:
                        resultStr = "해당 식당명이 존재하지 않습니다.";
                }
                break;
            }
            case "문창": {
                switch (menu) {
                    case "조식":
                        resultStr = "문창회관 교직원 식당\n" + restaurantsAllByCol.get(6) + "문창회관 학생 식당\n"
                                + restaurantsAllByCol.get(9);
                        break;
                    case "중식":
                        resultStr = "문창회관 교직원 식당\n" + restaurantsAllByCol.get(7) + "문창회관 학생 식당\n"
                                + restaurantsAllByCol.get(10);
                        break;
                    case "석식":
                        resultStr = "문창회관 교직원 식당\n" + restaurantsAllByCol.get(8) + "문창회관 학생 식당\n"
                                + restaurantsAllByCol.get(11);
                        break;
                    case "all":
                        resultStr = "문창회관 교직원 식당\n" + restaurantsAllByCol.get(6) + "\n"
                                + restaurantsAllByCol.get(7) + "\n" + restaurantsAllByCol.get(8) + "문창회관 학생 식당\n"
                                + restaurantsAllByCol.get(9) + "\n" + restaurantsAllByCol.get(10) + "\n" + restaurantsAllByCol.get(11);
                        break;
                    default:
                        resultStr = "해당 식당명이 존재하지 않습니다.";
                }
                break;
            }
            case "샛벌": {
                switch (menu) {
                    case "조식":
                        resultStr = "샛벌회관 식당\n" + restaurantsAllByCol.get(12);
                        break;
                    case "중식":
                        resultStr = "샛벌회관 식당\n" + restaurantsAllByCol.get(13);
                        break;
                    case "석식":
                        resultStr = "샛벌회관 식당\n" + restaurantsAllByCol.get(14);
                        break;
                    case "all":
                        resultStr = "샛벌회관 식당\n" + restaurantsAllByCol.get(12) + "\n"
                                + restaurantsAllByCol.get(13) + "\n" + restaurantsAllByCol.get(14);
                        break;
                    default:
                        resultStr = "해당 식당명이 존재하지 않습니다.";
                }
                break;
            }
            case "학생": {
                switch (menu) {
                    case "조식":
                        resultStr = "학생회관 교직원 식당\n" + restaurantsAllByCol.get(15) + "학생회관 학생 식당\n"
                                + restaurantsAllByCol.get(18);
                        break;
                    case "중식":
                        resultStr = "학생회관 교직원 식당\n" + restaurantsAllByCol.get(16) + "학생회관 학생 식당\n"
                                + restaurantsAllByCol.get(19);
                        break;
                    case "석식":
                        resultStr = "학생회관 교직원 식당\n" + restaurantsAllByCol.get(17) + "학생회관 학생 식당\n"
                                + restaurantsAllByCol.get(20);
                        break;
                    case "all":
                        resultStr = "학생회관 교직원 식당\n" + restaurantsAllByCol.get(15) + "\n"
                                + restaurantsAllByCol.get(16) + "\n" + restaurantsAllByCol.get(17) + "학생회관 학생 식당\n"
                                + restaurantsAllByCol.get(18) + "\n" + restaurantsAllByCol.get(19) + "\n" + restaurantsAllByCol.get(20);
                        break;
                    default:
                        resultStr = "해당 식당명이 존재하지 않습니다.";
                }
                break;
            }
            case "all":
                resultStr = "금정회관 교직원 식당\n" + restaurantsAllByCol.get(0) + "\n"
                        + restaurantsAllByCol.get(1) + "\n" + restaurantsAllByCol.get(2) + "금정회관 학생 식당\n"
                        + restaurantsAllByCol.get(3) + "\n" + restaurantsAllByCol.get(4) + "\n" + restaurantsAllByCol.get(5)
                        + "\n문창회관 교직원 식당\n" + restaurantsAllByCol.get(6) + "\n"
                        + restaurantsAllByCol.get(7) + "\n" + restaurantsAllByCol.get(8) + "문창회관 학생 식당\n"
                        + restaurantsAllByCol.get(9) + "\n" + restaurantsAllByCol.get(10) + "\n" + restaurantsAllByCol.get(11)
                        + "\n샛벌회관 식당\n" + restaurantsAllByCol.get(12) + "\n" + restaurantsAllByCol.get(13) + "\n" + restaurantsAllByCol.get(14)
                        + "\n학생회관 교직원 식당\n" + restaurantsAllByCol.get(15) + "\n"
                        + restaurantsAllByCol.get(16) + "\n" + restaurantsAllByCol.get(17) + "학생회관 학생 식당\n"
                        + restaurantsAllByCol.get(18) + "\n" + restaurantsAllByCol.get(19) + "\n" + restaurantsAllByCol.get(20);
                break;
            default:
                resultStr = "해당 식당명이 존재하지 않습니다.";
        }

        restaurantsAllByCol.clear();
        return resultStr;
    }

    public JSONObject getStringResultJson(String restaurantName, String menu) throws JSONException {

        conntectURL();

        // 개관시간
        for (Element element : pnu.select("td")) {
            restaurantsAllByCol.add(element.text());
        }

        JSONObject restaurantMenu = new JSONObject();
        JSONObject restaurantMenus = new JSONObject();
        switch (restaurantName) {
            case "금정": {
                switch(menu) {
                    case "조식":
                        restaurantMenus.put("GumJeongRestaurantStaff", "금정회관 교직원 식당");
                        restaurantMenus.put("GumJeongMenusStaff", restaurantsAllByCol.get(0));
                        restaurantMenus.put("GumJeongRestaurantStudent", "금정회관 학생 식당");
                        restaurantMenus.put("GumJeongMenusStudent", restaurantsAllByCol.get(3));
                        break;
                    case "중식":
                        restaurantMenus.put("GumJeongRestaurantStaff", "금정회관 교직원 식당");
                        restaurantMenus.put("GumJeongMenusStaff", restaurantsAllByCol.get(1));
                        restaurantMenus.put("GumJeongRestaurantStudent", "금정회관 학생 식당");
                        restaurantMenus.put("GumJeongMenusStudent", restaurantsAllByCol.get(4));
                        break;
                    case "석식":
                        restaurantMenus.put("GumJeongRestaurantStaff", "금정회관 교직원 식당");
                        restaurantMenus.put("GumJeongMenusStaff", restaurantsAllByCol.get(2));
                        restaurantMenus.put("GumJeongRestaurantStudent", "금정회관 학생 식당");
                        restaurantMenus.put("GumJeongMenusStudent", restaurantsAllByCol.get(5));
                        break;
                    case "all":
                        restaurantMenus.put("GumJeongRestaurantStaff", "금정회관 교직원 식당");
                        restaurantMenus.put("GumJeongMenusStaff", restaurantsAllByCol.get(0) + "\n"  + restaurantsAllByCol.get(1) + "\n"  + restaurantsAllByCol.get(2));
                        restaurantMenus.put("GumJeongRestaurantStudent", "금정회관 학생 식당");
                        restaurantMenus.put("GumJeongMenusStudent", restaurantsAllByCol.get(3) + "\n"  + restaurantsAllByCol.get(4) + "\n"  + restaurantsAllByCol.get(5));
                        break;
                    default:
                        restaurantMenus.put("NotExist", "True");
                }
            }
            case "문창": {
                switch(menu) {
                    case "조식":
                        restaurantMenus.put("MonChangRestaurantStaff", "문창회관 교직원 식당");
                        restaurantMenus.put("MonChangMenusStaff", restaurantsAllByCol.get(6));
                        restaurantMenus.put("MonChangRestaurantStudent", "문창회관 학생 식당");
                        restaurantMenus.put("MonChangMenusStudent", restaurantsAllByCol.get(9));
                        break;
                    case "중식":
                        restaurantMenus.put("MonChangRestaurantStaff", "문창회관 교직원 식당");
                        restaurantMenus.put("MonChangMenusStaff", restaurantsAllByCol.get(7));
                        restaurantMenus.put("MonChangRestaurantStudent", "문창회관 학생 식당");
                        restaurantMenus.put("MonChangMenusStudent", restaurantsAllByCol.get(10));
                        break;
                    case "석식":
                        restaurantMenus.put("MonChangRestaurantStaff", "문창회관 교직원 식당");
                        restaurantMenus.put("MonChangMenusStaff", restaurantsAllByCol.get(8));
                        restaurantMenus.put("MonChangRestaurantStudent", "문창회관 학생 식당");
                        restaurantMenus.put("MonChangMenusStudent", restaurantsAllByCol.get(11));
                        break;
                    case "all":
                        restaurantMenus.put("MonChangRestaurantStaff", "문창회관 교직원 식당");
                        restaurantMenus.put("MonChangMenusStaff", restaurantsAllByCol.get(6) + "\n"  + restaurantsAllByCol.get(7)  + "\n"  + restaurantsAllByCol.get(8));
                        restaurantMenus.put("MonChangRestaurantStudent", "문창회관 학생 식당");
                        restaurantMenus.put("MonChangMenusStudent", restaurantsAllByCol.get(9)  + "\n"  + restaurantsAllByCol.get(10)  + "\n"  + restaurantsAllByCol.get(11));
                        break;
                    default:
                        restaurantMenus.put("NotExist", "True");
                }
            }
            case "샛벌": {
                switch(menu) {
                    case "조식":
                        restaurantMenus.put("SetBelRestaurantStaff", "샛벌회관 식당");
                        restaurantMenus.put("SetBelMenusStaff", restaurantsAllByCol.get(12));
                        break;
                    case "중식":
                        restaurantMenus.put("SetBelRestaurantStaff", "샛벌회관 식당");
                        restaurantMenus.put("SetBelMenusStaff", restaurantsAllByCol.get(13));
                        break;
                    case "석식":
                        restaurantMenus.put("SetBelRestaurantStaff", "샛벌회관 식당");
                        restaurantMenus.put("SetBelMenusStaff", restaurantsAllByCol.get(14));
                        break;
                    case "all":
                        restaurantMenus.put("SetBelRestaurantStaff", "샛벌회관 식당");
                        restaurantMenus.put("SetBelMenusStaff", restaurantsAllByCol.get(12)  + "\n"  + restaurantsAllByCol.get(13)  + "\n"  + restaurantsAllByCol.get(14));
                        break;
                    default:
                        restaurantMenus.put("NotExist", "True");
                }
            }
            case "학생": {
                switch(menu) {
                    case "조식":
                        restaurantMenus.put("HakSengRestaurantStaff", "학생회관 교직원 식당");
                        restaurantMenus.put("HakSengMenusStaff", restaurantsAllByCol.get(15));
                        restaurantMenus.put("HakSengRestaurantStudent", "학생회관 학생 식당");
                        restaurantMenus.put("HakSengMenusStudent", restaurantsAllByCol.get(18));
                        break;
                    case "중식":
                        restaurantMenus.put("HakSengRestaurantStaff", "학생회관 교직원 식당");
                        restaurantMenus.put("HakSengMenusStaff", restaurantsAllByCol.get(16));
                        restaurantMenus.put("HakSengRestaurantStudent", "학생회관 학생 식당");
                        restaurantMenus.put("HakSengMenusStudent", restaurantsAllByCol.get(19));
                        break;
                    case "석식":
                        restaurantMenus.put("HakSengRestaurantStaff", "학생회관 교직원 식당");
                        restaurantMenus.put("HakSengMenusStaff", restaurantsAllByCol.get(17));
                        restaurantMenus.put("HakSengRestaurantStudent", "학생회관 학생 식당");
                        restaurantMenus.put("HakSengMenusStudent", restaurantsAllByCol.get(20));
                        break;
                    case "all":
                        restaurantMenus.put("HakSengRestaurantStaff", "학생회관 교직원 식당");
                        restaurantMenus.put("HakSengMenusStaff", restaurantsAllByCol.get(15)  + "\n"  + restaurantsAllByCol.get(16)  + "\n"  + restaurantsAllByCol.get(17));
                        restaurantMenus.put("HakSengRestaurantStudent", "학생회관 학생 식당");
                        restaurantMenus.put("HakSengMenusStudent", restaurantsAllByCol.get(18)  + "\n"  + restaurantsAllByCol.get(19)  + "\n"  + restaurantsAllByCol.get(20));
                        break;
                    default:
                        restaurantMenus.put("NotExist", "True");
                }
            }
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