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
import fa.training.service.MilkTeaService;
import fa.training.service.OrderMilkTeaService;
import fa.training.service.OrderService;
import fa.training.service.OrderToppingService;
import fa.training.service.StoreService;
import fa.training.service.ToppingService;

public class Main {

	public static void main(String[] args) throws Exception {
		
		CustomerService  cs = new CustomerService();
		MilkTeaService ms = new MilkTeaService();
		StoreService ss = new StoreService();
		ToppingService ts = new ToppingService();
		OrderService os = new OrderService();
		OrderToppingService ots = new OrderToppingService();
		OrderMilkTeaService oms = new OrderMilkTeaService();
		
		cs.addFromExcel("MilkTeaSalesSystem.xlsx", "Customer");
		ms.addFromExcel("MilkTeaSalesSystem.xlsx", "MilkTea");
		ss.addFromExcel("MilkTeaSalesSystem.xlsx", "Store");
		ts.addFromExcel("MilkTeaSalesSystem.xlsx", "Topping");
		os.addFromExcel("MilkTeaSalesSystem.xlsx", "Order");
		ots.addFromExcel("MilkTeaSalesSystem.xlsx", "OrderTopping");
		oms.addFromExcel("MilkTeaSalesSystem.xlsx", "OrderMilkTea");
		
//		cs.create(c_1);
//		cs.create(c_2);
//		
//		List<CustomerDTO> list = cs.readAll();
//		System.out.println(Arrays.asList(list).toString());
	}
}
