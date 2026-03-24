package day3;

import java.util.HashMap;

public class Hashmap {

	public static void main(String[] args) {
		HashMap<Integer,String> map = new HashMap<>();
		map.put(1, "java");
		map.put(2, "python");
		map.put(3, "go");
		
		System.out.println(map);
	    System.out.println(map.get(2));

	}

}
