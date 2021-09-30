package fa.training.dto;

import java.time.LocalDate;

import fa.training.entities.Customer;
import fa.training.entities.Order;
import fa.training.entities.Store;

public class OrderDTO {

	private int id;
	private LocalDate createDate;
	private boolean status;
	private double totalPrice;
	private StoreDTO storeDto;
	private CustomerDTO customerDto;
	
	public OrderDTO() {}

	public OrderDTO(LocalDate createDate, boolean status, double totalPrice) {
		super();
		this.createDate = createDate;
		this.status = status;
		this.totalPrice = totalPrice;
	}
	
	public OrderDTO(Order entity) {
		this.createDate = entity.getCreateDate();
		this.customerDto = new CustomerDTO(entity.getCustomer());
		this.storeDto = new StoreDTO(entity.getStore());
		this.id = entity.getId();
		this.status = entity.getStatus();
		this.totalPrice = entity.getTotalPrice();
	}

	public StoreDTO getStoreDto() {
		return storeDto;
	}

	public void setStoreDto(StoreDTO storeDto) {
		this.storeDto = storeDto;
	}

	public CustomerDTO getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDTO customerDto) {
		this.customerDto = customerDto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", createDate=" + createDate + ", status=" + status + ", totalPrice=" + totalPrice
				+ ", storeId=" + storeDto.getId() + ", customerId=" + customerDto.getId() + "]";
	}
	
}
