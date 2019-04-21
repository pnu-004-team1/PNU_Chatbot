package com.pnu.demo.chatbot.hello;

import com.pnu.demo.chatbot.libInfo.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.pnu.demo.chatbot.readingRoom.ReadingRoomParsing;
import com.pnu.demo.chatbot.academicCalendar.AcademicCalendarParsing;

import java.util.logging.Logger;

@Component
public class HelloWorld implements ApplicationRunner {
    private static final Logger logger = Logger.getLogger(HelloWorld.class.getName());

    AcademicCalendarParsing libraryCrawler = new AcademicCalendarParsing();

    @Override
    public void run(ApplicationArguments args) throws Exception {
            // TODO Auto-generated method stub
        System.out.println(libraryCrawler.getResult("7월"));

    }
}
