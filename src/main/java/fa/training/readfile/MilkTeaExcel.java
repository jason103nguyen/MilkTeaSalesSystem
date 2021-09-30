/*
 * (C) Copyright 2021 Fresher Academy. All Right Reserved .
 *
 * @author pc Pham Tran Gia Huy
 * @date Sep 30, 2021
 * @version 1.0
 */
package fa.training.readfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import fa.training.entities.MilkTea;
import fa.training.entities.Topping;

public class MilkTeaExcel extends ReadFileExcel{
	private final int COLUMN_INDEX_NAME = 0;
	private final int COLUMN_INDEX_PRICE = 1;
	
	public List<MilkTea> readExcelMilkTea (String excel) throws IOException{
		List<MilkTea> listMilkTea = new ArrayList<MilkTea>();
		InputStream inputStream = new FileInputStream(new File(FILE));
		Workbook wb = getFile(inputStream, excel);
		//Indexsheet is 4
		Sheet sheet = wb.getSheetAt(4);
		Iterator<org.apache.poi.ss.usermodel.Row> iterator = sheet.iterator();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			if(nextRow.getRowNum() == 0) {
				continue;
			}
			Iterator<Cell> cellIterator = (nextRow).cellIterator();
			MilkTea milkTea = new MilkTea();
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				Object cellValue = getCellValue(cell);
				if(cellValue == null || cellValue.toString().isEmpty()) {
					continue;
				}
				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				case COLUMN_INDEX_NAME:
					milkTea.setName((String) getCellValue(cell));
					break;
				case COLUMN_INDEX_PRICE:
					milkTea.setPrice((Double) getCellValue(cell));
					break;
					default:
						break;
				}
			}
			listMilkTea.add(milkTea);
		}
		return listMilkTea;
	}

}
