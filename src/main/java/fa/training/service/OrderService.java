package fa.training.service;

import fa.training.dao.OrderDAO;
import fa.training.dto.OrderDTO;
import fa.training.entities.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private OrderDAO OrderDAO;

    public OrderService() {
        this.OrderDAO = new OrderDAO();
    }

    public boolean create(OrderDTO orderDTO) {
        try {
            Order order = new Order(orderDTO);
            order.setId(0);
            int newId = (int) OrderDAO.create(order);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public OrderDTO readOne(int id) {
        OrderDTO orderDTO = null;
        try {
            Order order = OrderDAO.readOne(id);
            if (order != null) {
                orderDTO = new OrderDTO(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDTO;
    }

    public List<OrderDTO> readAll() {
        List<Order> orderList = OrderDAO.readAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderList) {
            orderDTOList.add(new OrderDTO(order));
        }
        return orderDTOList;
    }

    public boolean update(OrderDTO orderDTO) {
        try {
            Order order = new Order(orderDTO);
            OrderDAO.update(order);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(int id) {
        try {
            OrderDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
