package com.pnu.demo.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        /*
            지금은 서버로 동작하지 않고 그냥 자바 프로젝트로 동작하게 해놓았습니다.
            밑에 3줄 코드를 주석처리하고 20번째 줄에 있는 코드 주석을 풀면 서버로 동작합니다.
         */
//        SpringApplication application = new SpringApplication(DemoApplication.class);
//        application.setWebApplicationType(WebApplicationType.NONE);
//        application.run();

        // 밑에 코드 주석을 풀면 서버로 동작합니다.
        SpringApplication.run(DemoApplication.class, args);
    }

}

