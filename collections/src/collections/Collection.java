package collections;

import java.util.List;
import java.util.*;
public class Collection {

	public static void main(String[] args) {
		
		List<String> fruits = new ArrayList<>();
		
		fruits.add("Apple");
		fruits.add("mango");
		fruits.add(new String("mango"));
		fruits.add("banana");
		
		
		System.out.println(fruits);
		System.out.println(fruits.get(1)== fruits.get(2));
	}

}
