package lms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class Library {

	private static final Logger logger = Logger.getLogger(Library.class.getName());
	private static final String FileName = "books.txt";

	List<Book> books = new ArrayList<>();

	public Library() {
		loadBooksFromFile();
	}

	public void addBook(Book book) {
		books.add(book);
		logger.info("Book added: " + book.title);
		saveBookToFile(book);
	}

	private void saveBookToFile(Book book) {
		try (FileWriter writer = new FileWriter(FileName, true)) {
			writer.write(
					String.format("%d,%s,%s,%.2f,%b%n", book.id, book.title, book.author, book.price, book.reserved));
		} catch (IOException e) {
			logger.warning("Error saving book: " + e.getMessage());
		}
	}

	private void loadBooksFromFile() {
		File file = new File(FileName);
		if (!file.exists())
			return;

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|");
				if (parts.length == 5) {
					int id = Integer.parseInt(parts[0]);
					String title = parts[1];
					String author = parts[2];
					double price = Double.parseDouble(parts[3]);
					boolean reserved = Boolean.parseBoolean(parts[4]);

					Book book = new Book(id, title, price, author);
					book.reserved = reserved;
					books.add(book);
				}
			}
		} catch (IOException e) {
			logger.warning("Error reading books from file: " + e.getMessage());
		}
	}

	public void showBooks() {
		if (books.isEmpty()) {
			logger.warning("No books in library.");
			return;
		}
		Consumer<Book> printBook = b -> System.out.println(b.toCSV());
		books.forEach(printBook);
	}

	public void reserveBook(int id) throws BookNotAvailableException {
		for (Book b : books) {
			if (b.id == id) {
				if (b.reserved) {
					throw new BookNotAvailableException("Book is not available: " + b.title);
				}
				b.reserved = true;
				logger.info("Book reserved: " + b.title);
				updateAllBooksFile();
				return;
			}
		}
		logger.warning("Book not found with ID: " + id);
		throw new BookNotAvailableException("Book not found with ID: " + id);
	}

	private void updateAllBooksFile() {
		try (FileWriter writer = new FileWriter(FileName)) {
			for (Book b : books) {
				writer.write(String.format("%d|%s|%s|%.2f|%b%n", b.id, b.title, b.author, b.price, b.reserved));
			}
		} catch (IOException e) {
			logger.warning("Error updating books file: " + e.getMessage());
		}
	}

	public void removeBook(int id) {
		for (Book b : books) {
			if (b.id == id) {
				books.remove(b);
				logger.info("Book removed: " + b.title);
				updateAllBooksFile();
				return;
			}
		}
		logger.warning("Book not found with ID: " + id);
	}
}