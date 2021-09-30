/*
 * (C) Copyright 2021 Fresher Academy. All Right Reserved .
 *
 * @author pc Pham Tran Gia Huy
 * @date Sep 30, 2021
 * @version 1.0
 */
package fa.training.controller;

import java.util.List;

import fa.training.dao.StoreDAO;
import fa.training.entities.Store;
import fa.training.readfile.ReadFileExcel;
import fa.training.readfile.StoreExcel;

public class StoreManagement {
	
	public StoreManagement() {
		
	}
	
	private static StoreDAO storeDao = new StoreDAO();
	
	public void createStoreFromExcel() {
		StoreExcel storeExcel = new StoreExcel();
		try {
			List<Store> listStore = storeExcel.readExcelStore(ReadFileExcel.FILE);
			Store store = new Store();
			for(Store s : listStore) {
				store.setStoreName(s.getStoreName());
				store.setAddress(s.getAddress());
				store.setAvailable(s.isAvailable());
				storeDao.create(store);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
