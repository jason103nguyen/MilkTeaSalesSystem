package fa.training.controller;

import java.io.IOException;
import fa.training.controller.StoreManagement;
import fa.training.dao.StoreDAO;
import fa.training.entities.Store;
public class Main {

	public static void main(String[] args) {
		Store store = new Store();
		StoreManagement sm = new StoreManagement();
		sm.createStoreFromExcel();
	}

}
