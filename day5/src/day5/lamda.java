package day5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

interface Greeting {
	void sayHello();
}

public class lamda {

	public static void main(String[] args) {
		List<Integer> l = Arrays.asList(1, 2, 3, 4);
		List finalResult = l.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
		System.out.println(finalResult);

	}

}