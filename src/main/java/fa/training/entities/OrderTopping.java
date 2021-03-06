package fa.training.entities;


import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import fa.training.dto.OrderToppingDTO;

@Entity
@Table(name = "order_topping")
public class OrderTopping {

	@Id
	@Column(name = "order_topping_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "topping_id")
	private Topping topping;
	
	private int quantity;
	
	public OrderTopping() {}

	public OrderTopping(Order order, Topping topping, int quantity) {
		super();
		this.order = order;
		this.topping = topping;
		this.quantity = quantity;
	}
	
	public OrderTopping(OrderToppingDTO dto) {
		this.id = dto.getId();
		this.order = new Order(dto.getOrderDto());
		this.topping = new Topping(dto.getToppingDto());
		this.quantity = dto.getQuantity();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Topping getTopping() {
		return topping;
	}

	public void setTopping(Topping topping) {
		this.topping = topping;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderTopping other = (OrderTopping) obj;
		return id == other.id && Objects.equals(order, other.order) && quantity == other.quantity
				&& Objects.equals(topping, other.topping);
	}

	@Override
	public String toString() {
		return "OrderTopping [id=" + id + ", orderId=" + order.getId() + ", toppingId=" + topping.getId() + ", quantity=" + quantity + "]";
	}
	
}