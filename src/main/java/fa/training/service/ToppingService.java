package fa.training.service;

import fa.training.dao.ToppingDAO;
import fa.training.dto.ToppingDTO;
import fa.training.entities.Topping;

import java.util.ArrayList;
import java.util.List;

public class ToppingService {

    private ToppingDAO toppingDAO;

    public ToppingService() {
        this.toppingDAO = new ToppingDAO();
    }

    public boolean create(ToppingDTO toppingDTO) {
        try {
            Topping topping = new Topping(toppingDTO);
            topping.setId(0);
            int newId = (int) toppingDAO.create(topping);
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

}
