package fa.training.controller;

import java.util.Scanner;

import fa.training.service.CustomerService;
import fa.training.service.MilkTeaService;
import fa.training.service.OrderMilkTeaService;
import fa.training.service.OrderService;
import fa.training.service.OrderToppingService;
import fa.training.service.StoreService;
import fa.training.service.ToppingService;

public class Main {

	public static void main(String[] args) throws Exception {

		CustomerService customerService = new CustomerService();
		MilkTeaService milkTeaService = new MilkTeaService();
		StoreService storeService = new StoreService();
		ToppingService toppingService = new ToppingService();
		OrderService orderService = new OrderService();
		OrderToppingService orderToppingService = new OrderToppingService();
		OrderMilkTeaService orderMilkTeaService = new OrderMilkTeaService();

		while (true) {

			/* Get option from user */
			String sellection = "";
			System.out.println("************** Welcome to program \"MILK TEA SALES SYSTEM\" **************");
			System.out.println("Please choose the option ");
			System.out.println("1. Auto insert data");
			System.out.println("2. Find customer by phone");
			System.out.println("exit: To close program");
			System.out.print("Your choose: ");
			sellection = getString();

			switch (sellection) {
			case "1":

				customerService.addFromExcel("MilkTeaSalesSystem.xlsx", "Customer");
				milkTeaService.addFromExcel("MilkTeaSalesSystem.xlsx", "MilkTea");
				storeService.addFromExcel("MilkTeaSalesSystem.xlsx", "Store");
				toppingService.addFromExcel("MilkTeaSalesSystem.xlsx", "Topping");
				orderService.addFromExcel("MilkTeaSalesSystem.xlsx", "Order");
				orderToppingService.addFromExcel("MilkTeaSalesSystem.xlsx", "OrderTopping");
				orderMilkTeaService.addFromExcel("MilkTeaSalesSystem.xlsx", "OrderMilkTea");
				break;
			
			case "2":
				customerService.findCustomer("MilkTeaSalesSystem.xlsx", "Find_Customer");
				break;

			case "exit":
				return;

			default:
				System.out.println("Wrong intput!");
				break;
			}
		}
	}

	public static String getString() {

		String result = "";
		Scanner scan = new Scanner(System.in);
		result = scan.nextLine();
		return result;
	}

}
