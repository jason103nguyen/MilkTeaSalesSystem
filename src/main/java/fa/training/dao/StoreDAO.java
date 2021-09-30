package fa.training.dao;

import java.util.List;

import fa.training.entities.Store;
import fa.training.readfile.ReadFileExcel;
import fa.training.readfile.StoreExcel;

public class StoreDAO extends GenericDao<Store> {

	public StoreDAO() {
		super(Store.class);
	}

}
