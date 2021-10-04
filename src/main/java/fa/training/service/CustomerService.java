package fa.training.service;

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

    private CustomerDAO customerDAO;
    
    public CustomerService() {
    	this.customerDAO = new CustomerDAO();
    }
    
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

    
    public boolean create(CustomerDTO customerDTO) {
        try {
            Customer customer = new Customer(customerDTO);
            customer.setId(0);
            int newId = (int) customerDAO.create(customer);
            customerDTO.setId(newId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public CustomerDTO readOne(int id) {
        CustomerDTO customerDTO = null;
        try {
            Customer customer = customerDAO.readOne(id);
            if (customer != null) {
                customerDTO = new CustomerDTO(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerDTO;
    }

    public List<CustomerDTO> readAll() {
        List<Customer> customerList = customerDAO.readAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList) {
            customerDTOList.add(new CustomerDTO(customer));
        }
        
        for (CustomerDTO customerDto : customerDTOList) {
        	System.out.println(customerDto.toString());
        }
        
        return customerDTOList;
    }

    public boolean update(CustomerDTO customerDTO) {
        try {
            Customer customer = new Customer(customerDTO);
            customerDAO.update(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(int id) {
        try {
            customerDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public void likeOperator(String field, String value) {
    	
    	List<Customer> customerList = customerDAO.likeOperator(field, value);
    	List<CustomerDTO> customerDtoList = new ArrayList<>();
    	
    	if (customerList.isEmpty()) {
    		System.out.println(String.format("The customer with %s: %s doesn't exist!",field, value));
    		return;
    	}
    	
    	for (Customer customer : customerList) {
    		customerDtoList.add(new CustomerDTO(customer));
		}
    	
    	System.out.println(String.format("Info of customers with %s: %s is: ", field, value));
		for (CustomerDTO customerDto : customerDtoList) {
			System.out.println(customerDto.toString());
		}
    }

	public void find(String pathFile, String sheetName) {
		
		Workbook workbook = ServiceUtil.convertXLSXtoWorkbook(pathFile);
		Sheet sheet = workbook.getSheet(sheetName);
		
		Map<String, String> mapStr = new HashMap<String, String>();
		
		int index = 0;
		for(Row row : sheet) {
			if (index == 0) {
				index++;
				continue;
			}
			
			mapStr.put(row.getCell(0).toString(), row.getCell(1).toString());
		}
		
		likeOperator("phone", mapStr.get("Phone"));
		likeOperator("email", mapStr.get("Email"));
		likeOperator("firstName", mapStr.get("FirstName"));
		likeOperator("lastName", mapStr.get("LastName"));
		
	}

}
