package fa.training.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fa.training.dao.StoreDAO;
import fa.training.dto.StoreDTO;
import fa.training.entities.Store;

public class StoreService {

	private StoreDAO storeDAO = new StoreDAO();
	
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
