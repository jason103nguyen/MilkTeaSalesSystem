package fa.training.service;

import java.io.Serializable;
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

	private OrderDAO orderDAO = new OrderDAO();
	

	public void addFromExcel(String pathFile, String sheetName) throws Exception {
		
		List<OrderDTO> listOrderDTO = convertXLSX(pathFile, sheetName);
		
		for (OrderDTO orderDTO : listOrderDTO) {
			create(orderDTO);
		}
		
		System.out.println("Adding success");
	}
	
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
	
	public Serializable create(OrderDTO orderDTO) {
		
		Order order = new Order(orderDTO);
		return orderDAO.create(order);
	}
	
	public OrderDTO readOne(Serializable id) {
		
		Order order = orderDAO.readOne(id);
		OrderDTO orderDTO = new OrderDTO(order);
		return orderDTO;
	}
	
	public List<OrderDTO> readAll() {
		
		List<Order> listOrder = orderDAO.readAll();
		List<OrderDTO> listOrderDTO = new ArrayList<OrderDTO>();
		
		for (Order order : listOrder) {
			listOrderDTO.add(new OrderDTO(order));
		}
		return listOrderDTO;
	}
	
	public void update(OrderDTO orderDTO) {
		
		Order order = new Order(orderDTO);
		orderDAO.update(order);
	}
	
	public void delete(Serializable id) {
		
		orderDAO.delete(id);
	}
}
