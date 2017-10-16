package librarymanager.user;

import java.io.Serializable;

public enum UserRole implements Serializable {
	Librarian ("Librarian"), Admin ("Admin"), Both ("All");
	
	private  final String role;
	UserRole(String role){
		this.role = role;
	}
	
	public String getUserRole() {
		return this.role;
	}
	
	@Override
	public String toString() {
		return role;
	}
	
	public static void main(String[] args) {
		System.out.println(UserRole.Librarian.getClass());
	}
}
