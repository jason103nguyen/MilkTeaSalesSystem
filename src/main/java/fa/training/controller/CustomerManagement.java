/*
 * (C) Copyright 2021 Fresher Academy. All Right Reserved .
 *
 * @author pc Pham Tran Gia Huy
 * @date Sep 30, 2021
 * @version 1.0
 */
package fa.training.controller;

import java.util.List;

import fa.training.dao.CustomerDAO;
import fa.training.entities.Customer;
import fa.training.readfile.CustomerExcel;
import fa.training.readfile.ReadFileExcel;



public class CustomerManagement {
	
	public CustomerManagement() {
		
	}
	
	private static CustomerDAO cusDao = new CustomerDAO();
	
	public void createCustomerFromExcel() {
		CustomerExcel cusExcel = new CustomerExcel();
		try {
			List<Customer> listCus = cusExcel.readExcelCustomer(ReadFileExcel.FILE);
			Customer cus = new Customer();
			for(Customer c : listCus) {
				cus.setFirstName(c.getFirstName());
				cus.setLastName(c.getLastName());
				cus.setEmail(c.getEmail());
				cus.setPhone(c.getPhone());
				cusDao.create(cus);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
