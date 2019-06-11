package com.pnu.demo.chatbot.nlp;

import com.pnu.demo.chatbot.service.ChatbotServiceDelegate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextClassifierTest implements ChatbotServiceDelegate {

    TextClassifier classifier;
    String expectedValue;
    String query = "";
    ClassificationResult sut;

    @Before
    public void setUp() throws Exception {
        classifier = new TextClassifier();
    }

    @After
    public void tearDown() {
        query = null;
    }

    @Test
    public void testPaper() {
        // given
        expectedValue = "getEArtical";
        query = "논문";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }

    @Test
    public void testLibraryRoom() {
        // given
        expectedValue = "getLibrarySeatingInfo";
        query = "열람실좌석";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }

    @Test
    public void testUniveristyCalendar() {
        // given
        expectedValue = "getUniversityCalendar";
        query = "학사일정";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }

    @Test
    public void testLibraryManager() {
        // given
        expectedValue = "getLibraryContactInfo";
        query = "도서관 관리자";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }

    @Test
    public void testLibraryAll() {
        // given
        expectedValue = "getLibraryInfo";
        query = "도서관";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }

    @Test
    public void testLibraryCentralSemester() {
        // given
        expectedValue = "getLibraryInfo";
        query = "방학중 중앙도서관";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }

    @Test
    public void testLibraryCentralVacation() {
        // given
        expectedValue = "getLibraryInfo";
        query = "중앙도서관 시간";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }

    @Test
    public void testLibraryCentralNoTime() {
        // given
        expectedValue = "getLibraryInfo";
        query = "그냥 중앙도서관";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }

    @Test
    public void testLibraryDawnInformationRoom() {
        // given
        expectedValue = "getLibraryInfo";
        query = "자료실 새벽 도서관";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }

    @Test
    public void testLibraryDawnReadingRoom() {
        // given
        expectedValue = "getLibraryInfo";
        query = "새벽 도서관 시간";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }

    @Test
    public void testLibraryDawnAllRoom() {
        // given
        expectedValue = "getLibraryInfo";
        query = "그냥 새벽 도서관";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }


    @Test
    public void testLibraryMirineInformationRoom() {
        // given
        expectedValue = "getLibraryInfo";
        query = "자료실 미리내 도서관";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }

    @Test
    public void testLibrarMirineReadingRoom() {
        // given
        expectedValue = "getLibraryInfo";
        query = "미리내 도서관 시간";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }

    @Test
    public void testLibraryMirineAllRoom() {
        // given
        expectedValue = "getLibraryInfo";
        query = "그냥 미리내 도서관";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }

    @Test
    public void testLibraryLegalUsally() {
        // given
        expectedValue = "getLibraryInfo";
        query = "평소 법학 도서관";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }

    @Test
    public void testLibraryLegalTestPeriod() {
        // given
        expectedValue = "getLibraryInfo";
        query = "시험기간 법학 도서관";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }

    @Test
    public void testLibraryLegalAllTime() {
        // given
        expectedValue = "getLibraryInfo";
        query = "그냥 법학 도서관";
        // when
        sut = classifier.classify(this, query);
        // then
        assertEquals(expectedValue, sut.answer);
    }

    @Override
    public String getBookInfo(String query) {
        return "getBookInfo";
    }

    @Override
    public String getEArtical(String query) {
        return "getEArtical";
    }

    @Override
    public String getLibraryInfo(String libName, String category) {
        return "getLibraryInfo";
    }

    @Override
    public String getLibrarySeatingInfo(String query) {
        return "getLibrarySeatingInfo";
    }

    @Override
    public String getLibraryContactInfo(String libName) {
        return "getLibraryContactInfo";
    }

    @Override
    public String getUniversityCalendar(String query) { return "getUniversityCalendar"; }

    @Override
    public String getFoodCafeInfo(String restaurant, String time) { return "getFoodCafeInfo"; }

    @Override
    public String getExceptionMessage() {
        return "getExceptionMessage";
    }
}