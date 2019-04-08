package com.pnu.demo.chatbot.crallwer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class RestaurantCrawler {
    private final String RESTAURANT_URL = "http://www.pusan.ac.kr/kor/CMS/MenuMgr/menuListOnWeekly.do?mCode=MN203";
    Document pnu;

    public void get(){
        try{
            pnu = Jsoup.connect(RESTAURANT_URL).get();
            Elements elements = pnu.select("table tbody tr");
            elements.forEach(element -> {
                String name = element.select("th").text();
                System.out.println(name);
                Elements menus = element.select("td");
                if(menus.text()==""|| menus.text()=="\n" || menus.text() == null){
                    System.out.println("빈칸체크");
                }
                System.out.println(menus.html());
//                menus.forEach(menu->{
//                    Elements realMenus = menu.select("li");
//                    realMenus.forEach(realMenu->{
//                        System.out.println(realMenu.text());
//                    });
//                });
            });
        } catch (Exception e){
            System.out.println(e);
        }

    }

    public void getName(){
        Elements elements = pnu.select("table tbody tr th");
        elements.forEach(element -> {
            System.out.println(element.text());
        });
    }



}
