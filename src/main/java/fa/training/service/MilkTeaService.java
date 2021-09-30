package fa.training.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fa.training.dao.MilkTeaDAO;
import fa.training.dto.MilkTeaDTO;
import fa.training.entities.MilkTea;

public class MilkTeaService {

	private MilkTeaDAO milkTeaDAO = new MilkTeaDAO();
	
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
