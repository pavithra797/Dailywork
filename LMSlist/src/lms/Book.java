package lms;

public class Book {

	int id;
	String title;
	double price;
	String author;
	boolean reserved;

	public Book(int id, String title, double price, String author) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.author = author;
		this.reserved = false;
	}

	@Override
	public String toString() {
		return """
				Book ID   : %d
				Title     : %s
				Author    : %s
				Price     : $%.2f
				---------------------------
				""".formatted(id, title, author, price);
	}

}