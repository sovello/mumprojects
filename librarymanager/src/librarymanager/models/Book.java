package librarymanager.models;

import java.util.*;

import librarymanager.storage.StorageAccess;

@SuppressWarnings("serial")
public class Book implements Model {
	private static String OBJECT_SAVE_TYPE = "Books";
	private String isbn, title;
	private int maxCheckoutLength, numberOfCopies;
	private List<Author> authors;
	private BookCopy[] copies;
	
	public Book(String isbn, String title, int numberOfCopies, int maxCheckoutLength) {
		this.isbn = isbn;
		this.title = title;
		this.maxCheckoutLength = maxCheckoutLength;
		authors = new ArrayList<Author>();
		copies = new BookCopy[numberOfCopies];
		if( numberOfCopies == 0 ) {
			copies[0] = new BookCopy(this, 1, true);
		}
		else {
			for( int i = 0; i < numberOfCopies; i++ ) {
				copies[i] = new BookCopy(this, i+1, true);
			}
		}
	}

	/**
	 * use this constructor when updating a book details as it doesn't 
	 * create a new books list.
	 * @param isbn
	 * @param title
	 * @param maxCheckoutLength
	 */
	public Book(String isbn, String title, int maxCheckoutLength) {
		this.isbn = isbn;
		this.title = title;
		this.maxCheckoutLength = maxCheckoutLength;
	}
	
	public BookCopy getAvailableCopy() {
		BookCopy copy = null;
		for( int i = 0; i < this.copies.length; i++) {
			if( copies[i].isAvailable()) {
				return copies[i];
			}
		}
		return copy;
	}
	
	public Model getModel() {
		return this;
	}
	
	/**
	 * check if a book exists in the database
	 * @param book
	 * @param books
	 * @return true if exists, false otherwise
	 */
	public boolean bookExists(Book book, HashMap<String, Book> books) {
		//if the list of members doesn't exist, return false
		if (books == null || books.size() == 0 ) return false;
				
		if( books.get(book.getKey()) != null) {
			return true;
		}
		return false;
	}
	public String getSaveType() {
		return OBJECT_SAVE_TYPE;
	}

	
	/**
	 * save the book and return true if the book was saved
	 * return false if a duplicate book exists
	 */
	@SuppressWarnings("unchecked")
	public boolean save() {
		HashMap<String, Book> books = (HashMap<String, Book>)StorageAccess.read("Books");
		if( bookExists(this, books) ) {
			return false;
		}
		else {
			StorageAccess.save(this);
			return true;
		}
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public BookCopy[] getCopies() {
		return copies;
	}
	public int getNumberOfCopies() {
		return this.numberOfCopies;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void addAuthor(Author author) {
		this.authors.add(author);
	}
	
	public String getKey() {
		return isbn;
	}

	@Override
	public int hashCode() {
		return getKey().hashCode();
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}

	public void setMaxCheckoutLength(int maxCheckoutLength) {
		this.maxCheckoutLength = maxCheckoutLength;
	}
	
	@Override
	public String toString() {
		return String.format("%s: %s - %d", this.title, this.isbn, this.maxCheckoutLength);
	}
}
