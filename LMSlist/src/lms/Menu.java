package lms;
import java.util.Scanner;
import java.util.logging.Logger;

public class Menu {

	private static final Logger logger = Logger.getLogger(Menu.class.getName());

	static Scanner sc = new Scanner(System.in);
	static Library library = new Library();

	public static void main(String[] args) throws BookNotAvailableException {

		while (true) {

			logger.info("""
					\n---- Book Management ----
					1. Add Book
					2. Remove Book
					3. Reserve Book
					4. Show Books
					5. Exit
					""");

			logger.info("Enter choice: ");
			int choice = sc.nextInt();

			switch (choice) {

			case 1:
				handleAdd();
				break;

			case 2:
				handleRemove();
				break;

			case 3:
				handleReserve();
				break;

			case 4:
				library.showBooks();
				break;

			case 5:
				logger.info("Program ended.");
				return;

			default:
				logger.info("Invalid choice");
			}
		}
	}
	static void handleAdd() {

		logger.info("Enter Book ID: ");
		int id = sc.nextInt();
		sc.nextLine();

		logger.info("Enter Title: ");
		String title = sc.nextLine();

		logger.info("Enter Author: ");
		String author = sc.nextLine();

		logger.info("Enter Price: ");
		double price = sc.nextDouble();

		library.addBook(new Book(id, title, price, author));
	}

	static void handleRemove() {
		logger.info("Enter Book ID to remove: ");
		int id = sc.nextInt();
		library.removeBook(id);
	}

	static void handleReserve() throws BookNotAvailableException {
		logger.info("Enter Book ID to reserve: ");
		int id = sc.nextInt();
		library.reserveBook(id);
	}
}