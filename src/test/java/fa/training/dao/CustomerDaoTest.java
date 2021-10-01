package fa.training.dao;

import fa.training.entities.Customer;
import fa.training.utils.HibernateUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CustomerDaoTest {

    @Test
    public void createTest() {
        HibernateUtil.refreshNewSessionFactory();
        Customer customer = new Customer("lastName", "firstName", "email1@email.com", "0123456788");
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.create(customer);
        assertNotNull(customer.getId());
    }

    @Test
    public void readTest() {
        HibernateUtil.refreshNewSessionFactory();
        Customer customer = new Customer("lastName", "firstName", "email@email.com", "0123456789");
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.create(customer);
        List<Customer> customerList = customerDAO.readAll();
        assertEquals(customerList.size(), 1);
        Customer customer1 = customerDAO.readOne(1);
        assertNotNull(customer1);
    }

    @Test
    public void updateTest() {
        HibernateUtil.refreshNewSessionFactory();
        Customer customer = new Customer("lastName", "firstName", "email@email.com", "0123456789");
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.create(customer);
        assertEquals("lastName", customer.getLastName());
        customer.setLastName("lastName1");
        customerDAO.update(customer);
        Customer updatedCustomer = customerDAO.readOne(1);
        assertEquals("lastName1", updatedCustomer.getLastName());
    }

    @Test
    public void deleteTest() {
        HibernateUtil.refreshNewSessionFactory();
        Customer customer = new Customer("lastName", "firstName", "email@email.com", "0123456789");
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.create(customer);
        customerDAO.delete(1);
        assertNull(customerDAO.readOne(1));
    }
}
