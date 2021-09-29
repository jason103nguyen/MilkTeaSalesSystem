/*
 * (C) Copyright 2021 Fresher Academy. All Right Reserved .
 *
 * @author pc Pham Tran Gia Huy
 * @date Sep 29, 2021
 * @version 1.0
 */
package fa.training.entities;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_topping")
public class OrderTopping {

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "topping_id")
	private Topping topping;
	
	private int quantity;
	
	public OrderTopping() {
		
	}

	public OrderTopping(Order order, Topping topping, int quantity) {
		super();
		this.order = order;
		this.topping = topping;
		this.quantity = quantity;
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
	
	
}
