package fa.training.readfile;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFileExcel {
	public static final String FILE = "src\\main\\java\\fa\\training\\readfile\\Read.xlsx";
	
	public static Workbook getFile(InputStream inputStream, String excel) throws IOException {
		Workbook wb = null;
		if(excel.endsWith("xlsx")) {
			wb = new XSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The file is not file Excel");
		}
		return wb;
	}

	public static Object getCellValue(Cell cell) {
		CellType cellType = cell.getCellTypeEnum();
		Object cellValue = null;
		switch(cellType) {
		case BOOLEAN:
			cellValue = cell.getBooleanCellValue();
			break;
		case FORMULA:
			Workbook wb = cell.getSheet().getWorkbook();
			FormulaEvaluator eva = wb.getCreationHelper().createFormulaEvaluator();
			cellValue = eva.evaluate(cell).getNumberValue();
			break;
		case NUMERIC:
			cellValue = cell.getNumericCellValue();
			break;
		case STRING:
			cellValue = cell.getStringCellValue();
			break;
		case _NONE:
		case BLANK:
		case ERROR:
			break;
			default:
				break;
		}
		return cellValue;
	}
}
