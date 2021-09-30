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

import fa.training.entities.Store;
import fa.training.entities.Topping;

public class ToppingExcel extends ReadFileExcel{
	private final int COLUMN_INDEX_TOPPING = 0;
	private final int COLUMN_INDEX_PRICE = 1;
	
	public List<Topping> readExcelTopping (String excel) throws IOException{
		List<Topping> listTopping = new ArrayList<Topping>();
		InputStream inputStream = new FileInputStream(new File(FILE));
		Workbook wb = getFile(inputStream, excel);
		//Indexsheet is 3
		Sheet sheet = wb.getSheetAt(3);
		Iterator<org.apache.poi.ss.usermodel.Row> iterator = sheet.iterator();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			if(nextRow.getRowNum() == 0) {
				continue;
			}
			Iterator<Cell> cellIterator = (nextRow).cellIterator();
			Topping topping = new Topping();
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				Object cellValue = getCellValue(cell);
				if(cellValue == null || cellValue.toString().isEmpty()) {
					continue;
				}
				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				case COLUMN_INDEX_TOPPING:
					topping.setToppingName((String) getCellValue(cell));
					break;
				case COLUMN_INDEX_PRICE:
					topping.setPrice((Double) getCellValue(cell));
					break;
					default:
						break;
				}
			}
			listTopping.add(topping);
		}
		return listTopping;
	}

}
