package librarymanager.user;

import librarymanager.models.Model;

@SuppressWarnings("serial")
public class User implements Model {

	private String username, password;
	private UserRole role;
	
	public User(String username, String password, UserRole role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	@Override
	public boolean equals(Object object) {
		if( object == null ) return false;
		if( !(object instanceof User)) return false;
		User user = (User)object;
		return ( this.getKey().equals(user.getKey()) &&
				this.getPassword().equals(user.getPassword()));
	}
	
	@Override
	public String toString() {
		return this.username;
	}
	
	@Override
	public String getSaveType() {
		return "Users";
	}

	@Override
	public String getKey() {
		return username;
	}
}
