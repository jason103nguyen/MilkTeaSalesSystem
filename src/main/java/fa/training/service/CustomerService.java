package fa.training.service;

import fa.training.dao.CustomerDAO;
import fa.training.dto.CustomerDTO;
import fa.training.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private CustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = new CustomerDAO();
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

}
