package day4;
import java.io.*;
public class FileRead {

	public static void main(String[] args)  throws IOException{
		FileReader reader = new FileReader("data.txt");
		
		int character;
		
		while((character = reader.read())!= -1) {
			System.out.println((char) character);
		}


	}

}
