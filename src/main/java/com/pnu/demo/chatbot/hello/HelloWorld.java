package com.pnu.demo.chatbot.hello;

import com.pnu.demo.chatbot.crallwer.RestaurantCrawler;
import com.pnu.demo.chatbot.libInfo.CentralLibCrawler;
import com.pnu.demo.chatbot.libInfo.LibraryCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class HelloWorld implements ApplicationRunner {
    private static final Logger logger = Logger.getLogger(HelloWorld.class.getName());

    @Autowired
    CentralLibCrawler centralLibCrawler;
    @Override
    public void run(ApplicationArguments args) throws Exception {
            // TODO Auto-generated method stub
            centralLibCrawler.getResult();
    }
}
