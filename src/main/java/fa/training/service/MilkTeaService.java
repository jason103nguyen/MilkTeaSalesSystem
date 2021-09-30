package fa.training.service;

import java.io.Serializable;
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

	private MilkTeaDAO milkTeaDAO = new MilkTeaDAO();

	public void addFromExcel(String pathFile, String sheetName) throws Exception {
		
		List<MilkTeaDTO> listMilkTeaDTO = convertXLSX(pathFile, sheetName);
		
		for (MilkTeaDTO milkTeaDTO : listMilkTeaDTO) {
			create(milkTeaDTO);
		}
		
		System.out.println("Adding success");
	}
	
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
	
	public Serializable create(MilkTeaDTO milkTeaDTO) {
		
		MilkTea milkTea = new MilkTea(milkTeaDTO);
		return milkTeaDAO.create(milkTea);
	}
	
	public MilkTeaDTO readOne(Serializable id) {
		
		MilkTea milkTea = milkTeaDAO.readOne(id);
		MilkTeaDTO milkTeaDTO = new MilkTeaDTO(milkTea);
		return milkTeaDTO;
	}
	
	public List<MilkTeaDTO> readAll() {
		
		List<MilkTea> listMilkTea = milkTeaDAO.readAll();
		List<MilkTeaDTO> listMilkTeaDTO = new ArrayList<MilkTeaDTO>();
		
		for (MilkTea milkTea : listMilkTea) {
			listMilkTeaDTO.add(new MilkTeaDTO(milkTea));
		}
		return listMilkTeaDTO;
	}
	
	public void update(MilkTeaDTO milkTeaDTO) {
		
		MilkTea milkTea = new MilkTea(milkTeaDTO);
		milkTeaDAO.update(milkTea);
	}
	
	public void delete(Serializable id) {
		
		milkTeaDAO.delete(id);
	}
}
