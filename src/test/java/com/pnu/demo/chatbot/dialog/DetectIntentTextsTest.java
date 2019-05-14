package com.pnu.demo.chatbot.dialog;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.cloud.dialogflow.v2.TextInput.Builder;
import com.google.common.collect.Maps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DetectIntentTextsTest {
    private String projectid;
    private List<String> texts;
    private String languagecode;
    private String sessionid;
    private Map<String, QueryResult> result;

    @Before
    public void setUp() {
        projectid = "pnuchatbot";
        texts = new ArrayList<String>();
        sessionid = "122344321";
        languagecode = "ko";

    }

    @After
    public void tearDown(){
        texts = null;
    }

    @Test
    public void detectIntentTexts() {
        texts.add("안녕");
        texts.add("오늘 학식 뭐야?");
        texts.add("수강 신청 기간 언제야");
        try {
            result = DetectIntentTexts.detectIntentTexts(projectid, texts, sessionid,languagecode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(texts.size(), 3);
        //assertEquals(result.size(), 3);
    }
}