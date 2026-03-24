package day2;

public class Dog extends Animal implements pet {
	private String breed;

	public Dog(String breed) {
		super();
		this.breed = breed;
	}

	public String getBreed() {
		return breed;
	}

	@Override
	public void play() {
		System.out.println("playing with"+ getName());

	}

	public void bark() {
		System.out.println(getName()+"is barking");
	}

}
