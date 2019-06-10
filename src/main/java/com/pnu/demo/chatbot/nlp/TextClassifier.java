package com.pnu.demo.chatbot.nlp;

import com.pnu.demo.chatbot.service.ChatbotServiceDelegate;
import com.pnu.demo.chatbot.dialogflow.IntentClassification;

public class TextClassifier {
    private IntentClassification intentClassification = new IntentClassification();

    public ClassificationResult classify(ChatbotServiceDelegate delegate, String inputText) {
        String intent = intentClassification.intentClassification(inputText);
        ClassificationResult result = new ClassificationResult();

        if (inputText.startsWith("논문:")) {
            String query = inputText.replace("논문:", "");
            result.type = "EArtical";
            result.answer = delegate.getEArtical(query);
        } else if (inputText.contains("열람실") && inputText.contains("좌석")) {
            result.type = "LibrarySeating";
            result.answer = delegate.getLibrarySeatingInfo(intent);
        } else if (inputText.contains("학사") && inputText.contains("일정")) {
            result.type = "UniversityCalendar";
            result.answer = delegate.getUniversityCalendar();
        } else if (inputText.contains("도서관")) {
            result.type = "Library";
            if (inputText.contains("연락") || inputText.contains("전화") || inputText.contains("번호") || inputText.contains("주소") || inputText.contains("담당자")) {
                result.type = "LibraryContact";
                if (inputText.contains("부산")) {
                    result.answer = delegate.getLibraryContactInfo("부산");
                } else if (inputText.contains("법학")) {
                    result.answer = delegate.getLibraryContactInfo("법학");
                } else if (inputText.contains("양산")) {
                    result.answer = delegate.getLibraryContactInfo("양산");
                } else if (inputText.contains("아미")) {
                    result.answer = delegate.getLibraryContactInfo("아미");
                } else if (inputText.contains("밀양")) {
                    result.answer = delegate.getLibraryContactInfo("밀양");
                } else {
                    result.answer = delegate.getLibraryContactInfo(null);
                }
            } else if (inputText.contains("중앙")) {
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
        } else {
            result.type = null;
            result.answer = delegate.getExceptionMessage();
        }

        return result;
    }
}
