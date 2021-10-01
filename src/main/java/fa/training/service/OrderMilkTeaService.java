package fa.training.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fa.training.dao.OrderMilkTeaDAO;
import fa.training.dto.OrderMilkTeaDTO;
import fa.training.entities.OrderMilkTea;

public class OrderMilkTeaService {

	private OrderMilkTeaDAO orderMilkTeaDAO = new OrderMilkTeaDAO();
	
	public Serializable create(OrderMilkTeaDTO orderMilkTeaDTO) {
		
		OrderMilkTea orderMilkTea = new OrderMilkTea(orderMilkTeaDTO);
		return orderMilkTeaDAO.create(orderMilkTea);
	}
	
	public OrderMilkTeaDTO readOne(Serializable id) {
		
		OrderMilkTea orderMilkTea = orderMilkTeaDAO.readOne(id);
		OrderMilkTeaDTO orderMilkTeaDTO = new OrderMilkTeaDTO(orderMilkTea);
		return orderMilkTeaDTO;
	}
	
	public List<OrderMilkTeaDTO> readAll() {
		
		List<OrderMilkTea> listOrderMilkTea = orderMilkTeaDAO.readAll();
		List<OrderMilkTeaDTO> listOrderMilkTeaDTO = new ArrayList<OrderMilkTeaDTO>();
		
		for (OrderMilkTea orderMilkTea : listOrderMilkTea) {
			listOrderMilkTeaDTO.add(new OrderMilkTeaDTO(orderMilkTea));
		}
		return listOrderMilkTeaDTO;
	}
	
	public void update(OrderMilkTeaDTO orderMilkTeaDTO) {
		
		OrderMilkTea orderMilkTea = new OrderMilkTea(orderMilkTeaDTO);
		orderMilkTeaDAO.update(orderMilkTea);
	}
	
	public void delete(Serializable id) {
		
		orderMilkTeaDAO.delete(id);
	}
}
