package fa.training.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fa.training.dao.OrderDAO;
import fa.training.dto.OrderDTO;
import fa.training.entities.Order;

public class OrderSevice {

	private OrderDAO orderDAO = new OrderDAO();
	
	public Serializable create(OrderDTO orderDTO) {
		
		Order order = new Order(orderDTO);
		return orderDAO.create(order);
	}
	
	public OrderDTO readOne(Serializable id) {
		
		Order order = orderDAO.readOne(id);
		OrderDTO orderDTO = new OrderDTO(order);
		return orderDTO;
	}
	
	public List<OrderDTO> readAll() {
		
		List<Order> listOrder = orderDAO.readAll();
		List<OrderDTO> listOrderDTO = new ArrayList<OrderDTO>();
		
		for (Order order : listOrder) {
			listOrderDTO.add(new OrderDTO(order));
		}
		return listOrderDTO;
	}
	
	public void update(OrderDTO orderDTO) {
		
		Order order = new Order(orderDTO);
		orderDAO.update(order);
	}
	
	public void delete(Serializable id) {
		
		orderDAO.delete(id);
	}
}
