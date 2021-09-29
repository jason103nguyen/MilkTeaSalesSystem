package fa.training.dao;

import fa.training.entities.Order;

public class OrderDAO extends GenericDao<Order> {

    public OrderDAO() {
        super(Order.class);
    }
}
