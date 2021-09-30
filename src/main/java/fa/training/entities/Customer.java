package fa.training.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fa.training.dto.CustomerDTO;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int id;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "phone", unique = true)
	private String phone;

	@OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
	private Set<Order> orders = new HashSet<Order>();
	
	public Customer() {
	}

	public Customer(String lastName, String firstName, String email, String phone) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
	}
	
	public Customer(CustomerDTO dto) {
		this.id = dto.getId();
		this.firstName = dto.getFirstName();
		this.lastName = dto.getLastName();
		this.phone = dto.getPhone();
		this.email = dto.getEmail();
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
	
	public void loadFromDto(CustomerDTO dto) {
		this.id = dto.getId();
		this.firstName = dto.getFirstName();
		this.lastName = dto.getLastName();
		this.phone = dto.getPhone();
		this.email = dto.getEmail();
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
				+ ", phone=" + phone + "]";
	}
	
}
