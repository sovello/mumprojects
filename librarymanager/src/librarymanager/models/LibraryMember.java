package librarymanager.models;

import java.time.LocalDate;
import java.util.*;

import librarymanager.storage.StorageAccess;

@SuppressWarnings("serial")
public class LibraryMember extends Person implements Model{
	private String memberId;
	private List<CheckoutRecordEntry> booksBorrowed;
	public LibraryMember(String memberId, String firstname, String lastname,
			String phone, Address address) {
		super(firstname, lastname, phone, address);
		this.memberId = memberId;
		booksBorrowed = new ArrayList<CheckoutRecordEntry>();
	}
	
	public String getMemberId() {
		return memberId;
	}

	public void addBook(CheckoutRecordEntry book) {
		booksBorrowed.add(book);
	}
	
	/**
	 * member has a book if he has this isbn number and the return date
	 * for the book is not yet set
	 * @param isbn
	 * @return
	 */
	public boolean hasBook(String isbn) {
		
		boolean hasBook = false;
		for( CheckoutRecordEntry entry : booksBorrowed) {
			if( entry.getBookCopy().getBook().getIsbn().equals(isbn) &&
					entry.getReturnDate() == null) {
				hasBook = true;
				break;
			}
		}
		return hasBook;
	}
	
	public List<CheckoutRecordEntry> getBooksBorrowed(){
		return this.booksBorrowed;
	}
	
	
	/**
	 * sets the return date for the book this member borrowed
	 */
	public void returnBook(CheckoutRecordEntry entry) {
		//get list of books
		
		//loop through books to find the one
		for( CheckoutRecordEntry e : booksBorrowed) {
			if( e.getId().equals(entry.getId()) ) {
				e.setReturnDate(LocalDate.now());
			}
		}
		
		StorageAccess.save(this);
	}
	
	@SuppressWarnings("unchecked")
	public static LibraryMember getLibraryMember(String key) {
		HashMap<String, LibraryMember> members= 
				(HashMap<String, LibraryMember>)StorageAccess.read("LibraryMembers");
		return members.get(key);
	}
	
	/**
	 * check if a book exists in the database
	 * @param book
	 * @param books
	 * @return true if exists, false otherwise
	 */
	public boolean memberExists(LibraryMember member, HashMap<String, LibraryMember> members) {
		//if the list of members doesn't exist, return false
		if (members == null || members.size() == 0 ) return false;
		if( members.get(member.getKey()) != null) {
			return true;
		}
		return false;
	}
	
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String getSaveType() {
		return "LibraryMembers";
	}

	@Override
	public String getKey() {
		return memberId;
	}
	
	@Override
	public boolean equals(Object object) {
		if( object == null ) return false;
		if( object instanceof LibraryMember ) return false;
		LibraryMember member = (LibraryMember)object;
		return this.memberId.equals(member.getMemberId());
	}
	
	@Override
	public int hashCode() {
		return getKey().hashCode();
	}
}
