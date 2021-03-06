package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	public static XSSFWorkbook workBook;
	public static XSSFSheet sheet;
	public static XSSFCell cell;

	public static void setExcelFIle() throws IOException {
		String file = System.getProperty("user.dir") + "\\TestData\\TestData.xlsx";
		// create and object of file class to read xlsx file
		File filePath = new File(file);
		// Create and object of fileinput stream to read xlsx file
		FileInputStream inputStream = new FileInputStream(filePath);
		// create and object of xssf workbook class
		workBook = new XSSFWorkbook(inputStream);
		// read sheet inside workbook by sheet name
		sheet = workBook.getSheet("Sheet1");
	}

	public static String getCellData(int rowNo, int coloumnNo) {
		DataFormatter formatter = new DataFormatter();
		cell = sheet.getRow(rowNo).getCell(coloumnNo);
		String cellData = formatter.formatCellValue(cell);
		return cellData;
	}

}
