package com.pnu.demo.chatbot.hello;

import com.pnu.demo.chatbot.libInfo.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class HelloWorld implements ApplicationRunner {
    private static final Logger logger = Logger.getLogger(HelloWorld.class.getName());

    LibraryInfoManager libraryCrawler = new LibraryInfoManager();
    LibraryContactInfoManager libTelCrawler = new LibraryContactInfoManager();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO Auto-generated method stub

        System.out.println(libraryCrawler.getLibraryOfficeHoursJson("중앙","학기중"));

        System.out.println(libraryCrawler.getLibraryOfficeHoursJson("중앙","방학중"));

        System.out.println(libraryCrawler.getLibraryOfficeHoursJson("새벽","자료실"));

        System.out.println(libraryCrawler.getLibraryOfficeHoursJson("새벽","열람실"));

        System.out.println(libraryCrawler.getLibraryOfficeHoursJson("법학","자료실"));

        System.out.println(libraryCrawler.getLibraryOfficeHoursJson("법학","열람실"));

        System.out.println(libraryCrawler.getLibraryOfficeHoursJson("미리내","평소"));

        System.out.println(libraryCrawler.getLibraryOfficeHoursJson("미리내","시험기간"));

        System.out.println(libraryCrawler.getLibraryOfficeHoursJson("의생명","학기중"));

        System.out.println(libraryCrawler.getLibraryOfficeHoursJson("의생명","방학중"));

        System.out.println(libraryCrawler.getLibraryOfficeHoursJson("나노생명","자료실"));

        System.out.println(libraryCrawler.getLibraryOfficeHoursJson("나노생명","열람실"));

        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libTelCrawler.getLibTelNumsJson("부산"));

        System.out.println(libTelCrawler.getLibTelNumsJson("법학"));


        System.out.println(libTelCrawler.getLibTelNumsJson("양산"));

        System.out.println(libTelCrawler.getLibTelNumsJson("아미"));

        System.out.println(libTelCrawler.getLibTelNumsJson("밀양"));


        /*
        System.out.println(libraryCrawler.getLibraryOfficeHours("중앙","학기중"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryCrawler.getLibraryOfficeHours("중앙","방학중"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryCrawler.getLibraryOfficeHours("새벽","자료실"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryCrawler.getLibraryOfficeHours("새벽","열람실"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryCrawler.getLibraryOfficeHours("법학","자료실"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryCrawler.getLibraryOfficeHours("법학","열람실"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryCrawler.getLibraryOfficeHours("미리내","평소"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryCrawler.getLibraryOfficeHours("미리내","시험기간"));
        System.out.println();         System.out.println();         System.out.println("");

        System.out.println(libraryCrawler.getLibraryOfficeHours("의생명","학기중"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryCrawler.getLibraryOfficeHours("의생명","방학중"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryCrawler.getLibraryOfficeHours("나노생명","자료실"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryCrawler.getLibraryOfficeHours("나노생명","열람실"));
        System.out.println();         System.out.println();         System.out.println();


        System.out.println(libTelCrawler.getLibTelNums("부산"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libTelCrawler.getLibTelNums("법학"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libTelCrawler.getLibTelNums("양산"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libTelCrawler.getLibTelNums("아미"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libTelCrawler.getLibTelNums("밀양"));
        System.out.println();         System.out.println();         System.out.println();
        */

    }
}

