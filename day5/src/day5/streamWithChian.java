package day5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamWithChian {

	public static void main(String[] args) {

		StreamWithChain();

	}

	private static void StreamWithChain() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		Stream<Integer> finalStream = numbers.stream().map(number -> {
			return (number % 2 == 1) ? number * number : number;
		}).filter((number) -> {
			System.out.println(number);
			return number % 2 != 0;
		});
		List finalList = finalStream.collect(Collectors.toList());
		finalList.forEach(x -> System.out.println(x));
		finalList.forEach(System.out::println); // shorter method reference
//		System.out.println(finalList);
	}
}