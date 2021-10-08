package fa.training.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fa.training.dto.MilkTeaDTO;

@Entity
@Table(name = "milk_tea")
public class MilkTea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "milk_tea_id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private double price;
	
	@OneToMany(mappedBy = "milkTea", cascade = CascadeType.ALL)
	private Set<OrderMilkTea> setMilkTeaOrder;

	public MilkTea() {}

	public MilkTea(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public MilkTea(MilkTeaDTO dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.price = dto.getPrice();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name, price, setMilkTeaOrder);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MilkTea other = (MilkTea) obj;
		return id == other.id && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(setMilkTeaOrder, other.setMilkTeaOrder);
	}

	public Set<OrderMilkTea> getSetMilkTeaOrder() {
		return setMilkTeaOrder;
	}

	public void setSetMilkTeaOrder(Set<OrderMilkTea> setMilkTeaOrder) {
		this.setMilkTeaOrder = setMilkTeaOrder;
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

	@Override
	public String toString() {
		return "MilkTea [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

}