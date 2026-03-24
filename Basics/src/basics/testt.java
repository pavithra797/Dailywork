package basics;
class parent{
	void show() {
		System.out.println("parent");
	}
}

class Child extends parent{
	void show() {
		System.out.println("child");
	}
	
}

public class testt {

	public static void main(String[] args) {
		parent p = new Child();
		p.show();
		

	}

}
