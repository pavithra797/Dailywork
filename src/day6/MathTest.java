package day6;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class MathTest {
	@Test
	void testAdd() {
		Math math = new Math();
		int result = math.add(2, 5);
		assertEquals(7,result);
	}
	@Test
	void testAddNegativeNumbers() {
		Math math = new Math();
		int result = math.add(-2,-5);
		assertEquals(-7,result);
	}

}

