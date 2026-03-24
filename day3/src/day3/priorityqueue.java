package day3;

import java.util.PriorityQueue;

public class priorityqueue {

	public static void main(String[] args) {
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		pq.add(30);
		pq.add(40);
		pq.add(50);
		
		System.out.println(pq);
		System.out.println(pq.poll());

	}

}
