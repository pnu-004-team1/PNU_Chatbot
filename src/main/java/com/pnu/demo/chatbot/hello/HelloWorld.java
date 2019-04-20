package com.pnu.demo.chatbot.hello;

import com.pnu.demo.chatbot.libInfo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;
import java.io.IOException;

@Component
public class HelloWorld implements ApplicationRunner {
    private static final Logger logger = Logger.getLogger(HelloWorld.class.getName());

    CentralLibCrawler centralLibCrawler = new CentralLibCrawler();
    DawnLibCrawler dawnLibCrawler = new DawnLibCrawler();
    LawLibCrawler lawLibCrawler = new LawLibCrawler();
    @Override
    public void run(ApplicationArguments args) throws Exception {
            // TODO Auto-generated method stub
       lawLibCrawler.getResult();
       System.out.println("\n\n\n");

        System.out.println(centralLibCrawler.getResult("도서관 이름"));
        System.out.println(centralLibCrawler.getResult("방학 유무"));
        System.out.println(centralLibCrawler.getResult("제목"));
        System.out.println(centralLibCrawler.getResult("개관 시간"));
        System.out.println(centralLibCrawler.getResult("층별 안내"));
        System.out.println(dawnLibCrawler.getResult("도서관 이름"));
        System.out.println(dawnLibCrawler.getResult("방학 유무"));
        System.out.println(dawnLibCrawler.getResult("제목"));
        System.out.println(dawnLibCrawler.getResult("개관 시간"));
        System.out.println(dawnLibCrawler.getResult("층별 안내"));
        System.out.println(lawLibCrawler.getResult("도서관 이름"));
        System.out.println(lawLibCrawler.getResult("방학 유무"));
        System.out.println(lawLibCrawler.getResult("제목"));
        System.out.println(lawLibCrawler.getResult("개관 시간"));
        System.out.println(lawLibCrawler.getResult("층별 안내"));

    }
}
