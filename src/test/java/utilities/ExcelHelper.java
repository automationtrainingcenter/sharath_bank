package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {

	Workbook book;
	Sheet sh;

	// open the excel to read the data
	public void openExcel(String folderName, String fileName, String sheetName) {
		try {
			FileInputStream fis = new FileInputStream(GenericHelper.getFilePath(folderName, fileName));
			if (fileName.endsWith("xls")) {
				book = new HSSFWorkbook(fis);
			} else if (fileName.endsWith("xlsx")) {
				book = new XSSFWorkbook(fis);
			}
			sh = book.getSheet(sheetName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// count the number of rows
	public int rowCount() {
		return sh.getLastRowNum();
	}

	// count the number of columns
	public int columnCount() {
		return sh.getRow(1).getLastCellNum();
	}

	// read data
	public String readData(int rnum, int cnum) {
		String data = "";
		Cell cell = sh.getRow(rnum).getCell(cnum);
		CellType cellType = cell.getCellTypeEnum();
		switch (cellType) {
		case STRING:
			data = cell.getStringCellValue();
			break;
		case NUMERIC:
			int i = (int) (cell.getNumericCellValue());
			data = Integer.toString(i);
		default:
			System.out.println("invalid cell type");
			break;
		}
		return data;
	}

	// close the excel file
	public void closeExcel() {
		try {
			book.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// read all excel data into a 2D array
	public String[][] readSheetData(String folderName, String fileName, String sheetName) {
		openExcel(folderName, fileName, sheetName);
		int nor = rowCount();
		int noc = columnCount();
		System.out.println(nor+"\t"+noc);
		String[][] data = new String[nor][noc];
		for (int i = 1; i <= nor; i++) {
			for (int j = 0; j < noc; j++) {
				System.out.println(readData(i, j));
				data[i-1][j] = readData(i, j);
			}
		}
		return data;
	}

}
