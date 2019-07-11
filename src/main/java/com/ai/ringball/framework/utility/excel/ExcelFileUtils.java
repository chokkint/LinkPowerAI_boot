package com.ai.ringball.framework.utility.excel;

import com.ai.ringball.framework.constants.SysConstants;
import com.ai.ringball.framework.utility.common.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;



public class ExcelFileUtils {

	public static ReadResult readExcelData(File file, ExcelReadCondition condition) {
		ReadResult result = new ReadResult();
		Sheet sheet = null;
		if (file.getName().endsWith("xls")) {
			HSSFWorkbook book = null;
			FileInputStream fileInputStream = null;
			try {
				fileInputStream = new FileInputStream(file);
				book = new HSSFWorkbook(fileInputStream);
				fileInputStream.close();
			} catch (FileNotFoundException e) {
				result.addError("Excel文件打开失败！");
			} catch (IOException e) {
				result.addError("Excel文件解析失败！");
				return result;
			}
			sheet = book.getSheetAt(0);
		} else {
			XSSFWorkbook book = null;
			FileInputStream fileInputStream = null;
			try {
				fileInputStream = new FileInputStream(file);
				book = new XSSFWorkbook(fileInputStream);
				fileInputStream.close();
			} catch (FileNotFoundException e) {
				result.addError("Excel文件打开失败！");
			} catch (IOException e) {
				result.addError("Excel文件解析失败！");
				return result;
			}
			sheet = book.getSheetAt(0);
		}
		readExcelData(sheet, condition, result);
		return result;
	}

	public static ReadResult readExcelData(MultipartFile file, ExcelReadCondition condition) {
		ReadResult result = new ReadResult();
		Sheet sheet = null;
		Workbook book = null;
		try {
			InputStream inputStream = file.getInputStream();
			book = WorkbookFactory.create(inputStream);
			inputStream.close();
		} catch (FileNotFoundException e) {
			result.addError("Excel文件打开失败！");
		} catch (IOException e) {
			result.addError("Excel文件解析失败！");
			return result;
		} catch (InvalidFormatException e) {
			result.addError("Excel文件解析失败！");
			return result;
		}
		sheet = book.getSheetAt(0);
		readExcelData(sheet, condition, result);
		return result;
	}

	private static void readExcelData(Sheet sheet, ExcelReadCondition condition, ReadResult result) {

		int rowLength = sheet.getLastRowNum();
		if (rowLength > condition.getMaxRow()) {
			result.addError("Excel数据超过" + condition.getMaxRow() + "行，超过系统限制，请分批导入！");
			return;
		}

		for (int i = 0; i <= rowLength; i++) {
			Row row = sheet.getRow(i);
			if (row == null || i < condition.getTitleLine()) {
				continue;
			}
			int column = row.getLastCellNum();
			if (column > condition.getMaxColumn()) {
				result.addError("Excel数据超过" + condition.getMaxColumn() + "列，请使用最新模板导入！");
				return;
			}
			List<Object> rowData = new ArrayList<Object>();
			column = condition.getColumn() != 0 ? condition.getColumn() : column;
			for (int j = 0; j < column; j++) {
				// 超过要取得列数则停止
				// if(condition.getColumn()!= 0&&j>=condition.getColumn())break;
				ColumnCondition cc = condition.getConditions().get(j);
				Cell cell = row.getCell(j);

				if (cell == null) {
					if (cc != null && !cc.isNull()) {
						result.addError("Excel文件中第" + (i + 1) + "行第" + (j + 1) + "列不可为空！");
					}
					rowData.add(SysConstants.CONSTANT_NULL_STRING);
					continue;
				}
				rowData.add(getRealValue(cell, cc, result));
				// logger.debug("r:"+i+" , c:"
				// +j+" ,content:"+getValue(cell)+" ,type:" +
				// cell.getCellType());
			}
			result.addData(rowData);
		}
	}

