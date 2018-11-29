package excel;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * EXCEL2003 EXCEL2007 util via apache POI
 */
public class ExcelUtilSim {

	public static final String EXCEL2003_FILETYPE = "application/vnd.ms-excel";

	public static final String EXCEL2007_FILETYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	/**
	 * 是否excel
	 */
	public static boolean isExcel(File file) {
		if (isExcel2003(file) || isExcel2007(file))
			return true;
		return false;
	}

	/**
	 * 是否Excel2003
	 */
	public static boolean isExcel2003(File file) {
		String fileName = file.getName();
		if (fileName.matches("^.+\\.(?i)(xls)$"))
			return true;
		return false;
	}

	/**
	 * 是否Excel2007
	 */
	public static boolean isExcel2007(File file) {
		String fileName = file.getName();
		if (fileName.matches("^.+\\.(?i)(xlsx)$"))
			return true;
		return false;
	}

	/**
	 * 获得总行数Excel03
	 */
	public static int getRowCountExcel03(HSSFSheet hssfSheet) {
		int rows = 0;
		rows = hssfSheet.getLastRowNum();

		if (rows < 0)
			return rows;
		else
			return rows + 1;
	}

	/**
	 * 获得总行数Excel07
	 */
	public static int getRowCountExcel07(XSSFSheet xssfSheet) {
		int rows = 0;
		rows = xssfSheet.getLastRowNum();

		if (rows < 0)
			return rows;
		else
			return rows + 1;
	}

	/**
	 * 获得第几行总列数Excel03
	 */
	public static int getColCountAtRowNumExcel03(HSSFSheet hssfSheet, int rowNum) {
		int colNum = 0;
		int pysicalColNum = 0;
		HSSFRow row = hssfSheet.getRow(rowNum);
		if (row != null) {
			colNum = row.getLastCellNum();
			pysicalColNum = row.getPhysicalNumberOfCells();
		}
		if (colNum == 0) {
			if (pysicalColNum > 0)
				colNum = 1;
		}
		return colNum;
	}

	/**
	 * 获得第几行总列数Excel07
	 */
	public static int getColCountAtRowNumExcel07(XSSFSheet xssfSheet, int rowNum) {
		int colNum = 0;
		int pysicalColNum = 0;
		XSSFRow row = xssfSheet.getRow(rowNum);
		if (row != null) {
			colNum = row.getLastCellNum();
			pysicalColNum = row.getPhysicalNumberOfCells();
		}
		if (colNum == 0) {
			if (pysicalColNum > 0)
				colNum = 1;
		}
		return colNum;
	}

	/**
	 * 获得页sheet对象Excel03
	 */
	public static HSSFSheet getExcel03Sheet(File file) throws Exception {
		POIFSFileSystem poiFileSystem;
		FileInputStream fint = new FileInputStream(file);
		poiFileSystem = new POIFSFileSystem(fint);
		HSSFWorkbook wb = new HSSFWorkbook(poiFileSystem);
		HSSFSheet sheet = wb.getSheetAt(0);
		return sheet;
	}

	/**
	 * 获得页sheet对象Excel07
	 */
	public static XSSFSheet getExcel07Sheet(File file) throws Exception {
		@SuppressWarnings("deprecation")
		XSSFWorkbook xwb = new XSSFWorkbook(file.getPath());
		XSSFSheet xsheet = xwb.getSheetAt(0);
		return xsheet;
	}

	/**
	 * 获得第几行第几列的单元格cell的值Excel03
	 */
	public static String getCellValueByRowNumAndColNumExcel03(HSSFSheet hssfSheet, int rowNum, int colNum)
			throws Exception {
		HSSFRow row = hssfSheet.getRow(rowNum);
		if (row == null)
			return "";
		HSSFCell cell = row.getCell(colNum);
		if (cell == null)
			return "";
		return getCellValueExcel03(cell);
	}

	/**
	 * 获得第几行第几列的单元格cell的值Excel07
	 */
	public static String getCellValueByRowNumAndColNumExcel07(XSSFSheet xssfSheet, int rowNum, int colNum)
			throws Exception {
		XSSFRow row = xssfSheet.getRow(rowNum);
		if (row == null)
			return "";
		XSSFCell cell = row.getCell(colNum);
		if (cell == null)
			return "";
		return getCellValueExcel07(cell);
	}

	/**
	 * 解析Excel文件，把一行的数据解析出来，返回一个读取Excel文件的字符串数组。Excel03
	 */
	public static String[] getCellValuesByRowExcel03(HSSFSheet hssfSheet, int rowNum, int colCount) {
		String[] arrays = new String[colCount];
		HSSFRow row = hssfSheet.getRow(rowNum);
		if (row == null) {
			return null;
		}
		for (int i = 0; i < colCount; i++) {
			HSSFCell cell = row.getCell(i);
			if (cell != null) {
				arrays[i] = getCellValueExcel03(cell);
			}
		}
		return arrays;
	}

	/**
	 * 解析Excel文件，把一行的数据解析出来，返回一个读取Excel文件的字符串数组。Excel07
	 */
	public static String[] getCellValuesByRowExcel07(XSSFSheet xssfSheet, int rowNum, int colCount) {
		String[] arrays = new String[colCount];
		XSSFRow row = xssfSheet.getRow(rowNum);
		if (row == null) {
			return null;
		}
		for (int i = 0; i < colCount; i++) {
			XSSFCell cell = row.getCell(i);
			if (cell != null) {
				arrays[i] = getCellValueExcel07(cell);
			}
		}
		return arrays;
	}

	/**
	 * 获得单元格cell的值Excel03
	 */
	private static String getCellValueExcel03(HSSFCell cell) {

		DecimalFormat df = new DecimalFormat("#.##");
		if (cell == null)
			return "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
			}
			return df.format(cell.getNumericCellValue());
		case HSSFCell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case HSSFCell.CELL_TYPE_BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case HSSFCell.CELL_TYPE_FORMULA:
			return String.valueOf(cell.getCellFormula());
		case HSSFCell.CELL_TYPE_BLANK:
			return null;
		case HSSFCell.CELL_TYPE_ERROR:
			return null;
		default:
			return null;
		}
	}

	/**
	 * 获得单元格cell的值Excel07
	 */
	private static String getCellValueExcel07(XSSFCell cell) {
		DecimalFormat df = new DecimalFormat("#.##");
		if (cell == null)
			return "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
			}
			return df.format(cell.getNumericCellValue());
		case HSSFCell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case HSSFCell.CELL_TYPE_BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case HSSFCell.CELL_TYPE_FORMULA:
			return String.valueOf(cell.getCellFormula());
		case HSSFCell.CELL_TYPE_BLANK:
			return null;
		case HSSFCell.CELL_TYPE_ERROR:
			return null;
		default:
			return null;
		}
	}

}