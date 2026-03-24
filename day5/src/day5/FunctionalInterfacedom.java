package day5;

@FunctionalInterface
interface Animal {
	void eat();
}

class Cat implements Animal {
	@Override
	public void eat() {
		System.out.println("animal eat inside class");
	}
}

public class FunctionalInterfacedom {
	public static void main(String[] args) {
		oopWay();

		Animal animal = () -> {
			System.out.println("animal eat in lamda");
		};
		animal.eat();
	}

	private static void oopWay() {
		Animal animal = new Cat();
		animal.eat();
	}
}