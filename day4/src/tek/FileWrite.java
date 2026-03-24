package tek;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWrite {

	public static void main(String[] args) throws IOException {
			FileWriter writer = new FileWriter("Received Input output.txt", true);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the text you want in the file, press ctrl C to close:");
			try {
				while(true) {
					String input = sc.nextLine();
					writer.write(input+ "\n");
					writer.flush();
					System.out.println("If you want to enter more, go ahead: ");
				}
			} finally {
				writer.close();
				sc.close();
	}
}
}