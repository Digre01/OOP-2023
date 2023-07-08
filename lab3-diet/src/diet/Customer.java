package diet;


public class Customer {
	
	private String firstName,lastName,email,phone;
	
	
	public Customer(String name, String surname, String email, String phone) {
		this.firstName = name;
		this.lastName = surname;
		this.email = email;
		this.phone = phone;
		
	}
	
	
	public String getLastName() {
		return lastName;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void SetEmail(String email) {
		this.email = email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return this.firstName + " " + this.lastName;
	}

	
}
