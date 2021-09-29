package fa.training.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "topping")
public class Topping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "topping_id")
	private int toppingId;
	
	@Column(name = "topping_name")
	private String toppingName;
	
	@Column(name = "price")
	private double price;

	public Topping() {}
	
	public Topping(int toppingId, String toppingName, double price) {
		super();
		this.toppingId = toppingId;
		this.toppingName = toppingName;
		this.price = price;
	}

	public int getToppingId() {
		return toppingId;
	}

	public void setToppingId(int toppingId) {
		this.toppingId = toppingId;
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
		return "Topping [toppingId=" + toppingId + ", toppingName=" + toppingName + ", price=" + price + "]";
	}
	
}
