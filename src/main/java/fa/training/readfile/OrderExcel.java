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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import fa.training.entities.Order;
import fa.training.entities.Store;

public class OrderExcel extends ReadFileExcel{
	private final int COLUMN_INDEX_CREATE_DATE = 0;
	private final int COLUMN_INDEX_STATUS = 1;
	private final int COLUMN_INDEX_TOTAL_PRICE = 2;
	
	public List<Order> readExcelOrder (String excel) throws IOException{
		List<Order> listOrder = new ArrayList<Order>();
		InputStream inputStream = new FileInputStream(new File(FILE));
		Workbook wb = getFile(inputStream, excel);
		//Indexsheet is 2
		Sheet sheet = wb.getSheetAt(2);
		Iterator<org.apache.poi.ss.usermodel.Row> iterator = sheet.iterator();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			if(nextRow.getRowNum() == 0) {
				continue;
			}
			Iterator<Cell> cellIterator = (nextRow).cellIterator();
			Order order = new Order();
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				Object cellValue = getCellValue(cell);
				if(cellValue == null || cellValue.toString().isEmpty()) {
					continue;
				}
				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				case COLUMN_INDEX_CREATE_DATE:
					order.setCreateDate((LocalDate) getCellValue(cell));
					break;
				case COLUMN_INDEX_STATUS:
					order.setStatus((Boolean) getCellValue(cell));
					break;
				case COLUMN_INDEX_TOTAL_PRICE:
					order.setTotalPrice((Double) getCellValue(cell));
					break;
					default:
						break;
				}
			}
			listOrder.add(order);
		}
		return listOrder;
	}
}
