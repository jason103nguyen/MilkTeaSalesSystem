/*
 * (C) Copyright 2021 Fresher Academy. All Right Reserved .
 *
 * @author pc Pham Tran Gia Huy
 * @date Oct 1, 2021
 * @version 1.0
 */
package fa.training.dao;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;
import fa.training.dao.StoreDAO;
import fa.training.entities.Store;
import fa.training.utils.HibernateUtil;

public class StoreDAOTestUnit {
	
	Session session = HibernateUtil.getSessionFactory().openSession();

	@Test
//	@Order(1)
	public void testAddStoreDAO() {
		try {

			HibernateUtil.refreshNewSessionFactory();
			Store store = new Store("Phuc Long", "103 Le Loi", false);
			StoreDAO storeDao = new StoreDAO();
			storeDao.create(store);
			assertEquals("Phuc Long", store.getStoreName());
			assertEquals("103 Le Loi", store.getAddress());
			assertEquals(false, store.isAvailable());
			session.getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Test
//	@Order(2)
	public void testReadAll() {
//		HibernateUtil.refreshNewSessionFactory();
		try {
			StoreDAO storeDao = new StoreDAO();
			List<Store> listStore = storeDao.readAll();
			Store store = listStore.get(0);
			assertEquals(1, store.getId());
			assertEquals("Phuc Long", store.getStoreName());
			session.getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}
	
	@Test
//	@Order(3)
	public void testUpdateStoreDAO() {
		try {
			HibernateUtil.refreshNewSessionFactory();
			Store store = new Store("Phuc Long", "103 Le Loi", false);
			StoreDAO storeDao = new StoreDAO();
			storeDao.create(store);
			assertEquals("Phuc Long", store.getStoreName());;
			store.setStoreName("StarBuck");
			storeDao.update(store);
			Store updateStore = storeDao.readOne(1);
			assertEquals("StarBuck", updateStore.getStoreName());
			session.getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	
	
	@Test
//	@Order(4)
	public void testDeleteTest() {
		try {
			HibernateUtil.refreshNewSessionFactory();
			Store store = new Store("Phuc Long", "103 Le Loi", false);
			StoreDAO storeDao = new StoreDAO();
			storeDao.create(store);
			storeDao.delete(1);		
			assertEquals(null, storeDao.readOne(1));
			session.getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	
}
