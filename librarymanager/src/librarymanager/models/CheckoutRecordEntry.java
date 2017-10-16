package librarymanager.models;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class CheckoutRecordEntry implements Model, Serializable {
	private BookCopy bookCopy;
	private String memberid;
	private LocalDate checkoutDate, dueDate, returnDate;
	public CheckoutRecordEntry(BookCopy bookCopy, LibraryMember member) {
		this.bookCopy = bookCopy;
		this.checkoutDate = LocalDate.now();
		this.dueDate = checkoutDate.plusDays(bookCopy.getBook().getMaxCheckoutLength());
		this.memberid = member.getMemberId();
		this.returnDate = null;
	}
	
	public String getMemberid() {
		return memberid;
	}
	
	public String getIsbn(){
		return bookCopy.getBook().getIsbn();
	}
	
	public String getId() {
		return String.format("%s", bookCopy.getCopyId());
	}
	
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}
	
	public BookCopy getBookCopy() {
		return bookCopy;
	}
	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}
	
	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	
	@Override
	public String toString() {
		return String.format("%s - %d", bookCopy.getBook().getIsbn(), bookCopy.getCopyId());
	}

	@Override
	public String getSaveType() {
		return "CheckoutRecordEntry";
	}

	//ISBN-ID-MEMBER_ID
	@Override
	public String getKey() {
		return String.format("%s-%s", bookCopy.getKey(), this.getMemberid());
	}
	
	@Override
	public int hashCode() {
		return getKey().hashCode();
	}
}
