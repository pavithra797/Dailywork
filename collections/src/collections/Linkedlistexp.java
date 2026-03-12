package collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Linkedlistexp {

public static void main(String[] args) {
	List<String> cities = createListOfCities();
	System.out.println(cities.contains("Delhi"));
	System.out.println(cities.remove("Delhi"));
	System.out.println(cities.lastIndexOf("Delhi"));
	System.out.println(cities.set(0, "chennai"));
	System.out.println(cities);
}

	private static List<String> createListOfCities() {
		List<String> cities = new ArrayList<>();

		cities.add("Delhi");
		cities.add("mumai");
		cities.add("banglore");

		LinkedList<String> linkedList = ((LinkedList) cities); // downcasting
		linkedList.addFirst("chennai");
	}
}
}
