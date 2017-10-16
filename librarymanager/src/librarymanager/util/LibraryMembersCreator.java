package librarymanager.util;

import librarymanager.models.Address;
import librarymanager.models.LibraryMember;
import librarymanager.storage.StorageAccess;

public class LibraryMembersCreator {
	private static String[] people = {"Yee Bruso", "Nella Ostlund", "Hyo Lair", "Cheri Zavala", "Cheyenne Glass"};
	private static String[] ids = {"123", "1234", "12345", "123456", "1234567"};
	String[][] ad = {
			{"123 6th St.", "Melbourne", "FL", "32904"},
			{"70 Bowman St.", "South Windsor", "CT", "06074"},
			{"514 S. Magnolia St.", "Orlando", "FL", "32806"},
			{"71 Pilgrim Avenue", "Chevy Chase", "MD", "20815"},
			{"4 Goldfield Rd.", "Honululu", "HI", "96815"}
	};
	
	public LibraryMembersCreator() {
		for( int i = 0; i < people.length; i++ ) {
			String[] names = people[0].split("\\s");
			Address addr = new Address();
			addr.setCity(ad[i][1]);
			addr.setState(ad[i][2]);
			addr.setStreet(ad[i][0]);
			addr.setZip(ad[i][3]);
			LibraryMember m = new LibraryMember(ids[i], names[0], names[1], "", addr);
			StorageAccess.save(m);
		}
	}
	
	public static String[] getMemberids() {
		return ids;
	}
	
	public static void main(String[] args) {
		new LibraryMembersCreator();
	}
}
