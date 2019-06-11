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
        } else if (inputText.contains("도서관")) {
            result.type = "Library";
            if (inputText.contains("중앙")) {
                if (inputText.contains("학기중")) {
                    result.answer = delegate.getLibraryInfo("중앙", "학기중");
                } else if (inputText.contains("방학중")) {
                    result.answer = delegate.getLibraryInfo("중앙", "방학중");
                } else {
                    result.answer = delegate.getLibraryInfo("중앙", null);
                }
            } else if (inputText.contains("새벽")) {
                if (inputText.contains("자료실")) {
                    result.answer = delegate.getLibraryInfo("새벽", "자료실");
                } else if (inputText.contains("열람실")) {
                    result.answer = delegate.getLibraryInfo("새벽", "열람실");
                } else {
                    result.answer = delegate.getLibraryInfo("새벽", null);
                }
            } else if (inputText.contains("미리내")) {
                if (inputText.contains("자료실")) {
                    result.answer = delegate.getLibraryInfo("미리내", "자료실");
                } else if (inputText.contains("열람실")) {
                    result.answer = delegate.getLibraryInfo("미리내", "열람실");
                } else {
                    result.answer = delegate.getLibraryInfo("미리내", null);
                }
            } else if (inputText.contains("법학")) {
                if (inputText.contains("평소")) {
                    result.answer = delegate.getLibraryInfo("법학", "평소");
                } else if (inputText.contains("시험기간")) {
                    result.answer = delegate.getLibraryInfo("법학", "시험기간");
                } else {
                    result.answer = delegate.getLibraryInfo("법학", null);
                }
            } else if (inputText.contains("의생명")) {
                if (inputText.contains("학기중")) {
                    result.answer = delegate.getLibraryInfo("의생명", "학기중");
                } else if (inputText.contains("방학중")) {
                    result.answer = delegate.getLibraryInfo("의생명", "방학중");
                } else {
                    result.answer = delegate.getLibraryInfo("의생명", null);
                }
            } else if (inputText.contains("나노생명")) {
                if (inputText.contains("자료실")) {
                    result.answer = delegate.getLibraryInfo("나노생명", "자료실");
                } else if (inputText.contains("열람실")) {
                    result.answer = delegate.getLibraryInfo("나노생명", "열람실");
                } else {
                    result.answer = delegate.getLibraryInfo("나노생명", null);
                }
            } else {
                result.answer =  delegate.getLibraryInfo(null, null);
            }
        } else if (intent.equals("학식 질문")) {
            result.type = "FoodCafe";
            if(parameters.get("Restaurant") == null) {
                result.answer = delegate.getFoodCafeInfo("");
            } else {
                result.answer = delegate.getFoodCafeInfo(responseResult);
            }
        } else {
            result.type = null;
            result.answer = delegate.getExceptionMessage();
        }

        return result;
    }
}
