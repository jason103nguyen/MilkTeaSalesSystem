
package fa.training.dto;

import fa.training.entities.Order;
import fa.training.entities.OrderTopping;
import fa.training.entities.Topping;

public class OrderToppingDTO {

	private int id;
	private OrderDTO orderDto;
	private ToppingDTO toppingDto;
	private int quantity;
	
	public OrderToppingDTO() {}

	public OrderToppingDTO(OrderDTO orderDto, ToppingDTO toppingDto, int quantity) {
		super();
		this.orderDto = orderDto;
		this.toppingDto = toppingDto;
		this.quantity = quantity;
	}
	
	public OrderToppingDTO(OrderTopping entity) {
		this.id = entity.getId();
		this.orderDto = new OrderDTO(entity.getOrder());
		this.toppingDto = new ToppingDTO(entity.getTopping());
		this.quantity = entity.getQuantity();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrderDTO getOrderDto() {
		return orderDto;
	}

	public void setOrderDto(OrderDTO orderDto) {
		this.orderDto = orderDto;
	}

	public ToppingDTO getToppingDto() {
		return toppingDto;
	}

	public void setToppingDto(ToppingDTO toppingDto) {
		this.toppingDto = toppingDto;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "OrderTopping [id=" + id + ", orderDtoId=" + orderDto.getId() + ", toppingDtoId=" + toppingDto.getId() + ", quantity=" + quantity + "]";
	}
	
}