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

    /**
     * Add data from file excel
     * @param pathFile path name of file excel
     * @param sheetName sheet contain data
     * @throws Exception throw if convert file excel doesn't success
     */
	public void addFromExcel(String pathFile, String sheetName) throws Exception {
		
		List<StoreDTO> listStoreDTO = convertXLSX(pathFile, sheetName);
		
		for (StoreDTO storeDTO : listStoreDTO) {
			create(storeDTO);
		}
		
		System.out.println("Adding success");
	}
	
	/**
     * Update info based on data from file excel
     * @param pathFile path name of file excel
     * @param sheetName sheet contain data
     * @throws Exception throw if convert file excel doesn't success
     */
	public void updateFromExcel(String pathFile, String sheetName) throws Exception {
		
		List<StoreDTO> listStoreDTO = convertXLSX(pathFile, sheetName);
		
		for (StoreDTO storeDTO : listStoreDTO) {
			update(storeDTO);
		}
		
		System.out.println("Update success");
	}
	
	/**
     * Convert data from file excel
     * @param pathFile path name of file excel
     * @param sheetName sheet contain data
     * @return list of instance
     */
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
	
	/**
     * Insert a instance to database
     * @param milkTeaDTO the instance will be inserted
     * @return true if insertion is success otherwise false
     */
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

    /**
     * Get a instance from database
     * @param id of instance that will be get
     * @return a instance
     */
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

    /**
     * Get all row from table
     * @return list of instance
     */
    public List<StoreDTO> readAll() {
        List<Store> storeList = storeDAO.readAll();
        List<StoreDTO> storeDTOList = new ArrayList<>();
        for (Store store : storeList) {
            storeDTOList.add(new StoreDTO(store));
        }
        
        for (StoreDTO storeDTO : storeDTOList) {
			System.out.println(storeDTO.toString());
		}
        
        return storeDTOList;
    }

    /**
     * Update info
     * @param milkTeaDTO that will be updated
     * @return true if update is success otherwise false
     */
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

    /**
     * Delete data
     * @param id of instance that will be delete
     * @return true if delete is success otherwise false
     */
    public boolean delete(int id) {
        try {
            storeDAO.delete(id);
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
    public void likeOperator(String field, String value) {
    	
    	List<Store> storeList = storeDAO.likeOperator(field, value);
    	List<StoreDTO> storeDtoList = new ArrayList<>();
    	
    	if (storeList.isEmpty()) {
    		System.out.println(String.format("The Store with %s: %s doesn't exist!",field, value));
    		return;
    	}
    	
    	for (Store store : storeList) {
    		storeDtoList.add(new StoreDTO(store));
		}
    	
    	System.out.println(String.format("Info of Stores with %s: %s is: ", field, value));
		for (StoreDTO storeDto : storeDtoList) {
			System.out.println(storeDto.toString());
		}
    }
    
    /**
     * Get rows of database based on condition input
     * @param fieldName of object 
     * @param value hat will be compared
     */
    public void equalOperator(String field, Boolean value) {
    	List<Store> storeList = storeDAO.equalOperator(field, value);
    	List<StoreDTO> storeDtoList = new ArrayList<>();
    	
    	if (storeList.isEmpty()) {
    		System.out.println(String.format("The Store with IsAvailable: %s doesn't exist!", String.valueOf(value)));
    		return;
    	}
    	
    	for (Store store : storeList) {
    		storeDtoList.add(new StoreDTO(store));
		}
    	
    	System.out.println(String.format("Info of Stores with IsAvaibale: %s is: ", String.valueOf(value)));
		for (StoreDTO storeDto : storeDtoList) {
			System.out.println(storeDto.toString());
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
		
		likeOperator("storeName", mapStr.get("StoreName"));
		likeOperator("address", mapStr.get("Address"));
		equalOperator("isAvailable",Boolean.valueOf(mapStr.get("IsAvailable")));
	}
}
