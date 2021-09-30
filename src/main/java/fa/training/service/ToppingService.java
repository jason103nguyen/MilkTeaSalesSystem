package fa.training.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import fa.training.dao.ToppingDAO;
import fa.training.dto.ToppingDTO;
import fa.training.entities.Topping;
import fa.training.utils.ServiceUtil;

public class ToppingService {

	private ToppingDAO toppingDAO = new ToppingDAO();
	

	public void addFromExcel(String pathFile, String sheetName) throws Exception {
		
		List<ToppingDTO> listToppingDTO = convertXLSX(pathFile, sheetName);
		
		for (ToppingDTO toppingDTO : listToppingDTO) {
			create(toppingDTO);
		}
		
		System.out.println("Adding success");
	}
	
	public List<ToppingDTO> convertXLSX(String pathFile, String sheetName) {
		
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
			mapRow.put("ToppingId", row.getCell(0).toString());
			mapRow.put("ToppingName", row.getCell(1).toString());
			mapRow.put("Price", row.getCell(2).toString());
			
			listStr.add(mapRow);
		}
		
		List<ToppingDTO> listObject = new ArrayList<>();
		
		for(Map<String, String> objectStr : listStr) {
			ToppingDTO toppingDTO = new ToppingDTO();
			toppingDTO.setId((int)Double.parseDouble(objectStr.get("ToppingId")));
			toppingDTO.setToppingName(objectStr.get("ToppingName"));
			toppingDTO.setPrice(Float.parseFloat(objectStr.get("Price")));
			
			
			listObject.add(toppingDTO);
		}
		
		return listObject;
	}
	
	public Serializable create(ToppingDTO toppingDTO) {
		
		Topping topping = new Topping(toppingDTO);
		return toppingDAO.create(topping);
	}
	
	public ToppingDTO readOne(Serializable id) {
		
		Topping topping = toppingDAO.readOne(id);
		ToppingDTO toppingDTO = new ToppingDTO(topping);
		return toppingDTO;
	}
	
	public List<ToppingDTO> readAll() {
		
		List<Topping> listTopping = toppingDAO.readAll();
		List<ToppingDTO> listToppingDTO = new ArrayList<ToppingDTO>();
		
		for (Topping topping : listTopping) {
			listToppingDTO.add(new ToppingDTO(topping));
		}
		return listToppingDTO;
	}
	
	public void update(ToppingDTO toppingDTO) {
		
		Topping topping = new Topping(toppingDTO);
		toppingDAO.update(topping);
	}
	
	public void delete(Serializable id) {
		
		toppingDAO.delete(id);
	}
}
