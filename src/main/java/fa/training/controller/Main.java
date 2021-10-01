package fa.training.controller;

import fa.training.service.CustomerService;
import fa.training.service.MilkTeaService;
import fa.training.service.OrderMilkTeaService;
import fa.training.service.OrderService;
import fa.training.service.OrderToppingService;
import fa.training.service.StoreService;
import fa.training.service.ToppingService;

public class Main {

	public static void main(String[] args) throws Exception {
//		
//		StoreDTO s_1 = new StoreDTO("Store_1", "HCM", true);
//		CustomerDTO c_1 = new CustomerDTO("Phuong", "Nguyen", "jason103nguyen@gmail.com", "0328150855");
//		OrderDTO o_1 = new OrderDTO(LocalDate.parse("30/09/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")) , true, 100000);
//		o_1.setCustomerDto(c_1);
//		o_1.setStoreDto(s_1);
//		ToppingDTO t_1 = new ToppingDTO("Banh plan", 10000);
//		
//		StoreService ss = new StoreService();
//		CustomerService cs = new CustomerService();
//		OrderService os = new OrderService();
//		ToppingService ts = new ToppingService();
//		
//		ss.create(s_1);
//		cs.create(c_1);
//		os.create(o_1);
//		ts.create(t_1);
//		os.addTopping(o_1, t_1, 10);
		
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
		
	}

}
