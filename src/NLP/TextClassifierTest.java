package NLP;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextClassifierTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
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
