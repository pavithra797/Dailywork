package day5;

@FunctionalInterface
interface Add {
	int sum(int a, int b);
}

public class Lamdawithparameter {

	public static void main(String[] args) {
		Add add = (a, b) -> a + b;
		System.out.println(add.sum(5, 3));

	}
}
