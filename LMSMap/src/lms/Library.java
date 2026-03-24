package lms;

import java.util.ArrayList;
import java.util.List;

public class Library {
	List<Book> books = new ArrayList<>();

	public void addBook(Book book) {
		books.add(book);
		System.out.println("Book added: " + book.title);
	}

	public void removeBook(int id) {
		for (Book b : books) {
			if (b.id == id) {
				books.remove(b);
				System.out.println("Book removed: " + b.title);
				return;
			}
		}
		System.out.println("Book not found");
	}

	public void reserveBook(int id) {
		for (Book b : books) {
			if (b.id == id) {
				if (!b.reserved) {
					b.reserved = true;
					System.out.println("Book reserved: " + b.title);
				} else {
					System.out.println("Book already reserved");
				}
				return;
			}
		}
		System.out.println("Book not found");
	}

	}