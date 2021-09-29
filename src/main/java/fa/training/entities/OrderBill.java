package fa.training.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_bill")
public class OrderBill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_bill_id")
	private int orderBillId;
	
	@Column(name = "create_date")
	private LocalDate createDate;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "total_price")
	private double totalPrice;

	public OrderBill() {}
	
	public OrderBill(int orderBillId, LocalDate createDate, boolean status, double totalPrice) {
		super();
		this.orderBillId = orderBillId;
		this.createDate = createDate;
		this.status = status;
		this.totalPrice = totalPrice;
	}

	public int getOrderBillId() {
		return orderBillId;
	}

	public void setOrderBillId(int orderBillId) {
		this.orderBillId = orderBillId;
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
		return "OrderBill [orderBillId=" + orderBillId + ", createDate=" + createDate + ", status=" + status + ", totalPrice="
				+ totalPrice + "]";
	}
	
}
