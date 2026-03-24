package day5;

import java.util.function.Predicate;

public class PredicateExamp {

	public static void main(String[] args) {
		Predicate<Integer> isEven = (Integer n)-> n % 2 ==0;
//		Predicate<Integer> isEven = n-> n % 2 ==0;
//		Predicate<Integer> isEven = n ->{
//		return n % 2 ==0;
//
//	};
    System.out.println(isEven.test(10));
}
}
