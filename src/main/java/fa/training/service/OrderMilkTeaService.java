package fa.training.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import fa.training.dao.OrderMilkTeaDAO;
import fa.training.dto.MilkTeaDTO;
import fa.training.dto.OrderDTO;
import fa.training.dto.OrderMilkTeaDTO;
import fa.training.entities.OrderMilkTea;
import fa.training.utils.ServiceUtil;

public class OrderMilkTeaService {

	private OrderMilkTeaDAO orderMilkTeaDAO = new OrderMilkTeaDAO();
	
public void addFromExcel(String pathFile, String sheetName) throws Exception {
		
		List<OrderMilkTeaDTO> listOrderMilkTeaDTO = convertXLSX(pathFile, sheetName);
		
		for (OrderMilkTeaDTO orderMilkTeaDTO : listOrderMilkTeaDTO) {
			create(orderMilkTeaDTO);
		}
		
		System.out.println("Adding success");
	}
	
	public List<OrderMilkTeaDTO> convertXLSX(String pathFile, String sheetName) {
		
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
			mapRow.put("OrderMilkTeaId", row.getCell(0).toString());
			mapRow.put("OrderId", row.getCell(1).toString());
			mapRow.put("MilkTeaId", row.getCell(2).toString());
			mapRow.put("Quantity", row.getCell(3).toString());
			
			listStr.add(mapRow);
		}
		
		List<OrderMilkTeaDTO> listObject = new ArrayList<>();
		
		for(Map<String, String> objectStr : listStr) {
			OrderMilkTeaDTO orderMilkTeaDTO = new OrderMilkTeaDTO();
			
			orderMilkTeaDTO.setId((int)Double.parseDouble(objectStr.get("OrderMilkTeaId")));
			
			OrderService orderService = new OrderService();
			OrderDTO orderDTO = orderService.readOne(Integer.parseInt(objectStr.get("OrderId")));
			orderMilkTeaDTO.setOrderDto(orderDTO);
			
			MilkTeaService milkTeaService = new MilkTeaService();
			MilkTeaDTO milkTeaDTO = milkTeaService.readOne(Integer.parseInt(objectStr.get("MilkTeaId")));
			orderMilkTeaDTO.setMilkTeaDto(milkTeaDTO);
			
			orderMilkTeaDTO.setQuantity(Integer.parseInt(objectStr.get("Quantity")));
			listObject.add(orderMilkTeaDTO);
		}
		
		return listObject;
	}
	

	
	public Serializable create(OrderMilkTeaDTO orderMilkTeaDTO) {
		
		OrderMilkTea orderMilkTea = new OrderMilkTea(orderMilkTeaDTO);
		return orderMilkTeaDAO.create(orderMilkTea);
	}
	
	public OrderMilkTeaDTO readOne(Serializable id) {
		
		OrderMilkTea orderMilkTea = orderMilkTeaDAO.readOne(id);
		OrderMilkTeaDTO orderMilkTeaDTO = new OrderMilkTeaDTO(orderMilkTea);
		return orderMilkTeaDTO;
	}
	
	public List<OrderMilkTeaDTO> readAll() {
		
		List<OrderMilkTea> listOrderMilkTea = orderMilkTeaDAO.readAll();
		List<OrderMilkTeaDTO> listOrderMilkTeaDTO = new ArrayList<OrderMilkTeaDTO>();
		
		for (OrderMilkTea orderMilkTea : listOrderMilkTea) {
			listOrderMilkTeaDTO.add(new OrderMilkTeaDTO(orderMilkTea));
		}
		return listOrderMilkTeaDTO;
	}
	
	public void update(OrderMilkTeaDTO orderMilkTeaDTO) {
		
		OrderMilkTea orderMilkTea = new OrderMilkTea(orderMilkTeaDTO);
		orderMilkTeaDAO.update(orderMilkTea);
	}
	
	public void delete(Serializable id) {
		
		orderMilkTeaDAO.delete(id);
	}
}
