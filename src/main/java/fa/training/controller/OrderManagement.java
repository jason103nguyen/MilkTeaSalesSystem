/*
 * (C) Copyright 2021 Fresher Academy. All Right Reserved .
 *
 * @author pc Pham Tran Gia Huy
 * @date Sep 30, 2021
 * @version 1.0
 */
package fa.training.controller;

import java.util.List;

import fa.training.dao.OrderDAO;
import fa.training.entities.Order;
import fa.training.readfile.OrderExcel;
import fa.training.readfile.ReadFileExcel;


public class OrderManagement {
	
	public OrderManagement() {
		
	}
	
	private static OrderDAO orderDao = new OrderDAO();
	
	public void createStoreFromExcel() {
		OrderExcel storeExcel = new OrderExcel();
		try {
			List<Order> listOrder = storeExcel.readExcelOrder(ReadFileExcel.FILE);
			Order order = new Order();
			for(Order o : listOrder) {
				order.setCreateDate(o.getCreateDate());
				order.setStatus(o.isStatus());
				order.setTotalPrice(o.getTotalPrice());
				orderDao.create(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
