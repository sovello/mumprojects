package librarymanager.models;

@SuppressWarnings("serial")
public class Address implements Model{
	private String street, city, state, zip;
	public Address() {
		
	}
	public Address(String street, String city, String state, String zip) {
		this();
		this.street = street;
		this.city  = city;
		this.state = state;
		this.zip = zip;
	}
	
	
	public String getStreet() {
		return this.street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Override
	public String toString() {
		return String.format("%s%n %s, %s. %s", this,street, 
				this.city, this.state, this.zip);
	}

	@Override
	public String getSaveType() {
		return "Addresses";
	}

	@Override
	public String getKey() {
		return String.format("%s-%s-%s", street, state, zip);
	}
	
	@Override
	public int hashCode() {
		return getKey().hashCode();
	}

}
