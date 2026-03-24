package day4;

import java.util.logging.Logger;

public class Logging {
	private static final Logger Logger = java.util.logging.Logger.getLogger(Logging .class.getName());

	public static void main(String[] args) {
	    Logger.info("Application started");
		Logger.warning("low memory warning");
		Logger.severe("System failure");
		

	}

}
