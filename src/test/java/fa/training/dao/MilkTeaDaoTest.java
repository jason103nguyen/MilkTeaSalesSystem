package fa.training.dao;

import fa.training.entities.MilkTea;
import fa.training.utils.HibernateUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MilkTeaDaoTest {

    @Test
    public void createTest() {
        HibernateUtil.refreshNewSessionFactory();
        MilkTea milktea = new MilkTea("milktea", 10.4);
        MilkTeaDAO milkTeaDAO = new MilkTeaDAO();
        milkTeaDAO.create(milktea);
        assertNotNull(milktea.getId());
    }

    @Test
    public void readTest() {
        HibernateUtil.refreshNewSessionFactory();
        MilkTea milktea = new MilkTea("milktea", 10.4);
        MilkTeaDAO milkTeaDAO = new MilkTeaDAO();
        milkTeaDAO.create(milktea);
        List<MilkTea> milkTeaList = milkTeaDAO.readAll();
        assertEquals(milkTeaList.size(), 1);
        MilkTea milkTea1 = milkTeaDAO.readOne(1);
        assertNotNull(milkTea1);
    }

    @Test
    public void updateTest() {
        HibernateUtil.refreshNewSessionFactory();
        MilkTea milktea = new MilkTea("milktea", 10.4);
        MilkTeaDAO milkTeaDAO = new MilkTeaDAO();
        milkTeaDAO.create(milktea);
        assertEquals("milktea", milktea.getName());
        milktea.setName("milktea1");
        milkTeaDAO.update(milktea);
        MilkTea updatedMilktea = milkTeaDAO.readOne(1);
        assertEquals("milktea1", updatedMilktea.getName());
    }

    @Test
    public void deleteTest() {
        HibernateUtil.refreshNewSessionFactory();
        MilkTea milktea = new MilkTea("milktea", 10.4);
        MilkTeaDAO milkTeaDAO = new MilkTeaDAO();
        milkTeaDAO.create(milktea);
        milkTeaDAO.delete(1);
        assertNull(milkTeaDAO.readOne(1));
    }
}
