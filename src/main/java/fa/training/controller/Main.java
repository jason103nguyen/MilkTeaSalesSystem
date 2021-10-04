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
			System.out.println("2. Find customer by FIELDs");
			System.out.println("3. Find Store by FIELDs");
			System.out.println("4. Find Order by FIELDs");
			System.out.println("5. Find MilkTea by FIELDs");
			System.out.println("6. Find Topping by FIELDs");
			System.out.println("7. Read all customers");
			System.out.println("8. Read all stores");
			System.out.println("9. Read all orders");
			System.out.println("10. Read all toppings");
			System.out.println("11. Read all milkteas");
			System.out.println("12. Update a customer");
			System.out.println("13. Update a store");
			System.out.println("14. Update a order");
			System.out.println("15. Update a topping");
			System.out.println("16. Update a milkTea");
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
				customerService.find("MilkTeaSalesSystem.xlsx", "Find_Customer");
				break;
				
			case "3":
				storeService.find("MilkTeaSalesSystem.xlsx", "Find_Store");
				break;

			case "4":
				orderService.find("MilkTeaSalesSystem.xlsx", "Find_Order");
				break;

			case "5":
				milkTeaService.find("MilkTeaSalesSystem.xlsx", "Find_MilkTea");
				break;

			case "6":
				toppingService.find("MilkTeaSalesSystem.xlsx", "Find_Topping");
				break;

			case "7":
				customerService.readAll();
				break;
				
			case "8":
				storeService.readAll();
				break;

			case "9":
				orderService.readAll();
				break;

			case "10":
				toppingService.readAll();
				break;

			case "11":
				milkTeaService.readAll();
				break;
				
			case "12":
				customerService.updateFromExcel("MilkTeaSalesSystem.xlsx", "Update_Customer");
				break;
			case "13":
				storeService.updateFromExcel("MilkTeaSalesSystem.xlsx", "Update_Store");
				break;
			case "14":
				orderService.updateFromExcel("MilkTeaSalesSystem.xlsx", "Update_Order");
				break;
			case "15":
				toppingService.updateFromExcel("MilkTeaSalesSystem.xlsx", "Update_Topping");
				break;
			case "16":
				milkTeaService.updateFromExcel("MilkTeaSalesSystem.xlsx", "Update_MilkTea");
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
