package fa.training.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import fa.training.dao.CustomerDAO;
import fa.training.dao.MilkTeaDAO;
import fa.training.dao.OrderDAO;
import fa.training.dao.OrderMilkTeaDAO;
import fa.training.dao.OrderToppingDAO;
import fa.training.dao.StoreDAO;
import fa.training.dao.ToppingDAO;
import fa.training.dto.CustomerDTO;
import fa.training.entities.Customer;
import fa.training.entities.MilkTea;
import fa.training.entities.Order;
import fa.training.entities.OrderMilkTea;
import fa.training.entities.OrderTopping;
import fa.training.entities.Store;
import fa.training.entities.Topping;
import fa.training.service.CustomerService;

public class Main {

	public static void main(String[] args) {
		
		CustomerDTO c_1 = new CustomerDTO("Phuong", "Nguyen", "jason103nguyen@gmail.com", "0328150855");
		CustomerDTO c_2 = new CustomerDTO("XXX", "Nguyen", "jason104nguyen@gmail.com", "0328150856");
		CustomerService cs = new CustomerService();
		
		cs.create(c_1);
		cs.create(c_2);
		
		List<CustomerDTO> list = cs.readAll();
		System.out.println(Arrays.asList(list).toString());
	}
}
