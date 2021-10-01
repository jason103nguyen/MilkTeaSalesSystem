package fa.training.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fa.training.dao.OrderToppingDAO;
import fa.training.dto.OrderToppingDTO;
import fa.training.entities.OrderTopping;

public class OrderToppingService {

	private OrderToppingDAO orderToppingDAO = new OrderToppingDAO();
	
	public Serializable create(OrderToppingDTO orderToppingDTO) {
		
		OrderTopping OrderTopping = new OrderTopping(orderToppingDTO);
		return orderToppingDAO.create(OrderTopping);
	}
	
	public OrderToppingDTO readOne(Serializable id) {
		
		OrderTopping orderTopping = orderToppingDAO.readOne(id);
		OrderToppingDTO orderToppingDTO = new OrderToppingDTO(orderTopping);
		return orderToppingDTO;
	}
	
	public List<OrderToppingDTO> readAll() {
		
		List<OrderTopping> listOrderTopping = orderToppingDAO.readAll();
		List<OrderToppingDTO> listOrderToppingDTO = new ArrayList<OrderToppingDTO>();
		
		for (OrderTopping orderTopping : listOrderTopping) {
			listOrderToppingDTO.add(new OrderToppingDTO(orderTopping));
		}
		return listOrderToppingDTO;
	}
	
	public void update(OrderToppingDTO orderToppingDTO) {
		
		OrderTopping orderTopping = new OrderTopping(orderToppingDTO);
		orderToppingDAO.update(orderTopping);
	}
	
	public void delete(Serializable id) {
		
		orderToppingDAO.delete(id);
	}
}
