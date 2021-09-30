package fa.training.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fa.training.dao.CustomerDAO;
import fa.training.dto.CustomerDTO;
import fa.training.entities.Customer;

public class CustomerService {

	private CustomerDAO customerDAO = new CustomerDAO();
	
	public Serializable create(CustomerDTO customerDTO) {
		
		Customer customer = new Customer(customerDTO);
		return customerDAO.create(customer);
	}
	
	public CustomerDTO readOne(Serializable id) {
		
		Customer customer = customerDAO.readOne(id);
		CustomerDTO customerDTO = new CustomerDTO(customer);
		return customerDTO;
	}
	
	public List<CustomerDTO> readAll() {
		
		List<Customer> listCustomer = customerDAO.readAll();
		List<CustomerDTO> listCustomerDTO = new ArrayList<CustomerDTO>();
		
		for (Customer customer : listCustomer) {
			listCustomerDTO.add(new CustomerDTO(customer));
		}
		return listCustomerDTO;
	}
	
	public void update(CustomerDTO customerDTO) {
		
		Customer customer = new Customer(customerDTO);
		customerDAO.update(customer);
	}
	
	public void delete(Serializable id) {
		
		customerDAO.delete(id);
	}
}
