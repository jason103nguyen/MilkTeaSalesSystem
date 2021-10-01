package fa.training.controller;

//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//import fa.training.dao.CustomerDAO;
//import fa.training.dao.OrderDAO;
//import fa.training.dao.StoreDAO;
//import fa.training.dto.CustomerDTO;
//import fa.training.dto.OrderDTO;
//import fa.training.dto.StoreDTO;
//import fa.training.dto.ToppingDTO;
//import fa.training.entities.Customer;
//import fa.training.entities.Order;
//import fa.training.entities.Store;
//import fa.training.service.CustomerService;
//import fa.training.service.OrderService;
//import fa.training.service.StoreService;
//import fa.training.service.ToppingService;

public class Main {

	public static void main(String[] args) {
		Store store = new Store();
		StoreManagement sm = new StoreManagement();
		sm.createStoreFromExcel();
	}

}
