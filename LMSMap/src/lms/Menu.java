package lms;

import java.util.Scanner;

public class Menu {

	static Scanner sc = new Scanner(System.in);
	static Library library = new Library();

	public static void main(String[] args) {

		while (true) {

			System.out.println("\n---- Book Management ----");
			System.out.println("1. Add Book");
			System.out.println("2. Remove Book");
			System.out.println("3. Reserve Book");
			System.out.println("4. Show Books");
			System.out.println("5. Exit");
			System.out.print("Enter choice: ");

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
				System.out.println("Program ended.");
				return; // exits main method

			default:
				System.out.println("Invalid choice");
			}
		}
	}

	static void handleAdd() {

		System.out.print("Enter Book ID: ");
		int id = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter Title: ");
		String title = sc.nextLine();

		System.out.print("Enter Author: ");
		String author = sc.nextLine();

		System.out.print("Enter Price: ");
		double price = sc.nextDouble();

		library.addBook(new Book(id, title, price, author));
	}

	static void handleRemove() {
		System.out.print("Enter Book ID to remove: ");
		int id = sc.nextInt();
		library.removeBook(id);
	}

	static void handleReserve() {
		System.out.print("Enter Book ID to reserve: ");
		int id = sc.nextInt();
		library.reserveBook(id);
	}
}
