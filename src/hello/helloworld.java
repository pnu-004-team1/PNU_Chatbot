package hello;

import java.util.logging.*;

public class helloworld {
	
	private static final Logger logger = Logger.getLogger(helloworld.class.getName());
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.info("hello chatbot!");
		logger.warning("Bye chatbot");
		logger.severe("Good bye");
	}
}
