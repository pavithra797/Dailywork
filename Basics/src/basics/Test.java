package basics;

public class Test {
	
	int x=10;
	float interest = 13.1f;
	void display() {
		System.out.println(x);
	}

	public static void main(String[] args) {
		Test obj = new Test();
		obj.display();
		whileloop();
		

	}
	private static void whileloop() {
		int i=1;
		while(i>=5) {
			System.out.println(i);
			i++;
		}
	}

}
