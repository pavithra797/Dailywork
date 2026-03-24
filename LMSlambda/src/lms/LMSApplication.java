package lms;

import java.util.Scanner;

public class LMSApplication {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		LibraryService library = new LibraryService();

		library.setBooks(FileService.loadBooks());

		while (true) {

			System.out.println("\n1 Add Book");
			System.out.println("2 Display Books");
			System.out.println("3 Search Book");
			System.out.println("4 Borrow Book");
			System.out.println("5 Show Available Books");
			System.out.println("6 Sort Books");
			System.out.println("7 Exit");

			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {

			case 1:

				System.out.print("ID: ");
				int id = sc.nextInt();
				sc.nextLine();

				System.out.print("Title: ");
				String title = sc.nextLine();

				System.out.print("Author: ");
				String author = sc.nextLine();

				library.addBook(new Book(id, title, author, true));

				break;

			case 2:
				library.displayBooks();
				break;

			case 3:

				System.out.print("Enter title: ");
				String search = sc.nextLine();

				library.searchByTitle(search);
				break;

			case 4:

				System.out.print("Book ID: ");
				int bookId = sc.nextInt();

				library.borrowBook(bookId);
				break;

			case 5:

				library.showAvailableBooks();
				break;

			case 6:

				library.sortBooksByTitle();
				break;

			case 7:

				FileService.saveBooks(library.getBooks());

				System.out.println("Saved to file.");
				System.exit(0);
			}
		}
	}
}
