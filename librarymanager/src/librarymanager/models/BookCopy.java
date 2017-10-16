package librarymanager.models;

@SuppressWarnings("serial")
public class BookCopy implements Model{
	/**
	 * the bookcopy ID is autoincrement from 1 to the number available
	 */
	private int bookCopyId;
	private boolean available;
	private Book book;
	/**
	 * By default a book is available. Only when it is taken, then do we
	 * make it unavailable
	 * @param copyNum
	 * @param available
	 */
	public BookCopy(Book book, int copyNum, boolean available) {
		this.bookCopyId = copyNum;
		this.available = available;
		this.book = book;
	}
	
	public BookCopy(int copyNum) {
		this.bookCopyId = copyNum;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public int getCopyId() {
		return bookCopyId;
	}
	
	public void setNotAvailable() {
		this.available = false;
	}
	
	/**
	 * looks for a book copy from the given 
	 */
	public void getBookCopy() {
		
	}
	
	public void setAvailable() {
		this.available = true;
	}
	
	public Book getBook() {
		return book;
	}
	@Override
	public String getSaveType() {
		return "BookCopies";
	}
	
	//ISBN-ID
	@Override
	public String getKey() {
		return String.format("%s-%d", this.book.getIsbn(), this.bookCopyId);
	}
	
	@Override
	public int hashCode() {
		return getKey().hashCode();
	}
}
