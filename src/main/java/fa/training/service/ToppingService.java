package fa.training.service;

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

    private ToppingDAO toppingDAO;

    public ToppingService() {
        this.toppingDAO = new ToppingDAO();
    }

	public void addFromExcel(String pathFile, String sheetName) throws Exception {
		
		List<ToppingDTO> listToppingDTO = convertXLSX(pathFile, sheetName);
		
		for (ToppingDTO toppingDTO : listToppingDTO) {
			create(toppingDTO);
		}
		
		System.out.println("Adding success");
	}
	
	public void updateFromExcel(String pathFile, String sheetName) throws Exception {
		
		List<ToppingDTO> listToppingDTO = convertXLSX(pathFile, sheetName);
		
		for (ToppingDTO toppingDTO : listToppingDTO) {
			update(toppingDTO);
		}
		
		System.out.println("Update success");
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
	
    
    public boolean create(ToppingDTO toppingDTO) {
        try {
            Topping topping = new Topping(toppingDTO);
            topping.setId(0);
            int newId = (int) toppingDAO.create(topping);
            toppingDTO.setId(newId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ToppingDTO readOne(int id) {
        ToppingDTO toppingDTO = null;
        try {
            Topping topping = toppingDAO.readOne(id);
            if (topping != null) {
                toppingDTO = new ToppingDTO(topping);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toppingDTO;
    }

    public List<ToppingDTO> readAll() {
        List<Topping> toppingList = toppingDAO.readAll();
        List<ToppingDTO> toppingDTOList = new ArrayList<>();
        for (Topping topping : toppingList) {
            toppingDTOList.add(new ToppingDTO(topping));
        }
        
        for (ToppingDTO toppingDTO : toppingDTOList) {
			System.out.println(toppingDTO.toString());
		}
        
        return toppingDTOList;
    }

    public boolean update(ToppingDTO toppingDTO) {
        try {
            Topping topping = new Topping(toppingDTO);
            toppingDAO.update(topping);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(int id) {
        try {
            toppingDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

	public void likeOperator(String field, String value) {
    	
    	List<Topping> toppingList = toppingDAO.likeOperator(field, value);
    	List<ToppingDTO> toppingDtoList = new ArrayList<>();
    	
    	if (toppingList.isEmpty()) {
    		System.out.println(String.format("The Topping with %s: %s doesn't exist!",field, value));
    		return;
    	}
    	
    	for (Topping topping : toppingList) {
    		toppingDtoList.add(new ToppingDTO(topping));
		}
    	
    	System.out.println(String.format("Info of Topping with %s: %s is: ", field, value));
		for (ToppingDTO toppingDto : toppingDtoList) {
			System.out.println(toppingDto.toString());
		}
    }

    public void priceGreaterThen(double value) {
    	
    	List<Topping> toppingList = toppingDAO.greaterThanOperator("price", value);
    	List<ToppingDTO> toppingDtoList = new ArrayList<>();
    	
    	if (toppingList.isEmpty()) {
    		System.out.println(String.format("The Topping with %s: %s doesn't exist!", "Price greater than or equal", String.valueOf(value)));
    		return;
    	}
    	
    	for (Topping topping : toppingList) {
    		toppingDtoList.add(new ToppingDTO(topping));
		}
    	
    	System.out.println(String.format("Info of Topping with %s: %s is: ", "Price greater than or equal", String.valueOf(value)));
		for (ToppingDTO toppingDto : toppingDtoList) {
			System.out.println(toppingDto.toString());
		}
    }
    
    public void priceLessThen(double value) {
    	
    	List<Topping> toppingList = toppingDAO.lessThanOperator("price", value);
    	List<ToppingDTO> toppingDtoList = new ArrayList<>();
    	
    	if (toppingList.isEmpty()) {
    		System.out.println(String.format("The Topping with %s: %s doesn't exist!", "Price less than or equal", String.valueOf(value)));
    		return;
    	}
    	
    	for (Topping topping : toppingList) {
    		toppingDtoList.add(new ToppingDTO(topping));
		}
    	
    	System.out.println(String.format("Info of Topping with %s: %s is: ", "Price less than or equal", String.valueOf(value)));
		for (ToppingDTO toppingDto : toppingDtoList) {
			System.out.println(toppingDto.toString());
		}
    }

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
		
		likeOperator("toppingName", mapStr.get("ToppingName"));
		priceGreaterThen(Double.valueOf(mapStr.get("Price[greater than or equal]")));
		priceLessThen(Double.valueOf(mapStr.get("Price[less than or equal]")));
	}
}