	private static Object getRealValue(Cell cell, ColumnCondition cc, ReadResult result2) {
		Object result = null;
		int type = -1;
		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			type = ColumnCondition.TYPE_BOOLEAN;
			result = cell.getBooleanCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			double d = cell.getNumericCellValue();
			if (DateUtil.isCellDateFormatted(cell)) {
				type = ColumnCondition.TYPE_DATE;
				result = DateUtil.getJavaDate(d);
			} else {
				if (cc != null && cc.getType() == ColumnCondition.TYPE_STRING) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					type = ColumnCondition.TYPE_STRING;
					result = cell.getStringCellValue();
				} else {
					type = ColumnCondition.TYPE_NUMBER;
					result = d;
				}
			}
		} else {
			type = ColumnCondition.TYPE_STRING;
			result = cell.getStringCellValue();
		}
		if (cc == null || cc.getType() == -1) {
			return result;
		}

		if (cc.getType() == ColumnCondition.TYPE_STRING) {
			if (type == ColumnCondition.TYPE_DATE) {
				result = DateUtils.getDate((Date) result);
			} else {
				result = String.valueOf(result);
			}
		} else if (cc.getType() != type) {
			result2.addError("Excel文件中第" + (cell.getRowIndex() + 1) + "行第" + (cell.getColumnIndex() + 1) + "列不为" + ColumnCondition.getTypeCN(cc.getType()) + "格式！");
			return null;
		}
		return result;
	}

	public static void writeExcel(String filePath, WriteExcelArgs writeExcelArgs) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		Map<String, CellStyle> styles = createStyles(wb);

		Sheet sheet = wb.createSheet(writeExcelArgs.getTitle());
		sheet.setDisplayGridlines(false);
		sheet.setPrintGridlines(false);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		PrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setLandscape(true);

		sheet.setAutobreaks(true);
		printSetup.setFitHeight((short) 1);
		printSetup.setFitWidth((short) 1);

		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(18f);
		for (int i = 0; i < writeExcelArgs.getTitles().length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(writeExcelArgs.getTitles()[i]);
			cell.setCellStyle(styles.get("header"));
		}
		sheet.createFreezePane(0, 1);
		Row row;
		Cell cell;
		int rownum = 1;
		for (int i = 0; i < writeExcelArgs.getData().length; i++, rownum++) {
			row = sheet.createRow(rownum);
			if (writeExcelArgs.getData()[i] == null)
				continue;

			for (int j = 0; j < writeExcelArgs.getData()[i].length; j++) {
				cell = row.createCell(j);
				String styleName = "cell_normal";
				Object value = writeExcelArgs.getData()[i][j];
				if (value instanceof Date) {
					cell.setCellValue(DateUtils.getDate((Date) value));
				} else {
					if (value == null) {
						cell.setCellValue(SysConstants.CONSTANT_NULL_STRING);
					} else {
						cell.setCellValue(String.valueOf(value));
					}
				}
				cell.setCellStyle(styles.get(styleName));
			}
		}
		FileOutputStream out = new FileOutputStream(filePath);
		wb.write(out);
		out.close();
	}

	public static void writeExcelByXlsx(String filePath, WriteExcelArgs writeExcelArgs) throws IOException {

		SXSSFWorkbook wb = new SXSSFWorkbook(-1);
		Map<String, CellStyle> styles = createStyles(wb);

		Sheet sheet = wb.createSheet(writeExcelArgs.getTitle());
		sheet.setDisplayGridlines(false);
		sheet.setPrintGridlines(false);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		PrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setLandscape(true);

		sheet.setAutobreaks(true);
		printSetup.setFitHeight((short) 1);
		printSetup.setFitWidth((short) 1);

		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(18f);
		for (int i = 0; i < writeExcelArgs.getTitles().length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(writeExcelArgs.getTitles()[i]);
			cell.setCellStyle(styles.get("header"));
		}
		sheet.createFreezePane(0, 1);
		Row row;
		Cell cell;
		int rownum = 1;
		for (int i = 0; i < writeExcelArgs.getData().length; i++, rownum++) {
			row = sheet.createRow(rownum);
			if (writeExcelArgs.getData()[i] == null)
				continue;

			for (int j = 0; j < writeExcelArgs.getData()[i].length; j++) {
				cell = row.createCell(j);
				String styleName = "cell_normal";
				Object value = writeExcelArgs.getData()[i][j];
				if (value instanceof Date) {
					cell.setCellValue(DateUtils.getDate((Date) value));
				} else {
					if (value == null) {
						cell.setCellValue(SysConstants.CONSTANT_NULL_STRING);
					} else {
						cell.setCellValue(String.valueOf(value));
					}
				}
				cell.setCellStyle(styles.get(styleName));
			}

			if (i % 100 == 0) {
				((SXSSFSheet) sheet).flushRows(100);
			}
		}
		FileOutputStream out = new FileOutputStream(filePath);
		wb.write(out);
		out.close();
		wb.dispose();
	}
	
	public static void writeExcel(OutputStream out, WriteExcelArgs writeExcelArgs) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		Map<String, CellStyle> styles = createStyles(wb);

		Sheet sheet = wb.createSheet(writeExcelArgs.getTitle());
		sheet.setDisplayGridlines(false);
		sheet.setPrintGridlines(false);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		sheet.setDefaultColumnWidth(13);
		PrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setLandscape(true);

		sheet.setAutobreaks(true);
		printSetup.setFitHeight((short) 1);
		printSetup.setFitWidth((short) 1);

		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(18f);
		for (int i = 0; i < writeExcelArgs.getTitles().length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(writeExcelArgs.getTitles()[i]);
			cell.setCellStyle(styles.get("header"));
		}
		sheet.createFreezePane(0, 1);
		Row row;
		Cell cell;
		int rownum = 1;
		for (int i = 0; i < writeExcelArgs.getData().length; i++, rownum++) {
			row = sheet.createRow(rownum);
			if (writeExcelArgs.getData()[i] == null)
				continue;

			for (int j = 0; j < writeExcelArgs.getData()[i].length; j++) {
				cell = row.createCell(j);
				String styleName = "cell_normal";
				Object value = writeExcelArgs.getData()[i][j];
				if (value instanceof Date) {
					cell.setCellValue(DateUtils.getDate((Date) value));
				} else {
					if (value == null) {
						cell.setCellValue(SysConstants.CONSTANT_NULL_STRING);
					} else {
						cell.setCellValue(String.valueOf(value));
					}
				}
				cell.setCellStyle(styles.get(styleName));
			}
		}
		wb.write(out);
	}
	
	public static void writeExcelByXlsx(OutputStream out, WriteExcelArgs writeExcelArgs) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook();
		Map<String, CellStyle> styles = createStyles(wb);

		Sheet sheet = wb.createSheet(writeExcelArgs.getTitle());
		sheet.setDisplayGridlines(false);
		sheet.setPrintGridlines(false);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		sheet.setDefaultColumnWidth(13);
		PrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setLandscape(true);

		sheet.setAutobreaks(true);
		printSetup.setFitHeight((short) 1);
		printSetup.setFitWidth((short) 1);

		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(18f);
		for (int i = 0; i < writeExcelArgs.getTitles().length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(writeExcelArgs.getTitles()[i]);
			cell.setCellStyle(styles.get("header"));
		}
		sheet.createFreezePane(0, 1);
		Row row;
		Cell cell;
		int rownum = 1;
		for (int i = 0; i < writeExcelArgs.getData().length; i++, rownum++) {
			row = sheet.createRow(rownum);
			if (writeExcelArgs.getData()[i] == null)
				continue;

			for (int j = 0; j < writeExcelArgs.getData()[i].length; j++) {
				cell = row.createCell(j);
				String styleName = "cell_normal";
				Object value = writeExcelArgs.getData()[i][j];
				if (value instanceof Date) {
					cell.setCellValue(DateUtils.getDate((Date) value));
				} else {
					if (value == null) {
						cell.setCellValue(SysConstants.CONSTANT_NULL_STRING);
					} else {
						cell.setCellValue(String.valueOf(value));
					}
				}
				cell.setCellStyle(styles.get(styleName));
			}
		}
		wb.write(out);
	}

	/**
	 * create a library of cell styles
	 */
	private static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

		CellStyle style;
		Font headerFont = wb.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 10);
		style = createBorderedStyle(wb);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFont(headerFont);
		styles.put("header", style);

		Font font = wb.createFont();
		font.setFontName("微软雅黑");
		font.setFontHeightInPoints((short) 10);
		style = createBorderedStyle(wb);
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setWrapText(true);
		style.setFont(font);
		styles.put("cell_normal", style);

		style = createBorderedStyle(wb);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setWrapText(true);
		style.setFont(font);
		styles.put("cell_normal_centered", style);

		return styles;
	}

	private static CellStyle createBorderedStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		return style;
	}

	// 根据sheetName获取信息
	// author:hanrongxin
	public static List<Map<String, String>> parseExcelFile(String excelFilePath, int headIndex, int dataIndex, int maxLine) {
		Workbook wb = null;
		Sheet curSheet = null;
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		try {
			InputStream inp = new FileInputStream(excelFilePath);
			wb = WorkbookFactory.create(inp);
			curSheet = wb.getSheetAt(0);
			if(maxLine != -1){
				if((curSheet.getLastRowNum() + 1) > maxLine){
					return null;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("读取sheet异常：" + e);
		}
		List<Map<String, String>> sheetData = parseSheet(curSheet, headIndex, dataIndex);
		result.addAll(sheetData);

		return result;
	}

	public static List<Map<String, String>> parseSheet(Sheet sheet, int headIndex, int dataIndex) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		int rowCount = sheet.getLastRowNum() + 1;
		// 获取表头,作为map的key
		Row headRow = sheet.getRow(headIndex);
		int cellCount = headRow.getLastCellNum();
		String[] heads = parseRow(headRow, cellCount);
		
		boolean isNullOrBlankRow = true;
		for (int i = dataIndex; i < rowCount; i++) {
			isNullOrBlankRow = true;
			Row dataRow = sheet.getRow(i);
			if (dataRow == null) {
				break;
			}
			String[] datas = parseRow(dataRow, cellCount);
			Map<String, String> map = new HashMap<String, String>();
			int rowNum = i + 1;
			map.put(SysConstants.ROW_NUM, rowNum + "");
			for (int j = 0; j < cellCount; j++) {
				if(datas[j] != null && !(SysConstants.CONSTANT_NULL_STRING.equals(datas[j]))){
					isNullOrBlankRow = false;
				}
				map.put(heads[j].toUpperCase(), datas[j]);
			}
			
			if(!isNullOrBlankRow){
				result.add(map);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param row
	 * @param cellCount
	 * @return
	 */
	private static String[] parseRow(Row row, int cellCount) {
		String[] cells = new String[cellCount];
		for (int i = 0; i < cellCount; i++) {
			Cell cell = row.getCell(i);
			if (cell == null) {
				cells[i] = SysConstants.CONSTANT_NULL_STRING;
			} else {
				try {
					if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
						double cellValue = cell.getNumericCellValue();
						if (cellValue % 1.0 == 0) {
							cells[i] = String.valueOf((long) cellValue);
						} else {
							cells[i] = String.valueOf(cellValue);
						}
					} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
						cells[i] = cell.getStringCellValue();
					} else if (Cell.CELL_TYPE_FORMULA == cell.getCellType()) {
						cells[i] = cell.getStringCellValue();
					}
				} catch (Exception e) {
					throw new RuntimeException("读取行异常：" + e);
				}
			}
		}
		return cells;
	}
}
