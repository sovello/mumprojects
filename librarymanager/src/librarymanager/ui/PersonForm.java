package librarymanager.ui;

import space.fugit.jfx.*;

public class PersonForm implements Form {
	private FTextField firstName, lastName, phone;
	private AddressForm address;
	public PersonForm() {
		this.firstName = new FTextField();
		this.lastName = new FTextField();
		this.phone = new FTextField();
		this.address = new AddressForm();
	}
	
	public FTextField getFirstName() {
		return this.firstName;
	}
	
	public FTextField getLastName() {
		return this.lastName;
	}
	
	public FTextField getPhone() {
		return this.phone;
	}
	
	public AddressForm getAddress() {
		return this.address;
	}
	
	public FTextField[] getRequiredFields() {
		FTextField[] arr = { firstName, lastName };
		return arr; 
	}
	
	//TODO implement this
	@Override
	public FVBox getForm() {
		return new FVBox();
	}
}
