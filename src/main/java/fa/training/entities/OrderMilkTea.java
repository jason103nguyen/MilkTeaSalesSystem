/*
 * (C) Copyright 2021 Fresher Academy. All Right Reserved .
 *
 * @author pc Pham Tran Gia Huy
 * @date Sep 29, 2021
 * @version 1.0
 */
package fa.training.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fa.training.dto.OrderMilkTeaDTO;

@Entity
@Table(name = "order_milktea")
public class OrderMilkTea {
	
	@Id
	@Column(name = "order_milktea_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
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
	
	public OrderMilkTea(OrderMilkTeaDTO dto) {
		this.id = dto.getId();
		this.milkTea = dto.getMilkTea();
		this.order = dto.getOrder();
		this.quantity = dto.getQuantity();
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

	@Override
	public String toString() {
		return "OrderMilkTea [id=" + id + ", orderId=" + order.getId() + ", milkTeaId=" + milkTea.getId() + ", quantity=" + quantity + "]";
	}

}
