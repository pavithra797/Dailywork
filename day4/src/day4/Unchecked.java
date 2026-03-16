package day4;

public class Unchecked {

	public static void main(String[] args) {
//		String str = null;
//		System.out.println(str.length());
		try {
			validateAage(17);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();

		}
		System.out.println("finished");

	}

	private static void validateAage(int age) {
		if (age < 18) {
			throw new IllegalArgumentException("Age must be 18+");
		}
	}

}
