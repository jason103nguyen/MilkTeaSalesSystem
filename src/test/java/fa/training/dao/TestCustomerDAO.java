package fa.training.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fa.training.entities.Customer;
import fa.training.entities.Order;
import fa.training.entities.Store;

public class TestCustomerDAO {

	@Test
	public void testCreateCustomerDao() {
		
		Customer customer = new Customer("Phuong", "Nguyen", "jason103nguyen@gmail.com", "0328150855");
		customer.setId(1);
		
		CustomerDAO cdao = new CustomerDAO();
		
		cdao.create(customer);
		
		Customer customerOutput = cdao.readOne(customer.getId());

		Assertions.assertEquals(customer, customerOutput);
	}
}
