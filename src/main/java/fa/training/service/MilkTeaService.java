package fa.training.service;

import fa.training.dao.MilkTeaDAO;
import fa.training.dto.MilkTeaDTO;
import fa.training.entities.MilkTea;

import java.util.ArrayList;
import java.util.List;

public class MilkTeaService {

    private MilkTeaDAO milkTeaDAO;

    public MilkTeaService() {
        this.milkTeaDAO = new MilkTeaDAO();
    }

    public boolean create(MilkTeaDTO milkTeaDTO) {
        try {
            MilkTea milkTea = new MilkTea(milkTeaDTO);
            milkTea.setId(0);
            int newId = (int) milkTeaDAO.create(milkTea);
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
