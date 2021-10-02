/*
 * (C) Copyright 2021 Fresher Academy. All Right Reserved .
 *
 * @author pc Pham Tran Gia Huy
 * @date Oct 1, 2021
 * @version 1.0
 */
package UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import fa.training.dao.ToppingDAO;
import fa.training.entities.Topping;
import fa.training.utils.HibernateUtil;

public class ToppingDAOTestUnit {

	Session session = HibernateUtil.getSessionFactory().openSession();

	@Test
	@Order(1)
	public void testcreateToppingDAO() {
		try {
			HibernateUtil.refreshNewSessionFactory();
			Topping topping = new Topping("Banh flan", 15000);
			ToppingDAO toppingDao = new ToppingDAO();
			toppingDao.create(topping);
			toppingDao.readOne(1);
			assertEquals("Banh flan", topping.getToppingName());
			assertEquals(15000, topping.getPrice());
			session.getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Test
	@Order(2)
	public void testUpdateToppingDAO() {
		try {
			HibernateUtil.refreshNewSessionFactory();
			Topping topping = new Topping("Banh flan", 15000);
			ToppingDAO topDao = new ToppingDAO();
			topDao.create(topping);
			assertEquals("Banh flan", topping.getToppingName());
			topping.setToppingName("Tran Chau");
			topDao.update(topping);
			Topping topUpdate = topDao.readOne(1);
			assertEquals("Tran Chau", topUpdate.getToppingName());
			session.getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Test
	@Order(3)
	public void testReadAllToppingDAO() {
		try {
			ToppingDAO toppingDao = new ToppingDAO();
			List<Topping> listTopping = toppingDao.readAll();
			Topping topping = listTopping.get(0);
			assertEquals(1, topping.getId());
			assertEquals("Tran Chau", topping.getToppingName());
			session.getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Test
	@Order(4)
	public void testDeleteToppingDAO() {
		try {
			HibernateUtil.refreshNewSessionFactory();
			Topping topping = new Topping("Mieng Dao", 10000);
			ToppingDAO topDao = new ToppingDAO();
			topDao.create(topping);
			topDao.delete(1);
			assertEquals(null, topDao.readOne(1));
			session.getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
}
