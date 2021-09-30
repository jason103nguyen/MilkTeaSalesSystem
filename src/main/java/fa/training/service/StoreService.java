package fa.training.service;

import fa.training.dao.StoreDAO;
import fa.training.dto.StoreDTO;
import fa.training.entities.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreService {

    private StoreDAO storeDAO;

    public StoreService() {
        this.storeDAO = new StoreDAO();
    }

    public boolean create(StoreDTO storeDTO) {
        try {
            Store store = new Store(storeDTO);
            store.setId(0);
            int newId = (int) storeDAO.create(store);
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
