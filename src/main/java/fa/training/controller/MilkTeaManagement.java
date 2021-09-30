/*
 * (C) Copyright 2021 Fresher Academy. All Right Reserved .
 *
 * @author pc Pham Tran Gia Huy
 * @date Sep 30, 2021
 * @version 1.0
 */
package fa.training.controller;

import java.util.List;

import fa.training.dao.MilkTeaDAO;
import fa.training.entities.MilkTea;
import fa.training.readfile.MilkTeaExcel;
import fa.training.readfile.ReadFileExcel;


public class MilkTeaManagement {
	
	public MilkTeaManagement() {
		
	}
	
	private static MilkTeaDAO milkDao = new MilkTeaDAO();
	
	public void createMilkTeaFromExcel() {
		MilkTeaExcel milkTeaExcel = new MilkTeaExcel();
		try {
			List<MilkTea> listMilkTea = milkTeaExcel.readExcelMilkTea(ReadFileExcel.FILE);
			MilkTea milkTea = new MilkTea();
			for(MilkTea m : listMilkTea) {
				milkTea.setName(m.getName());
				milkTea.setPrice(m.getPrice());
				milkDao.create(milkTea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
