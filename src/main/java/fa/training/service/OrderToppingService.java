package fa.training.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import fa.training.dao.OrderToppingDAO;
import fa.training.dto.OrderDTO;
import fa.training.dto.OrderToppingDTO;
import fa.training.dto.ToppingDTO;
import fa.training.entities.OrderTopping;
import fa.training.utils.ServiceUtil;

public class OrderToppingService {

	private OrderToppingDAO orderToppingDAO = new OrderToppingDAO();
	
	/**
     * Add data from file excel
     * @param pathFile path name of file excel
     * @param sheetName sheet contain data
     * @throws Exception throw if convert file excel doesn't success
     */
	public void addFromExcel(String pathFile, String sheetName) throws Exception {
		
		List<OrderToppingDTO> listOrderToppingDTO = convertXLSX(pathFile, sheetName);
		
		for (OrderToppingDTO orderToppingDTO : listOrderToppingDTO) {
			create(orderToppingDTO);
		}
		
		System.out.println("Adding success");
	}
	
	/**
     * Convert data from file excel
     * @param pathFile path name of file excel
     * @param sheetName sheet contain data
     * @return list of instance
     */
	public List<OrderToppingDTO> convertXLSX(String pathFile, String sheetName) {
		
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
			mapRow.put("OrderToppingId", row.getCell(0).toString());
			mapRow.put("OrderId", row.getCell(1).toString());
			mapRow.put("ToppingId", row.getCell(2).toString());
			mapRow.put("Quantity", row.getCell(3).toString());
			
			listStr.add(mapRow);
		}
		
		List<OrderToppingDTO> listObject = new ArrayList<>();
		
		for(Map<String, String> objectStr : listStr) {
			OrderToppingDTO orderToppingDTO = new OrderToppingDTO();
			
			orderToppingDTO.setId(Integer.parseInt(objectStr.get("OrderToppingId")));
			
			OrderService orderService = new OrderService();
			OrderDTO orderDTO = orderService.readOne(Integer.parseInt(objectStr.get("OrderId")));
			orderToppingDTO.setOrderDto(orderDTO);;
			
			ToppingService toppingService = new ToppingService();
			ToppingDTO toppingDTO = toppingService.readOne(Integer.parseInt(objectStr.get("ToppingId")));
			orderToppingDTO.setToppingDto(toppingDTO);
			
			orderToppingDTO.setQuantity(Integer.parseInt(objectStr.get("Quantity")));
			listObject.add(orderToppingDTO);
		}
		
		return listObject;
	}
	
	/**
     * Insert a instance to database
     * @param milkTeaDTO the instance will be inserted
     * @return true if insertion is success otherwise false
     */
	public Serializable create(OrderToppingDTO orderToppingDTO) {
		
		OrderTopping OrderTopping = new OrderTopping(orderToppingDTO);
		return orderToppingDAO.create(OrderTopping);
	}
	
	/**
     * Get a instance from database
     * @param id of instance that will be get
     * @return a instance
     */
	public OrderToppingDTO readOne(Serializable id) {
		
		OrderTopping orderTopping = orderToppingDAO.readOne(id);
		OrderToppingDTO orderToppingDTO = new OrderToppingDTO(orderTopping);
		return orderToppingDTO;
	}
	
	/**
     * Get all row from table
     * @return list of instance
     */
	public List<OrderToppingDTO> readAll() {
		
		List<OrderTopping> listOrderTopping = orderToppingDAO.readAll();
		List<OrderToppingDTO> listOrderToppingDTO = new ArrayList<OrderToppingDTO>();
		
		for (OrderTopping orderTopping : listOrderTopping) {
			listOrderToppingDTO.add(new OrderToppingDTO(orderTopping));
		}
		return listOrderToppingDTO;
	}
	
	/**
     * Update info
     * @param milkTeaDTO that will be updated
     * @return true if update is success otherwise false
     */
	public void update(OrderToppingDTO orderToppingDTO) {
		
		OrderTopping orderTopping = new OrderTopping(orderToppingDTO);
		orderToppingDAO.update(orderTopping);
	}
	
	/**
     * Delete data
     * @param id of instance that will be delete
     * @return true if delete is success otherwise false
     */
	public void delete(Serializable id) {
		
		orderToppingDAO.delete(id);
	}
}
