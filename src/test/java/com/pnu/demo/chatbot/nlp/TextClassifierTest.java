package com.pnu.demo.chatbot.nlp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextClassifierTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        TextClassifier classifier = new TextClassifier();

        String text = "오늘 학식이 뭐야?";
        System.out.println(classifier.classify(text));

        text = "도서관 열람실 자리 얼마나 남았어?";
        System.out.println(classifier.classify(text));

        text = "학사일정 알려줘";
        System.out.println(classifier.classify(text));

        text = "장학금 종류는?";
        System.out.println(classifier.classify(text));

        text = "금정식당 메뉴가 뭐야?";
        System.out.println(classifier.classify(text));
    }

}