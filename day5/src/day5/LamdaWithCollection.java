package day5;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LamdaWithCollection {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Java", "Python", "Go");

//		Consumer<String> consumer = name -> System.out.println(name);
//		Consumer<String> consumer = (String name) -> System.out.println(name);
//		Consumer<String> consumer = (name) -> System.out.println(name);
		Consumer<String> consumer = name -> {
			System.out.println("name=");
			System.out.println(name);
		};

	}
}
