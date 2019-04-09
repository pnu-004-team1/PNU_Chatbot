package com.pnu.demo.chatbot.service;

public class ChatbotService {
    public String getAnswer(String questionText) {
        if (questionText.contains("학식")) {

        } else if (questionText.contains("학사") && questionText.contains("일정")) {
            return "후기 학위청구 심사용 논문 제출\n" +
                    "2019.04.09 - 2019.04.09\n" +
                    "1학기 중간고사\n" +
                    "2019.04.15 - 2019.04.20\n" +
                    "1학기 수업일수 1/2선\n" +
                    "2019.04.25 - 2019.04.25\n" +
                    "여름계절수업 수강대상자 복학신청\n" +
                    "2019.05.02 - 2019.05.09\n" +
                    "1학기 수업일수 2/3선\n" +
                    "2019.05.13 - 2019.05.13\n" +
                    "개교기념일\n" +
                    "2019.05.15 - 2019.05.15\n" +
                    "여름계절수업 희망과목담기\n" +
                    "2019.05.16 - 2019.05.17\n" +
                    "여름계절수업 수강신청\n" +
                    "2019.05.20 - 2019.05.22\n" +
                    "여름계절수업 수강정정\n" +
                    "2019.05.28 - 2019.05.29";
        }
        return "잘 모르겠네요...";
    }
}
