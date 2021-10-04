package fa.training.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import fa.training.dao.OrderDAO;
import fa.training.dto.CustomerDTO;
import fa.training.dto.OrderDTO;
import fa.training.dto.StoreDTO;
import fa.training.entities.Order;
import fa.training.utils.ServiceUtil;

public class OrderService {

    private OrderDAO orderDAO;
    
    public OrderService() {
        this.orderDAO = new OrderDAO();
    }

    /**
     * Add data from file excel
     * @param pathFile path name of file excel
     * @param sheetName sheet contain data
     * @throws Exception throw if convert file excel doesn't success
     */
    public void addFromExcel(String pathFile, String sheetName) throws Exception {
		
		List<OrderDTO> listOrderDTO = convertXLSX(pathFile, sheetName);
		
		for (OrderDTO orderDTO : listOrderDTO) {
			create(orderDTO);
		}
		
		System.out.println("Adding success");
	}
    
    /**
     * update data from file excel
     * @param pathFile path name of file excel
     * @param sheetName sheet contain data
     * @throws Exception throw if convert file excel doesn't success
     */
    public void updateFromExcel(String pathFile, String sheetName) throws Exception {
		
		List<OrderDTO> listOrderDTO = convertXLSX(pathFile, sheetName);
		
		for (OrderDTO orderDTO : listOrderDTO) {
			update(orderDTO);
		}
		
		System.out.println("Update success");
	}
	
    /**
     * Convert data from file excel
     * @param pathFile path name of file excel
     * @param sheetName sheet contain data
     * @return list of instance
     */
	public List<OrderDTO> convertXLSX(String pathFile, String sheetName) {
		
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
			mapRow.put("MilkTeaOrderId", row.getCell(0).toString());
			mapRow.put("CreateDate", row.getCell(1).toString());
			mapRow.put("Status", row.getCell(2).toString());
			mapRow.put("TotalPrice", row.getCell(3).toString());
			mapRow.put("StoreId", row.getCell(4).toString());
			mapRow.put("CustomerId", row.getCell(5).toString());
			
			listStr.add(mapRow);
		}
		
		List<OrderDTO> listObject = new ArrayList<>();
		
