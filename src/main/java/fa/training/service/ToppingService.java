package fa.training.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fa.training.dao.ToppingDAO;
import fa.training.dto.ToppingDTO;
import fa.training.entities.Topping;

public class ToppingService {

	private ToppingDAO toppingDAO = new ToppingDAO();
	
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
