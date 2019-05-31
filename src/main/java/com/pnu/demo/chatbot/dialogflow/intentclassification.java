package com.pnu.demo.chatbot.dialogflow;

import java.io.BufferedReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

public class IntentClassification {
    private String APIKey="6e7ff2ba5c2544989452aabf48c4d54e";
    private AIConfiguration configuration = new AIConfiguration(APIKey);
    private AIDataService dataService = new AIDataService(configuration);

    public void intentClassification(String line){
        try{
            AIRequest request = new AIRequest(line);
            AIResponse response = dataService.request(request);

            if (response.getStatus().getCode() == 200) {
                System.out.println(response.getResult().getFulfillment().getSpeech());
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }


    private static void showHelp(String errorMessage, int exitCode) {
        if (errorMessage != null && errorMessage.length() > 0) {
            System.err.println(errorMessage);
            System.err.println();
        }

        System.out.println("Usage: APIKEY");
        System.out.println();
        System.out.println("APIKEY  Your unique application key");
        System.out.println("        See https://docs.api.ai/docs/key-concepts for details");
        System.out.println();
        System.exit(exitCode);
    }
}
