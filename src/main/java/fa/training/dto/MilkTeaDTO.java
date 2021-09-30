package fa.training.dto;

import fa.training.entities.MilkTea;

public class MilkTeaDTO {

	private int id;
	private String name;
	private double price;

	public MilkTeaDTO() {}

	public MilkTeaDTO(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public MilkTeaDTO(MilkTea entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.price = entity.getPrice();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public void loadFromEntity(MilkTea entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.price = entity.getPrice();
	}

	@Override
	public String toString() {
		return "MilkTea [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

}