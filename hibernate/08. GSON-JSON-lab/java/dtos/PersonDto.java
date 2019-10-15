package dtos;



import entities.Address;


public class PersonDto {

	private long id;
    private String firstName;
    private String lastName;
    private String addressStreet;


    
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	@Override
    public String toString() {
    	return String.format("ID = %d%nName = %s %s%nAddress = %s",
    			this.getId(), this.getFirstName(), this.getLastName(), this.getAddressStreet());
    }

	

	
}
