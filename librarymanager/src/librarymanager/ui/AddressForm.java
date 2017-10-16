package librarymanager.ui;

import space.fugit.jfx.FTextField;
import space.fugit.jfx.FVBox;

public class AddressForm implements Form{
	private FTextField street, city, state, zip;
	
	public AddressForm() {
		this.street = new FTextField();
		this.city  = new FTextField();
		this.state = new FTextField();
		this.zip = new FTextField();
	}
	
	public FTextField getStreet() {
		return this.street;
	}

	public FTextField[] getRequiredFields() {
		FTextField[] arr = { street, city, state, zip };
		return arr; 
	}
	
	public FTextField getCity() {
		return city;
	}

	public FTextField getState() {
		return state;
	}

	public FTextField getZip() {
		return zip;
	}
	
	// TODO implement this
	@Override
	public FVBox getForm() {
		return new FVBox();
	}
	@Override
	public String toString() {
		return String.format("%s%n %s, %s. %s", this,street, 
				this.city, this.state, this.zip);
	}
}
