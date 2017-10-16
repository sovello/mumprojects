package librarymanager.util;

import java.time.LocalDate;
import java.util.HashMap;

import librarymanager.models.Book;
import librarymanager.models.BookCopy;
import librarymanager.models.CheckoutRecord;
import librarymanager.models.CheckoutRecordEntry;
import librarymanager.models.LibraryMember;
import librarymanager.storage.StorageAccess;

public class CheckoutRecordsCreator {
	
	@SuppressWarnings("unchecked")
	public CheckoutRecordsCreator() {
		//get all members
		HashMap<String, LibraryMember> members = 
				(HashMap<String, LibraryMember>)StorageAccess.read("LibraryMembers");
		//get all books
		HashMap<String, Book> books = (HashMap<String, Book>)StorageAccess.read("Books");
		//get this member
		String[] memberids = LibraryMembersCreator.getMemberids();
		String[] isbns = BooksCreator.getIsbn();
		LocalDate[] checkoutdates = {
				LocalDate.of(2017, 9, 10),
				LocalDate.of(2017, 9, 23),
				LocalDate.of(2017, 9, 27),
				LocalDate.now(),
				LocalDate.now(),
		};
		
		LocalDate[] returndate = {
				LocalDate.of(2017, 9, 17)
		};
		for( int i = 0; i < 5; i++) {
			Book book = books.get(isbns[i]);
			BookCopy copy = book.getCopies()[0];
			if( i != 0 ) { copy.setNotAvailable(); }
			LibraryMember member = members.get(memberids[i]);
			CheckoutRecordEntry entry = new CheckoutRecordEntry(copy, member);
			if(i == 0) { entry.setReturnDate(returndate[0]); }
			entry.setCheckoutDate(checkoutdates[0]);
			member.addBook(entry);
			CheckoutRecord record = new CheckoutRecord(entry);
			StorageAccess.save(member);
			StorageAccess.save(book);
			StorageAccess.save(record);
		}
		
	}
	public static void main(String[] args) {
		new CheckoutRecordsCreator();
	}
}
