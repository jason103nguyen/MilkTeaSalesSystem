package fa.training.service;

import java.io.Serializable;

import fa.training.dao.CustomerDAO;
import fa.training.dto.CustomerDTO;
import fa.training.entities.Customer;

public class CustomerService {

	private CustomerDAO customerDAO = new CustomerDAO();
	
	public Serializable create(CustomerDTO customerDTO) {
		
		Customer customer = new Customer();
		customer.loadFromDto(customerDTO);
		return customerDAO.create(customer);
	}
	
}
