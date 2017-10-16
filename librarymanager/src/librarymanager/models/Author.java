package librarymanager.models;

@SuppressWarnings("serial")
public class Author extends Person implements Model {
	private String credentials;
	
	public Author(String credentials, String firstname, String lastname,
			String phone, Address address) {
		super(firstname, lastname, phone, address);
		this.credentials = credentials;
	}
	
	public Author(String credentials, String firstname, String lastname) {
		super(firstname, lastname);
		this.credentials = credentials;
	}

	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public String getSaveType() {
		return "Authors";
	}

	@Override
	public String getKey() {
		return String.format(
				"%s-%s-%s", 
				this.credentials, this.getFirstName(), this.getLastName());
	}
	
	@Override
	public int hashCode() {
		return getKey().hashCode();
	}
}
