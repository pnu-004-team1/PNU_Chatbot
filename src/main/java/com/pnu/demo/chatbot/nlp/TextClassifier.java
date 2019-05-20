package com.pnu.demo.chatbot.nlp;

import com.pnu.demo.chatbot.service.ChatbotServiceDelegate;

public class TextClassifier {
    public String classify(ChatbotServiceDelegate delegate, String inputText) {
        if (inputText.startsWith("도서:")) {
            String query = inputText.replace("도서:", "");
            return delegate.getBookInfo(query);
        } else if (inputText.contains("열람실") || inputText.contains("좌석")) {
            if (inputText.contains("새벽벌")) {
                return delegate.getLibrarySeatingInfo("새벽벌");
            } else if (inputText.contains("건설관")) {
                return delegate.getLibrarySeatingInfo("건설관");
            } else if (inputText.contains("나노생명")) {
                return delegate.getLibrarySeatingInfo("나노생명");
            } else {
                return delegate.getLibrarySeatingInfo("all");
            }
        } else if (inputText.contains("학사") && inputText.contains("일정")) {
            return delegate.getUniversityCalendar();
        } else if (inputText.contains("도서관")) {
            if (inputText.contains("연락") || inputText.contains("전화") || inputText.contains("번호") || inputText.contains("주소") || inputText.contains("담당자")) {
                if (inputText.contains("부산")) {
                    return delegate.getLibraryContactInfo("부산");
                } else if (inputText.contains("법학")) {
                    return delegate.getLibraryContactInfo("법학");
                } else if (inputText.contains("양산")) {
                    return delegate.getLibraryContactInfo("양산");
                } else if (inputText.contains("아미")) {
                    return delegate.getLibraryContactInfo("아미");
                } else if (inputText.contains("밀양")) {
                    return delegate.getLibraryContactInfo("밀양");
                } else {
                    return delegate.getLibraryContactInfo(null);
                }
            }
            if (inputText.contains("중앙")) {
                if (inputText.contains("학기중")) {
                    return delegate.getLibraryInfo("중앙", "학기중");
                } else if (inputText.contains("방학중")) {
                    return delegate.getLibraryInfo("중앙", "방학중");
                } else {
                    return delegate.getLibraryInfo("중앙", null);
                }
            } else if (inputText.contains("새벽")) {
                if (inputText.contains("자료실")) {
                    return delegate.getLibraryInfo("새벽", "자료실");
                } else if (inputText.contains("열람실")) {
                    return delegate.getLibraryInfo("새벽", "열람실");
                } else {
                    return delegate.getLibraryInfo("새벽", null);
                }
            } else if (inputText.contains("미리내")) {
                if (inputText.contains("자료실")) {
                    return delegate.getLibraryInfo("미리내", "자료실");
                } else if (inputText.contains("열람실")) {
                    return delegate.getLibraryInfo("미리내", "열람실");
                } else {
                    return delegate.getLibraryInfo("미리내", null);
                }
            } else if (inputText.contains("법학")) {
                if (inputText.contains("평소")) {
                    return delegate.getLibraryInfo("법학", "평소");
                } else if (inputText.contains("시험기간")) {
                    return delegate.getLibraryInfo("법학", "시험기간");
                } else {
                    return delegate.getLibraryInfo("법학", null);
                }
            } else if (inputText.contains("의생명")) {
                if (inputText.contains("학기중")) {
                    return delegate.getLibraryInfo("의생명", "학기중");
                } else if (inputText.contains("방학중")) {
                    return delegate.getLibraryInfo("의생명", "방학중");
                } else {
                    return delegate.getLibraryInfo("의생명", null);
                }
            } else if (inputText.contains("나노생명")) {
                if (inputText.contains("자료실")) {
                    return delegate.getLibraryInfo("나노생명", "자료실");
                } else if (inputText.contains("열람실")) {
                    return delegate.getLibraryInfo("나노생명", "열람실");
                } else {
                    return delegate.getLibraryInfo("나노생명", null);
                }
            }
            return delegate.getLibraryInfo(null, null);
        } else {
            return delegate.getExceptionMessage();
        }
    }
}
