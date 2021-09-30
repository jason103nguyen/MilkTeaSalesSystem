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

import fa.training.entities.Customer;

public class CustomerExcel extends ReadFileExcel {
	private final int COLUMN_INDEX_FIRST_NAME = 0;
	private final int COLUMN_INDEX_LAST_NAME = 1;
	private final int COLUMN_INDEX_EMAIL = 2;
	private final int COLUMN_INDEX_PHONE = 3;
	
	public List<Customer> readExcelCustomer (String excel) throws IOException{
		List<Customer> listCustomer = new ArrayList<Customer>();
		InputStream inputStream = new FileInputStream(new File(FILE));
		Workbook wb = getFile(inputStream, excel);
		//Indexsheet is 1
		Sheet sheet = wb.getSheetAt(1);
		Iterator<org.apache.poi.ss.usermodel.Row> iterator = sheet.iterator();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			if(nextRow.getRowNum() == 0) {
				continue;
			}
			Iterator<Cell> cellIterator = (nextRow).cellIterator();
			Customer cus = new Customer();
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				Object cellValue = getCellValue(cell);
				if(cellValue == null || cellValue.toString().isEmpty()) {
					continue;
				}
				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				case COLUMN_INDEX_FIRST_NAME:
					cus.setFirstName((String) getCellValue(cell));
					break;
				case COLUMN_INDEX_LAST_NAME:
					cus.setLastName((String) getCellValue(cell));
					break;
				case COLUMN_INDEX_EMAIL:
					cus.setEmail((String) getCellValue(cell));
					break;
				case COLUMN_INDEX_PHONE:
					cus.setPhone((String) getCellValue(cell));
					break;
					default:
						break;
				}
			}
			listCustomer.add(cus);
		}
		return listCustomer;
	}

}
