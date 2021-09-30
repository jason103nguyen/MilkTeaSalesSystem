package fa.training.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import fa.training.dao.StoreDAO;
import fa.training.dto.StoreDTO;
import fa.training.entities.Store;
import fa.training.utils.ServiceUtil;

public class StoreService {

	private StoreDAO storeDAO = new StoreDAO();
	

	public void addFromExcel(String pathFile, String sheetName) throws Exception {
		
		List<StoreDTO> listStoreDTO = convertXLSX(pathFile, sheetName);
		
		for (StoreDTO storeDTO : listStoreDTO) {
			create(storeDTO);
		}
		
		System.out.println("Adding success");
	}
	
	public List<StoreDTO> convertXLSX(String pathFile, String sheetName) {
		
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
			mapRow.put("StoreId", row.getCell(0).toString());
			mapRow.put("StoreName", row.getCell(1).toString());
			mapRow.put("Address", row.getCell(2).toString());
			mapRow.put("IsAvailable", row.getCell(3).toString());
			
			listStr.add(mapRow);
		}
		
		List<StoreDTO> listObject = new ArrayList<>();
		
		for(Map<String, String> objectStr : listStr) {
			StoreDTO storeDTO = new StoreDTO();
			storeDTO.setId((int)Double.parseDouble(objectStr.get("StoreId")));
			storeDTO.setStoreName(objectStr.get("StoreName"));
			storeDTO.setAddress(objectStr.get("Address"));
			storeDTO.setAvailable(Boolean.parseBoolean(objectStr.get("IsAvailable")));
			
			listObject.add(storeDTO);
		}
		
		return listObject;
	}
	
	public Serializable create(StoreDTO storeDTO) {
		
		Store store = new Store(storeDTO);
		return storeDAO.create(store);
	}
	
	public StoreDTO readOne(Serializable id) {
		
		Store store = storeDAO.readOne(id);
		StoreDTO storeDTO = new StoreDTO(store);
		return storeDTO;
	}
	
	public List<StoreDTO> readAll() {
		
		List<Store> listStore = storeDAO.readAll();
		List<StoreDTO> listStoreDTO = new ArrayList<StoreDTO>();
		
		for (Store store : listStore) {
			listStoreDTO.add(new StoreDTO(store));
		}
		return listStoreDTO;
	}
	
	public void update(StoreDTO storeDTO) {
		
		Store store = new Store(storeDTO);
		storeDAO.update(store);
	}
	
	public void delete(Serializable id) {
		
		storeDAO.delete(id);
	}
}
