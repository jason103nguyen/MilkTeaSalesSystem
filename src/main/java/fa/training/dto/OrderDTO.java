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
	private Store store;
	private Customer customer;
	
	public OrderDTO() {}

	public OrderDTO(LocalDate createDate, boolean status, double totalPrice) {
		super();
		this.createDate = createDate;
		this.status = status;
		this.totalPrice = totalPrice;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	
	public void loadFromEntity(Order entity) {
		this.createDate = entity.getCreateDate();
		this.customer = entity.getCustomer();
		this.id = entity.getId();
		this.status = entity.getStatus();
		this.store = entity.getStore();
		this.totalPrice = entity.getTotalPrice();
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", createDate=" + createDate + ", status=" + status + ", totalPrice=" + totalPrice
				+ ", storeId=" + store.getId() + ", customerId=" + customer.getId() + "]";
	}
	
}
