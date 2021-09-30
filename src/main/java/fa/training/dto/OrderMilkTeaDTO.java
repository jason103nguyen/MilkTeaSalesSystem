
package fa.training.dto;

import fa.training.entities.MilkTea;
import fa.training.entities.Order;
import fa.training.entities.OrderMilkTea;

public class OrderMilkTeaDTO {

	private int id;
	private Order order;
	private MilkTea milkTea;
	private int quantity;
	
	public OrderMilkTeaDTO() {}

	public OrderMilkTeaDTO(Order order, MilkTea milkTea, int quantity) {
		super();
		this.order = order;
		this.milkTea = milkTea;
		this.quantity = quantity;
	}
	
	public OrderMilkTeaDTO(OrderMilkTea entity) {
		this.id = entity.getId();
		this.milkTea = entity.getMilkTea();
		this.order = entity.getOrder();
		this.quantity = entity.getQuantity();
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

	public MilkTea getMilkTea() {
		return milkTea;
	}

	public void setMilkTea(MilkTea milkTea) {
		this.milkTea = milkTea;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void loadFromEntity(OrderMilkTea entity) {
		this.id = entity.getId();
		this.milkTea = entity.getMilkTea();
		this.order = entity.getOrder();
		this.quantity = entity.getQuantity();
	}

	@Override
	public String toString() {
		return "OrderMilkTea [id=" + id + ", orderId=" + order.getId() + ", milkTeaId=" + milkTea.getId() + ", quantity=" + quantity + "]";
	}

}
