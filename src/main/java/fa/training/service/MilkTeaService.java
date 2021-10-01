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

    public List<MilkTeaDTO> readAll() {
        List<MilkTea> MilkTeaList = milkTeaDAO.readAll();
        List<MilkTeaDTO> MilkTeaDTOList = new ArrayList<>();
        for (MilkTea MilkTea : MilkTeaList) {
            MilkTeaDTOList.add(new MilkTeaDTO(MilkTea));
        }
        return MilkTeaDTOList;
    }

    public boolean update(MilkTeaDTO MilkTeaDTO) {
        try {
            MilkTea MilkTea = new MilkTea(MilkTeaDTO);
            milkTeaDAO.update(MilkTea);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(int id) {
        try {
            milkTeaDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
