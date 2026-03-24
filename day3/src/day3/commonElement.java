package day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class commonElement {

	public static void main(String[] args) {
		List<Integer> list1 = Arrays.asList(1,2,3,4);
		List<Integer> list2 = Arrays.asList(3,4,5,6);
		
		List<Integer> common = new ArrayList<>();
		
		for (Integer num : list1) {
            if (list2.contains(num)) {
                common.add(num);
            }
        }
		
		System.out.println("common elements:"+ common);
	
	}

}
