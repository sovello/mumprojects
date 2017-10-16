package librarymanager.user;

import java.util.HashMap;

import librarymanager.storage.StorageAccess;

public class Authentication {
	
	private static User user;
	public Authentication(User logingUser) {
		user = logingUser;
	}
	
	/**
	 * This method checks if user details are available in the
	 * the database, and sets the role for the user
	 * @param logingUser
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public static boolean login(User logingUser) {
		new Authentication(logingUser);
		//get users list
		HashMap<String, User> users = (HashMap<String, User>)StorageAccess.read("Users");
				
		//System.out.println("get user and set the role from the database");
		//so we can fetch user role for the menu
		
		if( users.get(logingUser.getKey()) == null) return false;
		logingUser.setRole(users.get(logingUser.getKey()).getRole());
		return user.equals(users.get(logingUser.getKey()));
	}
	
	
}
