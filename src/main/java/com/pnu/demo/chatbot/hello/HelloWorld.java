package com.pnu.demo.chatbot.hello;

import com.pnu.demo.chatbot.libInfo.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class HelloWorld implements ApplicationRunner {
    private static final Logger logger = Logger.getLogger(HelloWorld.class.getName());

    LibraryCrawler libraryCrawler = new LibraryCrawler();

    @Override
    public void run(ApplicationArguments args) throws Exception {
            // TODO Auto-generated method stub
        System.out.println(libraryCrawler.getLibraryOfficeHours("미리내","시험기간"));

    }
}
