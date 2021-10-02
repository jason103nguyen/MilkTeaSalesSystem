package fa.training.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fa.training.dto.OrderDTO;

@Entity
@Table(name = "order_bill")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int id;

	@Column(name = "create_date")
	private LocalDate createDate;

	@Column(name = "status")
	private boolean status;

	@Column(name = "total_price")
	private double totalPrice;

	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private Set<OrderTopping> setOrderTopping = new HashSet<OrderTopping>();
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Set<OrderMilkTea> setOrderMilkTea = new HashSet<OrderMilkTea>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public Order() {
	}

	public Order(LocalDate createDate, boolean status, double totalPrice) {
		super();
		this.createDate = createDate;
		this.status = status;
		this.totalPrice = totalPrice;
	}
	
	public Order(OrderDTO dto) {
		this.createDate = dto.getCreateDate();
		this.customer = new Customer(dto.getCustomerDto());
		this.id = dto.getId();
		this.status = dto.getStatus();
		this.store = new Store(dto.getStoreDto());
		this.totalPrice = dto.getTotalPrice();
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
	
	@Override
	public int hashCode() {
		return Objects.hash(createDate, customer, id, status, store, totalPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(createDate, other.createDate) && Objects.equals(customer, other.customer)
				&& id == other.id && status == other.status && Objects.equals(store, other.store)
				&& Double.doubleToLongBits(totalPrice) == Double.doubleToLongBits(other.totalPrice);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", createDate=" + createDate + ", status=" + status + ", totalPrice=" + totalPrice
				+ ", storeId=" + store.getId() + ", customerId=" + customer.getId() + "]";
	}

}
