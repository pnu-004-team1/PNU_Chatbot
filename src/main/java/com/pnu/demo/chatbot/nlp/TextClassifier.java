package com.pnu.demo.chatbot.nlp;

import ai.api.model.AIResponse;
import com.google.gson.JsonElement;
import com.pnu.demo.chatbot.service.ChatbotServiceDelegate;
import com.pnu.demo.chatbot.dialogflow.IntentClassification;

import java.util.HashMap;

public class TextClassifier {
    private IntentClassification intentClassification = new IntentClassification();

    public ClassificationResult classify(ChatbotServiceDelegate delegate, String inputText) {
        ClassificationResult result = new ClassificationResult();
        AIResponse response = intentClassification.getResponse(inputText);

        String intent = intentClassification.getIntent(response);
        String responseResult = intentClassification.getResult(response);
        HashMap<String, JsonElement> parameters = intentClassification.getParameters(response);

        if (intent.equals("논문 질문")) {
            String query = inputText.replace("논문:", "");
            result.type = "EArtical";
            result.answer = delegate.getEArtical(query);
        } else if(intent.equals("책 질문")) {
            String query = inputText.replace("도서:", "").replace("책:","");
            result.type = "BookInfo";
            result.answer = delegate.getBookInfo(query);
        }else if (intent.equals("열람실 질문")) {
            result.type = "LibrarySeating";
            result.answer = delegate.getLibrarySeatingInfo(responseResult);
        } else if (intent.equals("학사 일정 질문")) {
            result.type = "UniversityCalendar";
            result.answer = delegate.getUniversityCalendar();
        } else if(intent.equals("도서관 관리자 질문")) {
            result.type = "LibraryContact";
            if(parameters.get("LibraryInfoSet") == null) {
                result.answer = delegate.getLibraryContactInfo(null);
            } else {
                result.answer = delegate.getLibraryContactInfo(responseResult);
            }
        } else if(intent.equals("도서관 시간 질문")) {
            result.type = "Library";
            result.answer = delegate.getLibraryInfo(null, null);
        } else if(intent.equals("중앙도서관 시간")) {
            result.type = "Library";
            if(parameters.get("LibraryTime") == null) {
                result.answer = delegate.getLibraryInfo("중앙", null);
            } else {
                result.answer = delegate.getLibraryInfo("중앙", responseResult);
            }
        } else if(intent.equals("새벽벌도서관 시간")) {
            result.type = "Library";
            if(parameters.get("LibraryInfo") == null) {
                result.answer = delegate.getLibraryInfo("새벽", null);
            } else {
                result.answer = delegate.getLibraryInfo("새벽", responseResult);
            }
        } else if(intent.equals("미리내도서관 시간")) {
            result.type = "Library";
            if(parameters.get("LibraryInfo") == null) {
                result.answer = delegate.getLibraryInfo("미리내", null);
            } else {
                result.answer = delegate.getLibraryInfo("미리내", responseResult);
            }
        } else if(intent.equals("법학도서관 시간")) {
            result.type = "Library";
            if(parameters.get("LegalLibraryTime") == null) {
                result.answer = delegate.getLibraryInfo("법학", null);
            } else {
                result.answer = delegate.getLibraryInfo("법학", responseResult);
            }
        } else if(intent.equals("의생명과학도서관 시간")) {
            result.type = "Library";
            if(parameters.get("LibraryTime") == null) {
                result.answer = delegate.getLibraryInfo("의생명", null);
            } else {
                result.answer = delegate.getLibraryInfo("의생명", responseResult);
            }
        }else if(intent.equals("나노생명과학도서관 시간")) {
            result.type = "Library";
            if(parameters.get("LibraryInfo") == null) {
                result.answer = delegate.getLibraryInfo("나노생명", null);
            } else {
                result.answer = delegate.getLibraryInfo("나노생명", responseResult);
            }
        }  else if (intent.equals("학식 질문")) {
            result.type = "FoodCafe";
            String[] results = responseResult.split(",");
            String restaurant = results[0];
            String menu = results[1];
            result.answer = delegate.getFoodCafeInfo(restaurant, menu);
        } else {
            result.type = null;
            result.answer = delegate.getExceptionMessage();
        }

        return result;
    }
}
