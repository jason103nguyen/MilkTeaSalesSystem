package fa.training.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;

import fa.training.entities.Customer;
import fa.training.entities.MilkTea;
import fa.training.entities.Order;
import fa.training.entities.OrderMilkTea;
import fa.training.entities.Store;
import fa.training.utils.HibernateUtil;

public class OrderMilkTeaDaoTest {

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
		MilkTea milkTea = new MilkTea("Tran chau", 20000);
		milkTea.setId(1);
		OrderMilkTea orderMilkTea = new OrderMilkTea(order, milkTea, 2);
		
		OrderDAO odao = new OrderDAO();
		StoreDAO sdao = new StoreDAO();
		CustomerDAO cdao = new CustomerDAO();
		MilkTeaDAO mdao = new MilkTeaDAO();
		OrderMilkTeaDAO omdao = new OrderMilkTeaDAO();
		
		sdao.create(store);
		cdao.create(customer);
		odao.create(order);
		mdao.create(milkTea);
		omdao.create(orderMilkTea);
		
		OrderMilkTea orderMilkTeaOuput = omdao.readOne(orderMilkTea.getId());
		
		assertNotNull(orderMilkTeaOuput.getId());
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
		MilkTea milkTea = new MilkTea("Tran chau", 20000);
		milkTea.setId(1);
		OrderMilkTea orderMilkTea = new OrderMilkTea(order, milkTea, 2);
		
		OrderDAO odao = new OrderDAO();
		StoreDAO sdao = new StoreDAO();
		CustomerDAO cdao = new CustomerDAO();
		MilkTeaDAO mdao = new MilkTeaDAO();
		OrderMilkTeaDAO omdao = new OrderMilkTeaDAO();
		
		sdao.create(store);
		cdao.create(customer);
		odao.create(order);
		mdao.create(milkTea);
		omdao.create(orderMilkTea);
		
		List<OrderMilkTea> orderMilkTeaList = omdao.readAll();
		assertEquals(orderMilkTeaList.size(), 1);
		OrderMilkTea orderMilkTea1 = omdao.readOne(1);
		assertNotNull(orderMilkTea1);
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
		MilkTea milkTea = new MilkTea("Tran chau", 20000);
		milkTea.setId(1);
		OrderMilkTea orderMilkTea = new OrderMilkTea(order, milkTea, 2);
		
		OrderDAO odao = new OrderDAO();
		StoreDAO sdao = new StoreDAO();
		CustomerDAO cdao = new CustomerDAO();
		MilkTeaDAO mdao = new MilkTeaDAO();
		OrderMilkTeaDAO omdao = new OrderMilkTeaDAO();
		
		sdao.create(store);
		cdao.create(customer);
		odao.create(order);
		mdao.create(milkTea);
		omdao.create(orderMilkTea);
		
		assertEquals(2, orderMilkTea.getQuantity());
		orderMilkTea.setQuantity(1);
		omdao.update(orderMilkTea);
		OrderMilkTea orderMilkTeaUpdate = omdao.readOne(1);
		assertEquals(1, orderMilkTeaUpdate.getQuantity());
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
		MilkTea milkTea = new MilkTea("Tran chau", 20000);
		milkTea.setId(1);
		OrderMilkTea orderMilkTea = new OrderMilkTea(order, milkTea, 2);
		
		OrderDAO odao = new OrderDAO();
		StoreDAO sdao = new StoreDAO();
		CustomerDAO cdao = new CustomerDAO();
		MilkTeaDAO mdao = new MilkTeaDAO();
		OrderMilkTeaDAO omdao = new OrderMilkTeaDAO();
		
		sdao.create(store);
		cdao.create(customer);
		odao.create(order);
		mdao.create(milkTea);
		omdao.create(orderMilkTea);
		
		omdao.delete(1);
		assertNull(omdao.readOne(1));
	}
}