		for(Map<String, String> objectStr : listStr) {
			OrderDTO orderDTO = new OrderDTO();
			
			orderDTO.setId((int)Double.parseDouble(objectStr.get("MilkTeaOrderId")));
			orderDTO.setCreateDate(LocalDate.parse(objectStr.get("CreateDate"), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			orderDTO.setStatus(Boolean.parseBoolean(objectStr.get("Status")));
			orderDTO.setTotalPrice(Float.parseFloat(objectStr.get("TotalPrice")));
			
			StoreService storeService = new StoreService();
			StoreDTO storeDTO = storeService.readOne((int)Float.parseFloat(objectStr.get("StoreId")));
			orderDTO.setStoreDto(storeDTO);
			
			CustomerService customerService = new CustomerService();
			CustomerDTO customerDTO = customerService.readOne((int)Float.parseFloat(objectStr.get("CustomerId")));
			orderDTO.setCustomerDto(customerDTO);
			
			listObject.add(orderDTO);
		}
		
		return listObject;
	}
	
	/**
     * Insert a instance to database
     * @param milkTeaDTO the instance will be inserted
     * @return true if insertion is success otherwise false
     */
    public boolean create(OrderDTO orderDTO) {
        try {
            Order order = new Order(orderDTO);
            order.setId(0);
            int newId = (int) orderDAO.create(order);
            orderDTO.setId(newId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Get a instance from database
     * @param id of instance that will be get
     * @return a instance
     */
    public OrderDTO readOne(int id) {
        OrderDTO orderDTO = null;
        try {
            Order order = orderDAO.readOne(id);
            if (order != null) {
                orderDTO = new OrderDTO(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDTO;
    }

    /**
     * Get all row from table
     * @return list of instance
     */
    public List<OrderDTO> readAll() {
        List<Order> orderList = orderDAO.readAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderList) {
            orderDTOList.add(new OrderDTO(order));
        }
        
        for (OrderDTO orderDTO : orderDTOList) {
			System.out.println(orderDTO.toString());
		}
        
        return orderDTOList;
    }

    /**
     * Update info
     * @param milkTeaDTO that will be updated
     * @return true if update is success otherwise false
     */
    public boolean update(OrderDTO orderDTO) {
        try {
            Order order = new Order(orderDTO);
            orderDAO.update(order);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Delete data
     * @param id of instance that will be delete
     * @return true if delete is success otherwise false
     */
    public boolean delete(int id) {
        try {
            orderDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * Get data base on input condition 
     * @param fieldName of object 
     * @param value that will be compared
     */
    public <V> void equalOperator(String field, V value) {
    	
    	List<Order> orderList = orderDAO.equalOperator(field, value);
    	List<OrderDTO> orderDtoList = new ArrayList<>();
    	
    	if (orderList.isEmpty()) {
    		System.out.println(String.format("The Order with %s: %s doesn't exist!", field, String.valueOf(value)));
    		return;
    	}
    	
    	for (Order order : orderList) {
    		orderDtoList.add(new OrderDTO(order));
		}
    	
    	System.out.println(String.format("Info of Orders with %s: %s is: ", field, String.valueOf(value)));
		for (OrderDTO orderDto : orderDtoList) {
			System.out.println(orderDto.toString());
		}
    }
    
    /**
     * Get rows of database based on condition input is greater than or equal
     * @param value that will be compared
     */
    public void totalPriceGreaterThen(double value) {
    	
    	List<Order> orderList = orderDAO.greaterThanOperator("totalPrice", value);
    	List<OrderDTO> orderDtoList = new ArrayList<>();
    	
    	if (orderList.isEmpty()) {
    		System.out.println(String.format("The Order with %s: %s doesn't exist!", "TotalPrice", String.valueOf(value)));
    		return;
    	}
    	
    	for (Order order : orderList) {
    		orderDtoList.add(new OrderDTO(order));
		}
    	
    	System.out.println(String.format("Info of Orders with %s: %s is: ", "TotalPrice greater than", String.valueOf(value)));
		for (OrderDTO orderDto : orderDtoList) {
			System.out.println(orderDto.toString());
		}
    }
    
    /**
     * Get rows of database based on condition input is less than or equal
     * @param value hat will be compared
     */
    public void totalPriceLessThen(double value) {
    	
    	List<Order> orderList = orderDAO.lessThanOperator("totalPrice", value);
    	List<OrderDTO> orderDtoList = new ArrayList<>();
    	
    	if (orderList.isEmpty()) {
    		System.out.println(String.format("The Order with %s: %s doesn't exist!", "TotalPrice less than", String.valueOf(value)));
    		return;
    	}
    	
    	for (Order order : orderList) {
    		orderDtoList.add(new OrderDTO(order));
		}
    	
    	System.out.println(String.format("Info of Orders with %s: %s is: ", "TotalPrice less than", String.valueOf(value)));
		for (OrderDTO orderDto : orderDtoList) {
			System.out.println(orderDto.toString());
		}
    }

    /**
     * Find a instance base on input condition
     * @param fieldName of entity 
     * @param value that will be compared
     */
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
		
		equalOperator("createDate", LocalDate.parse(mapStr.get("CreateDate"),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		equalOperator("status", Boolean.valueOf(mapStr.get("Status")));
		totalPriceGreaterThen(Double.valueOf(mapStr.get("TotalPrice[greater than or equal]")));
		totalPriceLessThen(Double.valueOf(mapStr.get("TotalPrice[less than or equal]")));
		equalOperator("store", Integer.valueOf(mapStr.get("StoreId")));
		equalOperator("customer", Integer.valueOf(mapStr.get("CustomerId")));
	}

}
