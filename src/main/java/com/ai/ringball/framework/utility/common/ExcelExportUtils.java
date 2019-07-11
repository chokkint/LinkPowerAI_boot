package com.ai.ringball.framework.utility.common;

import com.ai.ringball.framework.base.SimpleMapper;
import com.ai.ringball.framework.constants.SysConstants;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;

public class ExcelExportUtils {

	public static final String ROW_ID = "row_id";

	private static final Logger logger = Logger.getLogger(ExcelExportUtils.class);

	public static void exportExcel(String filename, String ownerName, String tableName, String filePath, SimpleMapper simpleMapper, Map<String, Object> params, PageUtils page) throws Exception {

		logger.debug("===============Export Excel File Start(" + filename + "): " + DateUtils.getDatetime(new Date()) + "=======================");

		params.put("page", page);
		SXSSFWorkbook wb = new SXSSFWorkbook(-1);
		Sheet sh = wb.createSheet(ownerName + "." + tableName);

		// 设置导出Excel工作簿的页面格式
		sh.setDisplayGridlines(false);
		sh.setPrintGridlines(false);
		sh.setFitToPage(true);
		sh.setHorizontallyCenter(true);
		sh.setDefaultColumnWidth(13);
		PrintSetup printSetup = sh.getPrintSetup();
		printSetup.setLandscape(true);
		sh.setAutobreaks(true);
		printSetup.setFitHeight((short) 1);
		printSetup.setFitWidth((short) 1);
		// 设置导出Excel单元格的格式
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle = createExportCellStyle(wb);

		// 创建表头
		Row firstRow = sh.createRow(0);
		firstRow.setHeightInPoints(18f);
		String[] heads = (String[]) params.get(SimpleMapper.TABLE_COLUMN_ARRAY);
		for (int m = 0; m < heads.length; m++) {
			Cell cell = firstRow.createCell(m);
			cell.setCellValue(heads[m]);
			cell.setCellStyle(cellStyle);
		}

		// 生成查询JNDI方式查询数据的SQL
		String sql = "SELECT ";
		sql += (String) params.get(SimpleMapper.TABLE_COLUMN_NAMES);
		sql += " FROM ";
		sql += (String) params.get(SimpleMapper.TABLE_NAME);

		String branch = (String) params.get("branch");
		String WHERE_DATE_SQL = (String) params.get(SimpleMapper.WHERE_DATE_SQL);
		String WHERE_UPPER_SQL = (String) params.get(SimpleMapper.WHERE_UPPER_SQL);

		if (branch != null || WHERE_DATE_SQL != null || WHERE_UPPER_SQL != null) {
			sql += " WHERE 1=1 ";
			if (branch != null) {
				sql += " AND ";
				sql += (String) params.get("branchColum") + " = " + branch;
			}

			if (WHERE_DATE_SQL != null) {
				sql += " AND ";
				sql += " ( " + WHERE_DATE_SQL + " ) ";
			}

			if (WHERE_UPPER_SQL != null) {
				sql += " AND ";
				sql += " ( " + WHERE_UPPER_SQL + " ) ";
			}
		}

		logger.debug("===============Export Excel SQL: " + sql + "=======================");
		// 写入数据

		Connection conn = null;
		Statement sm = null;
		ResultSet rs = null;

		conn = SqlUtils.GetConnection();
		sm = conn.createStatement();
		rs = sm.executeQuery(sql);
		do {
			page.setPageNo(page.getPageNo() + 1);
			logger.debug("Export Row: " + page.getPageNo() + ":" + DateUtils.getDatetime(new Date()));
			// List<Map<String, Object>> maps =
			// simpleMapper.selectByPage(params);

			if (rs != null) {
				int rownum = (page.getPageSize() * (page.getPageNo() - 1)) + 1;
				while (rs.next()) {
					Row row = sh.createRow(rownum);
					for (int j = 0; j < heads.length; j++) {
						String head = heads[j];
						Object value = rs.getString(head);
						Cell cell = row.createCell(j);
						if (value == null) {
							cell.setCellValue(SysConstants.CONSTANT_NULL_STRING);
						} else {
							cell.setCellValue(value.toString());
						}
					}

					if (rownum % 100 == 0) {
						((SXSSFSheet) sh).flushRows(100);
					}
					rownum = rownum + 1;
				}
			}

			/*
			 * if (maps != null && maps.size() > 0) { int rownum =
			 * (page.getPageSize() * (page.getPageNo() - 1)) + 1; for (int i =
			 * 0; i < maps.size(); i++) { Row row = sh.createRow(rownum);
			 * Map<String, Object> objectMap = maps.get(i); for (int j = 0; j <
			 * heads.length; j++) { String head = heads[j]; Object value =
			 * objectMap.get(head); Cell cell = row.createCell(j); if (value ==
			 * null) { cell.setCellValue(SysConstants.CONSTANT_NULL_STRING); }
			 * else { cell.setCellValue(value.toString()); } } } }
			 */
		} while (page.hasNext());

		rs.close();
		sm.close();
		conn.close();

		FileOutputStream out = new FileOutputStream(filePath);
		wb.write(out);
		out.close();
		wb.dispose();
		logger.debug("===============Export Excel File End(" + filename + "): " + DateUtils.getDatetime(new Date()) + "=======================");
	}

	// 设置导出Excel单元格的格式
	private static CellStyle createExportCellStyle(SXSSFWorkbook wb) {
		CellStyle cellStyle = wb.createCellStyle();
		Font headerFont = wb.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 10);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setFont(headerFont);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		return cellStyle;
	}
}
