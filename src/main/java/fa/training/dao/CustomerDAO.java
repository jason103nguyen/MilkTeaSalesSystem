package fa.training.dao;

import fa.training.entities.Customer;

public class CustomerDAO extends GenericDao<Customer> {

    public CustomerDAO() {
        super(Customer.class);
    }
}
