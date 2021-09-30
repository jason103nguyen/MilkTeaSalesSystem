/*
 * (C) Copyright 2021 Fresher Academy. All Right Reserved .
 *
 * @author pc Pham Tran Gia Huy
 * @date Sep 30, 2021
 * @version 1.0
 */
package fa.training.controller;

import java.util.List;

import fa.training.dao.ToppingDAO;
import fa.training.entities.Topping;
import fa.training.readfile.ReadFileExcel;
import fa.training.readfile.ToppingExcel;



public class ToppingManagement {
	
	public ToppingManagement() {
		
	}
	
	private static ToppingDAO toppingDao = new ToppingDAO();
	
	public void createToppingFromExcel() {
		ToppingExcel toppingExcel = new ToppingExcel();
		try {
			List<Topping> listTopping = toppingExcel.readExcelTopping(ReadFileExcel.FILE);
			Topping topping = new Topping();
			for(Topping t : listTopping) {
				topping.setToppingName(t.getToppingName());
				topping.setPrice(t.getPrice());
				toppingDao.create(topping);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
