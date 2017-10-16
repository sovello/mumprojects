package librarymanager.models;

import java.time.LocalDate;

import librarymanager.storage.StorageAccess;

@SuppressWarnings("serial")
public class CheckoutRecord implements Model{
	private CheckoutRecordEntry entry;
	
	public CheckoutRecord(CheckoutRecordEntry entry) {
		this.entry = entry;
	}
	
	@Override
	public String getSaveType() {
		return "CheckoutRecords";
	}
	
	public CheckoutRecordEntry getEntry() {
		return entry;
	}
	//ID-ISBN-MEMBERID
	@Override
	public String getKey() {
		return String.format("%s", entry.getKey());
	}
	
	@Override
	public int hashCode() {
		return getKey().hashCode();
	}

	public static void confirmCheckout(BookCopy copy, LibraryMember member) {
		CheckoutRecordEntry entry = new CheckoutRecordEntry(copy, member);
		member.addBook(entry);
		CheckoutRecord record = new CheckoutRecord(entry);
		StorageAccess.save(record);
		StorageAccess.save(member);
	}
	
	public void returnBook(CheckoutRecordEntry checkoutEntry) {
		checkoutEntry.setReturnDate(LocalDate.now());
		StorageAccess.save(new CheckoutRecord(checkoutEntry));
	}
	
}
