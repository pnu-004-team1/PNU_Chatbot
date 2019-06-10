package com.pnu.demo.chatbot.dialogflow;

import java.io.BufferedReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import com.google.gson.JsonElement;


public class IntentClassification {
    private String APIKey="6e7ff2ba5c2544989452aabf48c4d54e";
    private AIConfiguration configuration = new AIConfiguration(APIKey);
    private AIDataService dataService = new AIDataService(configuration);

    public AIResponse getResponse(String line){
        AIRequest request = new AIRequest(line);
        AIResponse response = null;
        try {
            response = dataService.request(request);
        } catch (AIServiceException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String getIntent(AIResponse response){
        String intent = response.getResult().getMetadata().getIntentName();
        return intent;
    }

    public String getResult(AIResponse response){
        String result = response.getResult().getFulfillment().getSpeech();
        return result;
    }

    public HashMap<String, JsonElement> getParameters(AIResponse response) {
        HashMap<String, JsonElement> parameters = response.getResult().getParameters();
        return parameters;
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
