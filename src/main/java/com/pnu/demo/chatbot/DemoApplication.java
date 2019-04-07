package com.pnu.demo.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run();

        SpringApplication.run(DemoApplication.class, args);
    }
}
