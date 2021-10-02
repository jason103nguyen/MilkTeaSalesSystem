package fa.training.dao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fa.training.entities.Customer;
import fa.training.entities.Order;
import fa.training.entities.Store;

public class TestOrderDAO {

//	@Test
//	public void testCreateAndReadOrderDao() {
//		
//		Store store = new Store("Store_1", "Address_1", true);
//		store.setId(1);
//		Customer customer = new Customer("Phuong", "Nguyen", "jason103nguyen@gmail.com", "0328150855");
//		customer.setId(1);
//		Order order = new Order(LocalDate.parse("01/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), true, 100000);
//		order.setStore(store);
//		order.setCustomer(customer);
//		order.setId(1);
//		
//		OrderDAO odao = new OrderDAO();
//		StoreDAO sdao = new StoreDAO();
//		CustomerDAO cdao = new CustomerDAO();
//		
//		sdao.create(store);
//		cdao.create(customer);
//		odao.create(order);
//		
//		Order orderOutput = odao.readOne(order.getId());
//		orderOutput.getCustomer();
//		orderOutput.getStore();
////		
//		Assertions.assertEquals(order, orderOutput);
//	}
}
