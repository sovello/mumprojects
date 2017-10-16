package librarymanager.models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Person implements Serializable {
	private String firstName, lastName, phone;
	private Address address;
	public Person(String firstName, String lastName, String phone, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
	}
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s%n Mobile: %s", this.lastName.toUpperCase(), this.firstName,
				this.phone);
	}
}
