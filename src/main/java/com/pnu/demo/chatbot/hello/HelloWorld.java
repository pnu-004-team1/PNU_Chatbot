package com.pnu.demo.chatbot.hello;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class HelloWorld implements ApplicationRunner {
    private static final Logger logger = Logger.getLogger(HelloWorld.class.getName());

    @Override
    public void run(ApplicationArguments args) throws Exception {
            // TODO Auto-generated method stub
            logger.info("hello chatbot!");
            logger.warning("Bye chatbot");
            logger.severe("Good bye");
    }
}
