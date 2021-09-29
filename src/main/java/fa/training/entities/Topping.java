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

@Entity
@Table(name = "topping")
public class Topping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "topping_id")
	private int id;

	@Column(name = "topping_name")
	private String toppingName;

	@Column(name = "price")
	private double price;

	@OneToMany(mappedBy = "topping",cascade = CascadeType.ALL)
	private Set<OrderTopping> setToppingOrder = new HashSet<OrderTopping>();
	public Topping() {
	}

	public Topping(String toppingName, double price) {
		super();
		this.toppingName = toppingName;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToppingName() {
		return toppingName;
	}

	public void setToppingName(String toppingName) {
		this.toppingName = toppingName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Topping [id=" + id + ", toppingName=" + toppingName + ", price=" + price + "]";
	}
	
}
