package fa.training.service;

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

    private StoreDAO storeDAO;

    public StoreService() {
        this.storeDAO = new StoreDAO();
    }

    
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
	
    
    public boolean create(StoreDTO storeDTO) {
        try {
            Store store = new Store(storeDTO);
            store.setId(0);
            int newId = (int) storeDAO.create(store);
            storeDTO.setId(newId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public StoreDTO readOne(int id) {
        StoreDTO storeDTO = null;
        try {
            Store store = storeDAO.readOne(id);
            if (store != null) {
                storeDTO = new StoreDTO(store);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storeDTO;
    }

    public List<StoreDTO> readAll() {
        List<Store> storeList = storeDAO.readAll();
        List<StoreDTO> storeDTOList = new ArrayList<>();
        for (Store store : storeList) {
            storeDTOList.add(new StoreDTO(store));
        }
        return storeDTOList;
    }

    public boolean update(StoreDTO storeDTO) {
        try {
            Store store = new Store(storeDTO);
            storeDAO.update(store);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(int id) {
        try {
            storeDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
