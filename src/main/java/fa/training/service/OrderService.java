package fa.training.service;

import fa.training.dao.OrderDAO;
import fa.training.dao.OrderToppingDAO;
import fa.training.dto.OrderDTO;
import fa.training.dto.ToppingDTO;
import fa.training.entities.Order;
import fa.training.entities.OrderTopping;
import fa.training.entities.Topping;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private OrderDAO orderDAO;
    private OrderToppingDAO orderToppingDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
        this.orderToppingDAO = new OrderToppingDAO();
    }
    
    public void addTopping(OrderDTO orderDTO, ToppingDTO toppingDTO, int quantity) {
    	Order order = new Order(orderDTO);
    	Topping topping = new Topping(toppingDTO);
    	OrderTopping orderTopping = new OrderTopping(order, topping, quantity);
    	orderToppingDAO.create(orderTopping);
    }

    public boolean create(OrderDTO orderDTO) {
        try {
            Order order = new Order(orderDTO);
            order.setId(0);
            int newId = (int) orderDAO.create(order);
            orderDTO.setId(newId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public OrderDTO readOne(int id) {
        OrderDTO orderDTO = null;
        try {
            Order order = orderDAO.readOne(id);
            if (order != null) {
                orderDTO = new OrderDTO(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDTO;
    }

    public List<OrderDTO> readAll() {
        List<Order> orderList = orderDAO.readAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderList) {
            orderDTOList.add(new OrderDTO(order));
        }
        return orderDTOList;
    }

    public boolean update(OrderDTO orderDTO) {
        try {
            Order order = new Order(orderDTO);
            orderDAO.update(order);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(int id) {
        try {
            orderDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
