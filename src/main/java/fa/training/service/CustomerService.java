package fa.training.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import fa.training.dao.CustomerDAO;
import fa.training.dto.CustomerDTO;
import fa.training.entities.Customer;
import fa.training.utils.ServiceUtil;

public class CustomerService {

	private CustomerDAO customerDAO = new CustomerDAO();
	
	public void addFromExcel(String pathFile, String sheetName) throws Exception {
		
		List<CustomerDTO> listCustomerDTO = convertXLSX(pathFile, sheetName);
		
		for (CustomerDTO customerDTO : listCustomerDTO) {
			create(customerDTO);
		}
		
		System.out.println("Adding success");
	}
	
	public List<CustomerDTO> convertXLSX(String pathFile, String sheetName) {
		
		Workbook workbook = ServiceUtil.convertXLSXtoWorkbook(pathFile);
		Sheet sheet = workbook.getSheet(sheetName);
		
		ArrayList<Map<String, String>> listStr = new ArrayList<>();
		
		int index = 0;
		for(Row row : sheet) {
			if (index == 0) {
				index++;
				continue;
			}
			
			Map<String, String> mapRow = new HashMap<String, String>();
			mapRow.put("CustomerId", row.getCell(0).toString());
			mapRow.put("LastName", row.getCell(1).toString());
			mapRow.put("FirstName", row.getCell(2).toString());
			mapRow.put("Email", row.getCell(3).toString());
			mapRow.put("Phone", row.getCell(4).toString());
			
			listStr.add(mapRow);
		}
		
		List<CustomerDTO> listObject = new ArrayList<>();
		
		for(Map<String, String> objectStr : listStr) {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setId((int)Double.parseDouble(objectStr.get("CustomerId")));
			customerDTO.setLastName(objectStr.get("LastName"));
			customerDTO.setFirstName(objectStr.get("FirstName"));
			customerDTO.setEmail(objectStr.get("Email"));
			customerDTO.setPhone(objectStr.get("Phone"));
			
			listObject.add(customerDTO);
		}
		
		return listObject;
	}
	
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
