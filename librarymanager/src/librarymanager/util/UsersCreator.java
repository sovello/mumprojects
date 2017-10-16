package librarymanager.util;

import librarymanager.user.*;
import librarymanager.storage.StorageAccess;

public class UsersCreator {
	
	String[] users = {"librarian", "both", "admin"};
	
	String[] passwords = {"librarian", "both", "admin"};
	
	UserRole[] roles = {UserRole.Librarian, UserRole.Both, UserRole.Admin };
	
	public UsersCreator() {
		for(int i = 0; i < users.length; i++) {
			StorageAccess.save(new User(users[i], passwords[i], roles[i]));
		}
	}
	
	public static void main(String[] args) {
		new UsersCreator();
	}
}
