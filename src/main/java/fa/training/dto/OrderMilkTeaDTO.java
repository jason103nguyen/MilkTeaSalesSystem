
package fa.training.dto;

import fa.training.entities.MilkTea;
import fa.training.entities.Order;
import fa.training.entities.OrderMilkTea;

public class OrderMilkTeaDTO {

	private int id;
	private OrderDTO orderDto;
	private MilkTeaDTO milkTeaDto;
	private int quantity;
	
	public OrderMilkTeaDTO() {}

	public OrderMilkTeaDTO(OrderDTO orderDto, MilkTeaDTO milkTeaDto, int quantity) {
		super();
		this.orderDto = orderDto;
		this.milkTeaDto = milkTeaDto;
		this.quantity = quantity;
	}
	
	public OrderMilkTeaDTO(OrderMilkTea entity) {
		this.id = entity.getId();
		this.milkTeaDto = new MilkTeaDTO(entity.getMilkTea());
		this.orderDto = new OrderDTO(entity.getOrder());
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

	public MilkTeaDTO getMilkTeaDto() {
		return milkTeaDto;
	}

	public void setMilkTeaDto(MilkTeaDTO milkTeaDto) {
		this.milkTeaDto = milkTeaDto;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void loadFromEntity(OrderMilkTea entity) {
		this.id = entity.getId();
		this.milkTeaDto = new MilkTeaDTO(entity.getMilkTea());
		this.orderDto = new OrderDTO(entity.getOrder());
		this.quantity = entity.getQuantity();
	}

	@Override
	public String toString() {
		return "OrderMilkTea [id=" + id + ", orderDtoId=" + orderDto.getId() + ", milkTeaDtoId=" + milkTeaDto.getId() + ", quantity=" + quantity + "]";
	}

}
