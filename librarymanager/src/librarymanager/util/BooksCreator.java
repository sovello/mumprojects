package librarymanager.util;

import librarymanager.models.Author;
import librarymanager.models.Book;
import librarymanager.storage.StorageAccess;

public class BooksCreator {
	private static String[] titles = {
			"What Happened", 
			"Star Wars", 
			"The Lord of the Rings",
			"Prison Break",
			"Javafx : Activate a tooltip with a button\n"
			};
	private static String[][] authors = {
			{"Sherrie Raffaele"},
			{"Willia Whitenack", "Norbert Nottingham"},
			{"Coreen Behrends", "Harriett Kerfoot", "Jasmin Fyfe"},
			{"Beverlee Aikin", "Regina Kretschmer", "Nelson Tootle","Alonso Beaird"},
			{"Arletta Peloquin", "Izetta Kinard", "Ha Beyer", "Brianna Blocker"}
	};
			
	private static String[] isbn = {"1", "12", "1233", "1234", "12345"};
	private static int[] copies = {1, 2, 3, 4, 5};
	private static int[] maxtime = {5, 21, 14, 10, 3};
	
	public BooksCreator() {
		for( int i = 0; i < titles.length; i++) {
			Book book = new Book(isbn[i], titles[i], copies[i], maxtime[i]);
			
			for( int j = 0; j < authors[i].length; j++ ) { 
				String names[] = authors[i][j].split("\\s");
				Author author = new Author(
						String.format("%d", i+1), //credentials
						names[0], names[1]);
				book.addAuthor(author);
				StorageAccess.save(author);
			}
			StorageAccess.save(book);
		}
	}
	
	public static String[] getIsbn() {
		return isbn;
	}
	
	public static int[] getCopies() {
		return copies;
	}
	
	public static int[] getMaxtime() {
		return maxtime;
	}
	public static void main(String[] args) {
		new BooksCreator();
	}
}

