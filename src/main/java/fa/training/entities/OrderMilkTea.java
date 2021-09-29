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
@Table(name = "order_milktea")
public class OrderMilkTea {
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "milk_tea_id")
	private MilkTea milkTea;
	
	private int quantity;
	
	public OrderMilkTea() {
		
	}

	public OrderMilkTea(Order order, MilkTea milkTea, int quantity) {
		super();
		this.order = order;
		this.milkTea = milkTea;
		this.quantity = quantity;
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
	
	

}
