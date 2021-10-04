/*
 * (C) Copyright 2021 Fresher Academy. All Right Reserved .
 *
 * @author pc Pham Tran Gia Huy
 * @date Oct 1, 2021
 * @version 1.0
 */
package fa.training.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Create method to read data from file excel 
 *
 */
public class ServiceUtil {

	public static Workbook convertXLSXtoWorkbook(String pathFile) {
		
		FileInputStream file = null;
		try {
			file = new FileInputStream(new File(pathFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return workbook;
	}
	
	public static String convertCellToString(String pathFile, String sheetName, String cellAddress) {
		
		Workbook workbook = ServiceUtil.convertXLSXtoWorkbook(pathFile);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(new CellAddress(cellAddress).getRow());
		Cell cell = row.getCell(new CellAddress(cellAddress).getColumn());
		
		return cell.toString();
	}
}
