package fa.training.dto;

import fa.training.entities.Customer;

public class CustomerDTO {

	private int id;
	private String lastName;
	private String firstName;
	private String email;
	private String phone;
	
	public CustomerDTO() {}

	public CustomerDTO(String lastName, String firstName, String email, String phone) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
	}
	
	public CustomerDTO(Customer entity) {
		this.id = entity.getId();
		this.firstName = entity.getFirstName();
		this.lastName = entity.getLastName();
		this.phone = entity.getPhone();
		this.email = entity.getEmail();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
				+ ", phone=" + phone + "]";
	}
	
}
