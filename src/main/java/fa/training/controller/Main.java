package fa.training.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fa.training.dao.CustomerDAO;
import fa.training.dao.OrderDAO;
import fa.training.dao.StoreDAO;
import fa.training.dto.CustomerDTO;
import fa.training.dto.OrderDTO;
import fa.training.dto.StoreDTO;
import fa.training.dto.ToppingDTO;
import fa.training.entities.Customer;
import fa.training.entities.Order;
import fa.training.entities.Store;
import fa.training.service.CustomerService;
import fa.training.service.OrderService;
import fa.training.service.StoreService;
import fa.training.service.ToppingService;

public class Main {

	public static void main(String[] args) {
		
		StoreDTO s_1 = new StoreDTO("Store_1", "HCM", true);
		CustomerDTO c_1 = new CustomerDTO("Phuong", "Nguyen", "jason103nguyen@gmail.com", "0328150855");
		OrderDTO o_1 = new OrderDTO(LocalDate.parse("30/09/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")) , true, 100000);
		o_1.setCustomerDto(c_1);
		o_1.setStoreDto(s_1);
		ToppingDTO t_1 = new ToppingDTO("Banh plan", 10000);
		
		StoreService ss = new StoreService();
		CustomerService cs = new CustomerService();
		OrderService os = new OrderService();
		ToppingService ts = new ToppingService();
		
		ss.create(s_1);
		cs.create(c_1);
		os.create(o_1);
		ts.create(t_1);
		os.addTopping(o_1, t_1, 10);
		
	}

}
