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
import java.io.FileNotFoundException;
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

public class StoreExcel extends ReadFileExcel{
//	private final int COLUMN_INDEX_ID = 0;
	private final int COLUMN_INDEX_NAME = 0;
	private final int COLUMN_INDEX_ADDRESS = 1;
	private final int COLUMN_INDEX_IS_AVAILABLE = 2;
	
	public List<Store> readExcelStore (String excel) throws IOException{
		List<Store> listStore = new ArrayList<Store>();
		InputStream inputStream = new FileInputStream(new File(FILE));
		Workbook wb = getFile(inputStream, excel);
		//Indexsheet is 0
		Sheet sheet = wb.getSheetAt(0);
		Iterator<org.apache.poi.ss.usermodel.Row> iterator = sheet.iterator();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			if(nextRow.getRowNum() == 0) {
				continue;
			}
			Iterator<Cell> cellIterator = (nextRow).cellIterator();
			Store store = new Store();
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				Object cellValue = getCellValue(cell);
				if(cellValue == null || cellValue.toString().isEmpty()) {
					continue;
				}
				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				case COLUMN_INDEX_NAME:
					store.setStoreName((String) getCellValue(cell));
					break;
				case COLUMN_INDEX_ADDRESS:
					store.setAddress((String) getCellValue(cell));
					break;
				case COLUMN_INDEX_IS_AVAILABLE:
					store.setAvailable((Boolean) getCellValue(cell));
					break;
					default:
						break;
				}
			}
			listStore.add(store);
		}
		return listStore;
	}

}
