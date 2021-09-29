package fa.training.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "order")
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
	
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;
	
	@ManyToOne
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

	public boolean isStatus() {
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
				+ "]";
	}

}
