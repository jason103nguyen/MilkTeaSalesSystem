package fa.training.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import fa.training.entities.Customer;
import fa.training.entities.Order;
import fa.training.entities.Store;
import fa.training.utils.HibernateUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void createTest() {
        HibernateUtil.refreshNewSessionFactory();

		Store store = new Store("Store_1", "Address_1", true);
		store.setId(1);
		Customer customer = new Customer("Phuong", "Nguyen", "jason103nguyen@gmail.com", "0328150855");
		customer.setId(1);
		Order order = new Order(LocalDate.parse("01/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), true, 100000);
		order.setStore(store);
		order.setCustomer(customer);
		order.setId(1);
		
		OrderDAO odao = new OrderDAO();
		StoreDAO sdao = new StoreDAO();
		CustomerDAO cdao = new CustomerDAO();
		
		sdao.create(store);
		cdao.create(customer);
		odao.create(order);
		
		Order orderOutput = odao.readOne(order.getId());
		
		assertNotNull(orderOutput.getId());
    }

    @Test
    public void readTest() {
    	
    	HibernateUtil.refreshNewSessionFactory();

 		Store store = new Store("Store_1", "Address_1", true);
 		store.setId(1);
 		Customer customer = new Customer("Phuong", "Nguyen", "jason103nguyen@gmail.com", "0328150855");
 		customer.setId(1);
 		Order order = new Order(LocalDate.parse("01/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), true, 100000);
 		order.setStore(store);
 		order.setCustomer(customer);
 		order.setId(1);
 		
 		OrderDAO odao = new OrderDAO();
 		StoreDAO sdao = new StoreDAO();
 		CustomerDAO cdao = new CustomerDAO();
 		
 		sdao.create(store);
 		cdao.create(customer);
 		odao.create(order);
 		List<Order> orderList = odao.readAll();
 		assertEquals(orderList.size(), 1);
 		Order order1 = odao.readOne(1);
 		assertNotNull(order1);
    }

    @Test
    public void updateTest() {
    	HibernateUtil.refreshNewSessionFactory();

 		Store store = new Store("Store_1", "Address_1", true);
 		store.setId(1);
 		Customer customer = new Customer("Phuong", "Nguyen", "jason103nguyen@gmail.com", "0328150855");
 		customer.setId(1);
 		Order order = new Order(LocalDate.parse("01/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), true, 100000);
 		order.setStore(store);
 		order.setCustomer(customer);
 		order.setId(1);
 		
 		OrderDAO odao = new OrderDAO();
 		StoreDAO sdao = new StoreDAO();
 		CustomerDAO cdao = new CustomerDAO();
 		
 		sdao.create(store);
 		cdao.create(customer);
 		odao.create(order);
 		
 		assertEquals(LocalDate.parse("01/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), order.getCreateDate());
 		order.setCreateDate(LocalDate.parse("10/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
 		odao.update(order);
 		Order orderUpdate = odao.readOne(1);
 		assertEquals(LocalDate.parse("10/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), orderUpdate.getCreateDate());
    }

    @Test
    public void deleteTest() {
    	
    	HibernateUtil.refreshNewSessionFactory();

 		Store store = new Store("Store_1", "Address_1", true);
 		store.setId(1);
 		Customer customer = new Customer("Phuong", "Nguyen", "jason103nguyen@gmail.com", "0328150855");
 		customer.setId(1);
 		Order order = new Order(LocalDate.parse("01/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), true, 100000);
 		order.setStore(store);
 		order.setCustomer(customer);
 		order.setId(1);
 		
 		OrderDAO odao = new OrderDAO();
 		StoreDAO sdao = new StoreDAO();
 		CustomerDAO cdao = new CustomerDAO();
 		
 		sdao.create(store);
 		cdao.create(customer);
 		odao.create(order);
 		odao.delete(1);
 		assertNull(odao.readOne(1));
    }
}
