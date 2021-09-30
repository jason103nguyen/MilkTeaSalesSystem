
package fa.training.dto;

import fa.training.entities.Order;
import fa.training.entities.OrderTopping;
import fa.training.entities.Topping;

public class OrderToppingDTO {

	private int id;
	private Order order;
	private Topping topping;
	private int quantity;
	
	public OrderToppingDTO() {}

	public OrderToppingDTO(Order order, Topping topping, int quantity) {
		super();
		this.order = order;
		this.topping = topping;
		this.quantity = quantity;
	}
	
	public OrderToppingDTO(OrderTopping entity) {
		this.id = entity.getId();
		this.order = entity.getOrder();
		this.quantity = entity.getQuantity();
		this.topping = entity.getTopping();
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

	public void loadFromEntity(OrderTopping entity) {
		this.id = entity.getId();
		this.order = entity.getOrder();
		this.quantity = entity.getQuantity();
		this.topping = entity.getTopping();
	}
	
	@Override
	public String toString() {
		return "OrderTopping [id=" + id + ", orderId=" + order.getId() + ", toppingId=" + topping.getId() + ", quantity=" + quantity + "]";
	}
	
}