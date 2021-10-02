/*
 * (C) Copyright 2021 Fresher Academy. All Right Reserved .
 *
 * @author pc Pham Tran Gia Huy
 * @date Oct 1, 2021
 * @version 1.0
 */
package fa.training.dao;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

import fa.training.entities.Customer;
import fa.training.entities.Order;
import fa.training.entities.OrderTopping;
import fa.training.entities.Store;
import fa.training.entities.Topping;
import fa.training.utils.HibernateUtil;

public class OrderToppingDAOTestUnint {

	Session session = HibernateUtil.getSessionFactory().openSession();

	@Test
	public void testCreateOrderToppingDAO() {
		try {
			HibernateUtil.refreshNewSessionFactory();

			Customer cus = new Customer("Phuong", "Nguyen", "jason103nguyen@gmail.com", "0328150855");
			CustomerDAO cusDao = new CustomerDAO();
			cusDao.create(cus);
			cusDao.readOne(1);
			assertEquals("jason103nguyen@gmail.com", cus.getEmail());

			Store store = new Store("Store_1", "HCM", true);
			StoreDAO storeDao = new StoreDAO();
			storeDao.create(store);
			storeDao.readOne(1);
			assertEquals("HCM", store.getAddress());

			Order order1 = new Order();
			order1.setCustomer(cus);
			order1.setStore(store);
			order1.setId(1);
			order1.setStatus(true);
			order1.setTotalPrice(100000);
			order1.setCreateDate(LocalDate.of(2021, Month.OCTOBER, 02));
			OrderDAO orderDao = new OrderDAO();
			orderDao.create(order1);
			orderDao.readOne(1);
			assertEquals((long)100000, (long)order1.getTotalPrice());

			Topping topping = new Topping("Qua Vai", 12000);
			ToppingDAO topDao = new ToppingDAO();
			topDao.create(topping);
			topDao.readOne(1);
			assertEquals("Qua Vai", topping.getToppingName());

			OrderTopping orderTop = new OrderTopping(order1, topping, 2);
			OrderToppingDAO OrderTopDao = new OrderToppingDAO();
			OrderTopDao.create(orderTop);
			OrderTopDao.readOne(1);
			assertEquals((long)100000, (long)order1.getTotalPrice());
			assertEquals("Qua Vai", topping.getToppingName());
			assertEquals(2, orderTop.getQuantity());

			session.getTransaction().rollback();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Test
	public void testReadAllOrderToppingDAO() {
		try {
			Customer cus = new Customer("Phuong", "Nguyen", "jason103nguyen@gmail.com", "0328150855");
			CustomerDAO cusDao = new CustomerDAO();
			cusDao.create(cus);
			cusDao.readOne(1);
			assertEquals("jason103nguyen@gmail.com", cus.getEmail());

			Store store = new Store("Store_1", "HCM", true);
			StoreDAO storeDao = new StoreDAO();
			storeDao.create(store);
			storeDao.readOne(1);
			assertEquals("HCM", store.getAddress());

			Order order1 = new Order();
			order1.setCustomer(cus);
			order1.setStore(store);
			order1.setId(1);
			order1.setStatus(true);
			order1.setTotalPrice(100000);
			order1.setCreateDate(LocalDate.of(2021, Month.OCTOBER, 02));
			OrderDAO orderDao = new OrderDAO();
			orderDao.create(order1);
			orderDao.readOne(1);
			assertEquals((long)100000, (long)order1.getTotalPrice());

			Topping topping = new Topping("Qua Dau", 19000);
			ToppingDAO topDao = new ToppingDAO();
			topDao.create(topping);
			topDao.readOne(1);
			assertEquals("Qua Dau", topping.getToppingName());

			OrderTopping orderTop = new OrderTopping(order1, topping, 2);
			OrderToppingDAO OrderTopDao = new OrderToppingDAO();
			OrderTopDao.create(orderTop);
			List<OrderTopping> listOrderTop = OrderTopDao.readAll();
			assertEquals(listOrderTop.size(), 1);
			assertEquals((long)100000, (long)order1.getTotalPrice());
			assertEquals("Qua Dau", topping.getToppingName());
			assertEquals(2, orderTop.getQuantity());

			session.getTransaction().rollback();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Test
	public void testUpdateOrderToppingDAO() {
		HibernateUtil.refreshNewSessionFactory();
		try {
			Customer cus = new Customer("Phuong", "Nguyen", "jason103nguyen@gmail.com", "0328150855");
			CustomerDAO cusDao = new CustomerDAO();
			cusDao.create(cus);
			cusDao.readOne(1);
			assertEquals("0328150855", cus.getPhone());

			Store store = new Store("Store_1", "HCM", true);
			StoreDAO storeDao = new StoreDAO();
			storeDao.create(store);
			storeDao.readOne(1);
			assertEquals("Store_1", store.getStoreName());

			Order order = new Order(LocalDate.of(2021, Month.OCTOBER, 2), false, 210000);
			order.setCustomer(cus);
			order.setStore(store);
			OrderDAO orderDao = new OrderDAO();
			orderDao.create(order);
			orderDao.readOne(1);
			assertEquals(LocalDate.of(2021, Month.OCTOBER, 2), order.getCreateDate());

			Topping topping = new Topping("Chocolate", 25000);
			ToppingDAO topDao = new ToppingDAO();
			topDao.create(topping);
			topDao.readOne(1);
			assertEquals("Chocolate", topping.getToppingName());

			OrderTopping orderTop = new OrderTopping(order, topping, 2);
			OrderToppingDAO orderTopDao = new OrderToppingDAO();
			orderTopDao.create(orderTop);
			assertEquals(2, orderTop.getQuantity());
			orderTop.setQuantity(3);
			orderTopDao.update(orderTop);
			OrderTopping orderTopUpdate = orderTopDao.readOne(1);
			assertEquals(3, orderTopUpdate.getQuantity());
			session.getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
	
	@Test
	public void testDeletedOrderTopping() {
		HibernateUtil.refreshNewSessionFactory();
		try {
			Customer cus = new Customer("Phuong", "Nguyen", "jason103nguyen@gmail.com", "0328150855");
			CustomerDAO cusDao = new CustomerDAO();
			cusDao.create(cus);
			cusDao.readOne(1);
			assertEquals("0328150855", cus.getPhone());

			Store store = new Store("Store_1", "HCM", true);
			StoreDAO storeDao = new StoreDAO();
			storeDao.create(store);
			storeDao.readOne(1);
			assertEquals("Store_1", store.getStoreName());

			Order order = new Order(LocalDate.of(2021, Month.OCTOBER, 2), false, 210000);
			order.setCustomer(cus);
			order.setStore(store);
			OrderDAO orderDao = new OrderDAO();
			orderDao.create(order);
			orderDao.readOne(1);
			assertEquals(LocalDate.of(2021, Month.OCTOBER, 2), order.getCreateDate());

			Topping topping = new Topping("Chocolate", 25000);
			ToppingDAO topDao = new ToppingDAO();
			topDao.create(topping);
			topDao.readOne(1);
			assertEquals("Chocolate", topping.getToppingName());

			OrderTopping orderTop = new OrderTopping(order, topping, 2);
			OrderToppingDAO orderTopDao = new OrderToppingDAO();
			orderTopDao.create(orderTop);
			orderTopDao.delete(1);
			assertEquals(null, orderTopDao.readOne(1));
			session.getTransaction().rollback();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

}
