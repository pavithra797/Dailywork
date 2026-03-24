package day4;

import java.io.*;

public class checked {

	public static void main(String[] args) {
		try {
			readfile();

		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
	}
	private static void readfile() throws FileNotFoundException {
		FileReader file = new FileReader("data.txt");

	}

}
