package com.pnu.demo.chatbot.hello;

import com.pnu.demo.chatbot.libInfo.*;
import com.pnu.demo.chatbot.similarCmd.SimilarCmd;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class HelloWorld implements ApplicationRunner {
    private static final Logger logger = Logger.getLogger(HelloWorld.class.getName());

    LibraryInfoManager libraryInfoManager = new LibraryInfoManager();
    LibraryContactInfoManager libraryContactInfoManager = new LibraryContactInfoManager();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO Auto-generated method stub


        System.out.println(libraryInfoManager.getStringResultJson("중앙","학기중"));

        System.out.println(libraryInfoManager.getStringResultJson("중앙","방학중"));

        System.out.println(libraryInfoManager.getStringResultJson("새벽","자료실"));

        System.out.println(libraryInfoManager.getStringResultJson("새벽","열람실"));

        System.out.println(libraryInfoManager.getStringResultJson("법학","자료실"));

        System.out.println(libraryInfoManager.getStringResultJson("법학","열람실"));

        System.out.println(libraryInfoManager.getStringResultJson("미리내","평소"));

        System.out.println(libraryInfoManager.getStringResultJson("미리내","시험기간"));

        System.out.println(libraryInfoManager.getStringResultJson("의생명","학기중"));

        System.out.println(libraryInfoManager.getStringResultJson("의생명","방학중"));

        System.out.println(libraryInfoManager.getStringResultJson("나노생명","자료실"));

        System.out.println(libraryInfoManager.getStringResultJson("나노생명","열람실"));

        System.out.println();         System.out.println();         System.out.println();


        System.out.println(libraryContactInfoManager.getStringResultJson("부산"));

        System.out.println(libraryContactInfoManager.getStringResultJson("법학"));


        System.out.println(libraryContactInfoManager.getStringResultJson("양산"));

        System.out.println(libraryContactInfoManager.getStringResultJson("아미"));

        System.out.println(libraryContactInfoManager.getStringResultJson("밀양"));


        /*
        System.out.println(libraryInfoManager.getStringResult("중앙","학기중"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryInfoManager.getStringResult("중앙","방학중"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryInfoManager.getStringResult("새벽","자료실"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryInfoManager.getStringResult("새벽","열람실"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryInfoManager.getStringResult("법학","자료실"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryInfoManager.getStringResult("법학","열람실"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryInfoManager.getStringResult("미리내","평소"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryInfoManager.getStringResult("미리내","시험기간"));
        System.out.println();         System.out.println();         System.out.println("");

        System.out.println(libraryInfoManager.getStringResult("의생명","학기중"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryInfoManager.getStringResult("의생명","방학중"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryInfoManager.getStringResult("나노생명","자료실"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryInfoManager.getStringResult("나노생명","열람실"));
        System.out.println();         System.out.println();         System.out.println();
        */

        /*
        System.out.println(libraryContactInfoManager.getStringResult("부산"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryContactInfoManager.getStringResult("법학"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryContactInfoManager.getStringResult("양산"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryContactInfoManager.getStringResult("아미"));
        System.out.println();         System.out.println();         System.out.println();

        System.out.println(libraryContactInfoManager.getStringResult("밀양"));
        System.out.println();         System.out.println();         System.out.println();
        */

    }
}

