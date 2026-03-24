package day2;

import java.util.ArrayList;
import java.util.List;

public class petClinic {
	public static void main(String[] args) {
		methodOverloading();
	}
	private static void methodOverloading() {
	}
	private static void demo1() {
		Dog dog = new Dog("Husky");
		dog.setName("pluto");

		pet dog1 = new Dog("Husky");
		Cat cat = new Cat();

		dog.bark();
		List<pet> pets = new ArrayList<pet>();
		pets.add(dog);
		pets.add(dog1);
		pets.add(cat);
		pets.forEach((pet) -> pet.play());
	}

}
