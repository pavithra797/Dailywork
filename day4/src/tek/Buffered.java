package tek;

import java.io.BufferedReader;
import java.io.FileReader;

public class Buffered {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("data.txt"));

		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);

		}
		reader.close();

	}

}