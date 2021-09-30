package fa.training.dto;

import fa.training.entities.Topping;

public class ToppingDTO {

	private int id;
	private String toppingName;
	private double price;
	
	public ToppingDTO() {}

	public ToppingDTO(String toppingName, double price) {
		super();
		this.toppingName = toppingName;
		this.price = price;
	}
	
	public ToppingDTO(Topping entity) {
		this.id = entity.getId();
		this.price = entity.getPrice();
		this.toppingName = entity.getToppingName();
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

	public void loadFromEntity(Topping entity) {
		this.id = entity.getId();
		this.price = entity.getPrice();
		this.toppingName = entity.getToppingName();
	}
	
	@Override
	public String toString() {
		return "Topping [id=" + id + ", toppingName=" + toppingName + ", price=" + price + "]";
	}
	
}
