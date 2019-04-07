package NLP;

public class TextClassifier {
	String classify(String inputText) {
		if (inputText.contains("학식") || inputText.contains("금정")) {
			return "학식";
		} else if (inputText.contains("도서관")) {
			return "도서관";
		} else if (inputText.contains("학사일정")) {
			return "학사일정";
		}
		return "N/A";
	}
}
