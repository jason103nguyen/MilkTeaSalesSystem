package fa.training.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import fa.training.dao.MilkTeaDAO;
import fa.training.dto.MilkTeaDTO;
import fa.training.entities.MilkTea;
import fa.training.utils.ServiceUtil;

public class MilkTeaService {

    private MilkTeaDAO milkTeaDAO;

    public MilkTeaService() {
        this.milkTeaDAO = new MilkTeaDAO();
    }

    /**
     * Add data from file excel
     * @param pathFile path name of file excel
     * @param sheetName sheet contain data
     * @throws Exception throw if convert file excel doesn't success
     */
    public void addFromExcel(String pathFile, String sheetName) throws Exception {
		
		List<MilkTeaDTO> listMilkTeaDTO = convertXLSX(pathFile, sheetName);
		
		for (MilkTeaDTO milkTeaDTO : listMilkTeaDTO) {
			create(milkTeaDTO);
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
		
		List<MilkTeaDTO> listMilkTeaDTO = convertXLSX(pathFile, sheetName);
		
		for (MilkTeaDTO milkTeaDTO : listMilkTeaDTO) {
			update(milkTeaDTO);
		}
		
		System.out.println("Update success");
	}
	
    /**
     * Convert data from file excel
     * @param pathFile path name of file excel
     * @param sheetName sheet contain data
     * @return list of instance
     */
	public List<MilkTeaDTO> convertXLSX(String pathFile, String sheetName) {
		
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
			mapRow.put("MilkTeaId", row.getCell(0).toString());
			mapRow.put("Name", row.getCell(1).toString());
			mapRow.put("Price", row.getCell(2).toString());
			
			listStr.add(mapRow);
		}
		
		List<MilkTeaDTO> listObject = new ArrayList<>();
		
		for(Map<String, String> objectStr : listStr) {
			MilkTeaDTO milkTeaDTO = new MilkTeaDTO();
			milkTeaDTO.setId((int)Double.parseDouble(objectStr.get("MilkTeaId")));
			milkTeaDTO.setName(objectStr.get("Name"));
			milkTeaDTO.setPrice(Double.parseDouble(objectStr.get("Price")));
			
			listObject.add(milkTeaDTO);
		}
		
		return listObject;
	}
    
	/**
     * Insert a instance to database
     * @param milkTeaDTO the instance will be inserted
     * @return true if insertion is success otherwise false
     */
    public boolean create(MilkTeaDTO milkTeaDTO) {
        try {
            MilkTea milkTea = new MilkTea(milkTeaDTO);
            milkTea.setId(0);
            int newId = (int) milkTeaDAO.create(milkTea);
            milkTeaDTO.setId(newId);
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
    public MilkTeaDTO readOne(int id) {
        MilkTeaDTO milkTeaDTO = null;
        try {
            MilkTea milkTea = milkTeaDAO.readOne(id);
            if (milkTea != null) {
                milkTeaDTO = new MilkTeaDTO(milkTea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return milkTeaDTO;
    }

    /**
     * Get all row from table
     * @return list of instance
     */
    public List<MilkTeaDTO> readAll() {
        List<MilkTea> MilkTeaList = milkTeaDAO.readAll();
        List<MilkTeaDTO> MilkTeaDTOList = new ArrayList<>();
        for (MilkTea MilkTea : MilkTeaList) {
            MilkTeaDTOList.add(new MilkTeaDTO(MilkTea));
        }
        
        for (MilkTeaDTO milkTeaDto : MilkTeaDTOList) {
        	System.out.println(milkTeaDto.toString());
        }
        
        return MilkTeaDTOList;
    }

    /**
     * Update info
     * @param milkTeaDTO that will be updated
     * @return true if update is success otherwise false
     */
    public boolean update(MilkTeaDTO milkTeaDTO) {
        try {
            MilkTea MilkTea = new MilkTea(milkTeaDTO);
            milkTeaDAO.update(MilkTea);
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
            milkTeaDAO.delete(id);
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
    	
    	List<MilkTea> milkTeaList = milkTeaDAO.likeOperator(field, value);
    	List<MilkTeaDTO> milkTeaDtoList = new ArrayList<>();
    	
    	if (milkTeaList.isEmpty()) {
    		System.out.println(String.format("The MilkTea with %s: %s doesn't exist!",field, value));
    		return;
    	}
    	
    	for (MilkTea milkTea : milkTeaList) {
    		milkTeaDtoList.add(new MilkTeaDTO(milkTea));
		}
    	
    	System.out.println(String.format("Info of MilkTeas with %s: %s is: ", field, value));
		for (MilkTeaDTO milkTeaDto : milkTeaDtoList) {
			System.out.println(milkTeaDto.toString());
		}
    }
    
    /**
     * Get rows of database based on condition input is greater than or equal
     * @param value that will be compared
     */
    public void priceGreaterThen(double value) {
    	
    	List<MilkTea> milkTeaList = milkTeaDAO.greaterThanOperator("price", value);
    	List<MilkTeaDTO> milkTeaDtoList = new ArrayList<>();
    	
    	if (milkTeaList.isEmpty()) {
    		System.out.println(String.format("The MilkTea with %s: %s doesn't exist!", "Price greater than", String.valueOf(value)));
    		return;
    	}
    	
    	for (MilkTea milkTea : milkTeaList) {
    		milkTeaDtoList.add(new MilkTeaDTO(milkTea));
		}
    	
    	System.out.println(String.format("Info of MilkTea with %s: %s is: ", "Price greater than", String.valueOf(value)));
		for (MilkTeaDTO milkTeaDto : milkTeaDtoList) {
			System.out.println(milkTeaDto.toString());
		}
    }
    
    /**
     * Get rows of database based on condition input is less than or equal
     * @param value hat will be compared
     */
    public void priceLessThen(double value) {
    	
    	List<MilkTea> milkTeaList = milkTeaDAO.lessThanOperator("price", value);
    	List<MilkTeaDTO> milkTeaDtoList = new ArrayList<>();
    	
    	if (milkTeaList.isEmpty()) {
    		System.out.println(String.format("The MilkTea with %s: %s doesn't exist!", "Price less than", String.valueOf(value)));
    		return;
    	}
    	
    	for (MilkTea milkTea : milkTeaList) {
    		milkTeaDtoList.add(new MilkTeaDTO(milkTea));
		}
    	
    	System.out.println(String.format("Info of MilkTea with %s: %s is: ", "Price less than", String.valueOf(value)));
		for (MilkTeaDTO milkTeaDto : milkTeaDtoList) {
			System.out.println(milkTeaDto.toString());
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
		
		likeOperator("name", mapStr.get("Name"));
		priceGreaterThen(Double.valueOf(mapStr.get("Price[greater than or equal]")));
		priceLessThen(Double.valueOf(mapStr.get("Price[less than or equal]")));
	}

}
