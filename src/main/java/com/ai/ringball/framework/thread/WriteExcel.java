package com.ai.ringball.framework.thread;

import com.ai.ringball.framework.constants.SysConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WriteExcel {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";
    
    public static void main(String[] args) {
        
        Map<String, String> dataMap=new HashMap<String, String>();
        dataMap.put("BankName", "BankName");
        dataMap.put("Addr", "Addr");
        dataMap.put("Phone", "Phone");
        List<Map<String, String>> list=new ArrayList<Map<String, String>>();
        list.add(dataMap);
//        writeExcel(list, 3, "D:/temp/20181010/test.xlsx");
        
    }

    @SuppressWarnings("unchecked")
	public List<File> writeExcel(String orgCode, Map<String, Map<String, Object>> dataMaps){
    	List<File> downLoadFileList = new ArrayList<File>();
		String vatNo = SysConstants.CONSTANT_NULL_STRING;
		String custName = SysConstants.CONSTANT_NULL_STRING;
		String custAddr = SysConstants.CONSTANT_NULL_STRING;
		String custTel = SysConstants.CONSTANT_NULL_STRING;
		String zipcode = SysConstants.CONSTANT_NULL_STRING;
		BigDecimal amount = null;
		String vatDate = null;
		String contracts = SysConstants.CONSTANT_NULL_STRING;
		BigDecimal total = null;
//		List<VatG3TaxInvoiceOut> detailList = new ArrayList<VatG3TaxInvoiceOut>();
//		for (Map<String, Object> dataMap : dataMaps.values()) {
//			vatNo = dataMap.get("VAT_NO").toString();
//			custName = dataMap.get("CUST_NAME").toString();
//			custAddr = dataMap.get("CUST_ADDR").toString();
//			custTel = dataMap.get("CUST_TEL").toString();
//			zipcode = dataMap.get("ZIPCODE").toString();
//			amount = (BigDecimal) dataMap.get("AMOUNT");
//			vatDate = dataMap.get("VAT_DATE").toString();
//			contracts = dataMap.get("CONTRACTS").toString();
//			total = (BigDecimal) dataMap.get("TOTAL");
//			detailList = (List<VatG3TaxInvoiceOut>) dataMap.get("DETAIL_LIST");
//
//			FileOutputStream out = null;
//			Workbook wb = null;
//			String fileName = SysConstants.CONSTANT_NULL_STRING;
//			List<File> fileList = new ArrayList<File>();
//			try {
//				FileInputStream fis = new FileInputStream(SysConstants.TEMPLATE_FILE_FOLDER + "/InvoiceDetaiedlList.xlsx"); // 获取d://test.xls
//				wb = WorkbookFactory.create(fis);
//
//				// 获取第一个工作表-封面
//				Sheet sheet = wb.getSheetAt(0);
//
//				// 发票号
//				Cell vatTaxNoCell = sheet.getRow(0).getCell(0);
//				if (null == vatTaxNoCell) {
//					vatTaxNoCell = sheet.getRow(0).createCell(0);
//					vatTaxNoCell.setCellType(Cell.CELL_TYPE_STRING);
//				}
//				vatTaxNoCell.setCellValue(vatNo);
//
//				// 邮编
//				Cell zipCodeCell = sheet.getRow(8).getCell(3);
//				if (null == zipCodeCell) {
//					zipCodeCell = sheet.getRow(8).createCell(3);
//					zipCodeCell.setCellType(Cell.CELL_TYPE_STRING);
//				}
//				zipCodeCell.setCellValue(zipcode);
//
//				// 地址
//				Cell addrCell = sheet.getRow(9).getCell(2);
//				if (null == addrCell) {
//					addrCell = sheet.getRow(9).createCell(2);
//					addrCell.setCellType(Cell.CELL_TYPE_STRING);
//				}
//				addrCell.setCellValue(custAddr);
//
//				// 名称
//				Cell custNameCell = sheet.getRow(10).getCell(2);
//				if (null == custNameCell) {
//					custNameCell = sheet.getRow(10).createCell(2);
//					custNameCell.setCellType(Cell.CELL_TYPE_STRING);
//				}
//				custNameCell.setCellValue(custName);
//
//				// 财务部(客户联系人)
//				Cell contractCell = sheet.getRow(11).getCell(2);
//				if (null == contractCell) {
//					contractCell = sheet.getRow(11).createCell(2);
//					contractCell.setCellType(Cell.CELL_TYPE_STRING);
//				}
//				contractCell.setCellValue(contracts);
//
//				// 电话
//				Cell telCell = sheet.getRow(12).getCell(3);
//				if (null == telCell) {
//					telCell = sheet.getRow(12).createCell(3);
//					telCell.setCellType(Cell.CELL_TYPE_STRING);
//				}
//				telCell.setCellValue(custTel);
//
//				// 第二个"Details"sheet
//				Sheet sheet2 = wb.getSheetAt(1);
//
//				// 客户名称
//				Cell companyNameCell = sheet2.getRow(2).getCell(1);
//				companyNameCell.setCellValue(custName);
//
//				// 发票号
//				Cell vatTaxNoCell2 = sheet2.getRow(3).getCell(1);
//				vatTaxNoCell2.setCellValue(vatNo);
//
//				// 合计
//				Cell totalCell = sheet2.getRow(3).getCell(8);
//				if (total == null) {
//					totalCell.setCellValue(amount.doubleValue());
//				} else {
//					totalCell.setCellValue(total.doubleValue());
//				}
//
//				// 有交易流水时写入交易明细清单
//				if (detailList != null && detailList.size() > 0) {
//					Row titleRow = sheet2.getRow(5);
//					Row templateRow = sheet2.getRow(6);
//					// 循环列
//					for (int cellIndex = 0; cellIndex < titleRow.getPhysicalNumberOfCells(); cellIndex++) {
//						Cell currentTitleCell = titleRow.getCell(cellIndex);
//						if (currentTitleCell == null) {
//							continue;
//						}
//
//						if ("编号".equals(currentTitleCell.getStringCellValue())) {
//							for (int rowIndex = 0; rowIndex < detailList.size(); rowIndex++) {
//								Row sevenRow = sheet2.getRow(rowIndex + 6);
//								if (null == sevenRow) {
//									sevenRow = sheet2.createRow(rowIndex + 6);
//								}
//								if (rowIndex != 0) {
//									sevenRow.setHeight(templateRow.getHeight());
//								}
//								Cell cell = sevenRow.getCell(cellIndex);
//
//								if (null == cell) {
//									cell = sevenRow.createCell(cellIndex);
//									cell.setCellType(Cell.CELL_TYPE_STRING);
//								}
//								cell.setCellValue(rowIndex + 1);
//
//								if (rowIndex != 0) {
//									cell.setCellStyle(templateRow.getCell(cellIndex).getCellStyle());
//								}
//							}
//						} else if ("交易日期".equals(currentTitleCell.getStringCellValue())) {
//							for (int rowIndex = 0; rowIndex < detailList.size(); rowIndex++) {
//								Row sevenRow = sheet2.getRow(rowIndex + 6);
//								if (null == sevenRow) {
//									sevenRow = sheet2.createRow(rowIndex + 6);
//								}
//								Cell cell = sevenRow.getCell(cellIndex);
//
//								if (null == cell) {
//									cell = sevenRow.createCell(cellIndex);
//									cell.setCellType(Cell.CELL_TYPE_STRING);
//								}
//								cell.setCellValue(detailList.get(rowIndex).getOdsBusiDt());
//
//								if (rowIndex != 0) {
//									cell.setCellStyle(templateRow.getCell(cellIndex).getCellStyle());
//								}
//							}
//						} else if ("交易分行".equals(currentTitleCell.getStringCellValue())) {
//							for (int rowIndex = 0; rowIndex < detailList.size(); rowIndex++) {
//								Row sevenRow = sheet2.getRow(rowIndex + 6);
//								if (null == sevenRow) {
//									sevenRow = sheet2.createRow(rowIndex + 6);
//								}
//								Cell cell = sevenRow.getCell(cellIndex);
//								if (null == cell) {
//									cell = sevenRow.createCell(cellIndex);
//									cell.setCellType(Cell.CELL_TYPE_STRING);
//								}
//								cell.setCellValue(detailList.get(rowIndex).getOdsBranchCode());
//
//								if (rowIndex != 0) {
//									cell.setCellStyle(templateRow.getCell(cellIndex).getCellStyle());
//								}
//							}
//						} else if ("交易编号".equals(currentTitleCell.getStringCellValue())) {
//							for (int rowIndex = 0; rowIndex < detailList.size(); rowIndex++) {
//								Row sevenRow = sheet2.getRow(rowIndex + 6);
//								if (null == sevenRow) {
//									sevenRow = sheet2.createRow(rowIndex + 6);
//								}
//								Cell cell = sevenRow.getCell(cellIndex);
//								if (null == cell) {
//									cell = sevenRow.createCell(cellIndex);
//									cell.setCellType(Cell.CELL_TYPE_STRING);
//								}
//								cell.setCellValue(detailList.get(rowIndex).getRefNo());
//
//								if (rowIndex != 0) {
//									cell.setCellStyle(templateRow.getCell(cellIndex).getCellStyle());
//								}
//							}
//						} else if ("交易名称".equals(currentTitleCell.getStringCellValue())) {
//							for (int rowIndex = 0; rowIndex < detailList.size(); rowIndex++) {
//								Row sevenRow = sheet2.getRow(rowIndex + 6);
//								if (null == sevenRow) {
//									sevenRow = sheet2.createRow(rowIndex + 6);
//								}
//								Cell cell = sevenRow.getCell(cellIndex);
//								if (null == cell) {
//									cell = sevenRow.createCell(cellIndex);
//									cell.setCellType(Cell.CELL_TYPE_STRING);
//								}
//								cell.setCellValue(detailList.get(rowIndex).getGoodServiceName());
//
//								if (rowIndex != 0) {
//									cell.setCellStyle(templateRow.getCell(cellIndex).getCellStyle());
//								}
//							}
//						} else if ("交易币种".equals(currentTitleCell.getStringCellValue())) {
//							for (int rowIndex = 0; rowIndex < detailList.size(); rowIndex++) {
//								Row sevenRow = sheet2.getRow(rowIndex + 6);
//								if (null == sevenRow) {
//									sevenRow = sheet2.createRow(rowIndex + 6);
//								}
//								Cell cell = sevenRow.getCell(cellIndex);
//								if (null == cell) {
//									cell = sevenRow.createCell(cellIndex);
//									cell.setCellType(Cell.CELL_TYPE_STRING);
//								}
//								cell.setCellValue(detailList.get(rowIndex).getCcy());
//
//								if (rowIndex != 0) {
//									cell.setCellStyle(templateRow.getCell(cellIndex).getCellStyle());
//								}
//							}
//						} else if ("原币金额".equals(currentTitleCell.getStringCellValue())) {
//							for (int rowIndex = 0; rowIndex < detailList.size(); rowIndex++) {
//								Row sevenRow = sheet2.getRow(rowIndex + 6);
//								if (null == sevenRow) {
//									sevenRow = sheet2.createRow(rowIndex + 6);
//								}
//								Cell cell = sevenRow.getCell(cellIndex);
//								if (null == cell) {
//									cell = sevenRow.createCell(cellIndex);
//									cell.setCellType(Cell.CELL_TYPE_STRING);
//								}
//								cell.setCellValue(detailList.get(rowIndex).getOriginalAmt().doubleValue());
//
//								if (rowIndex != 0) {
//									cell.setCellStyle(templateRow.getCell(cellIndex).getCellStyle());
//								}
//							}
//						} else if ("人民币金额".equals(currentTitleCell.getStringCellValue())) {
//							for (int rowIndex = 0; rowIndex < detailList.size(); rowIndex++) {
//								Row sevenRow = sheet2.getRow(rowIndex + 6);
//								if (null == sevenRow) {
//									sevenRow = sheet2.createRow(rowIndex + 6);
//								}
//								Cell cell = sevenRow.getCell(cellIndex);
//								if (null == cell) {
//									cell = sevenRow.createCell(cellIndex);
//									cell.setCellType(Cell.CELL_TYPE_STRING);
//								}
//								cell.setCellValue(detailList.get(rowIndex).getAmtRmb().doubleValue());
//
//								if (rowIndex != 0) {
//									cell.setCellStyle(templateRow.getCell(cellIndex).getCellStyle());
//								}
//							}
//						} else if ("汇率".equals(currentTitleCell.getStringCellValue())) {
//							for (int rowIndex = 0; rowIndex < detailList.size(); rowIndex++) {
//								Row sevenRow = sheet2.getRow(rowIndex + 6);
//								if (null == sevenRow) {
//									sevenRow = sheet2.createRow(rowIndex + 6);
//								}
//								Cell cell = sevenRow.getCell(cellIndex);
//								if (null == cell) {
//									cell = sevenRow.createCell(cellIndex);
//									cell.setCellType(Cell.CELL_TYPE_STRING);
//								}
//								cell.setCellValue(detailList.get(rowIndex).getRmbExchangeRate().doubleValue());
//
//								if (rowIndex != 0) {
//									cell.setCellStyle(templateRow.getCell(cellIndex).getCellStyle());
//								}
//							}
//						} else if (currentTitleCell.getStringCellValue() != null && currentTitleCell.getStringCellValue().contains("不含税金额")) {
//							for (int rowIndex = 0; rowIndex < detailList.size(); rowIndex++) {
//								Row sevenRow = sheet2.getRow(rowIndex + 6);
//								if (null == sevenRow) {
//									sevenRow = sheet2.createRow(rowIndex + 6);
//								}
//								Cell cell = sevenRow.getCell(cellIndex);
//								if (null == cell) {
//									cell = sevenRow.createCell(cellIndex);
//									cell.setCellType(Cell.CELL_TYPE_STRING);
//								}
//								cell.setCellValue(detailList.get(rowIndex).getMoney().doubleValue());
//
//								if (rowIndex != 0) {
//									cell.setCellStyle(templateRow.getCell(cellIndex).getCellStyle());
//								}
//							}
//						} else if (currentTitleCell.getStringCellValue() != null && currentTitleCell.getStringCellValue().contains("税金")) {
//							for (int rowIndex = 0; rowIndex < detailList.size(); rowIndex++) {
//								Row sevenRow = sheet2.getRow(rowIndex + 6);
//								if (null == sevenRow) {
//									sevenRow = sheet2.createRow(rowIndex + 6);
//								}
//								Cell cell = sevenRow.getCell(cellIndex);
//								if (null == cell) {
//									cell = sevenRow.createCell(cellIndex);
//									cell.setCellType(Cell.CELL_TYPE_STRING);
//								}
//								cell.setCellValue(detailList.get(rowIndex).getTaxMoney().doubleValue());
//
//								if (rowIndex != 0) {
//									cell.setCellStyle(templateRow.getCell(cellIndex).getCellStyle());
//								}
//							}
//						}
//					}
//				}
//				fileName = orgCode + "_" + "InvoiceDetaiedlList_" + vatNo + "_" + vatDate;
//				out = new FileOutputStream(SysConstants.DOWNLOAD_FILE_ROOT_FOLDER + File.separator + fileName + ".xlsx");
//				wb.write(out);
//				fileList.add(new File(SysConstants.DOWNLOAD_FILE_ROOT_FOLDER + File.separator + fileName + ".xlsx"));
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					if (out != null) {
//						out.flush();
//						out.close();
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			downLoadFileList.addAll(fileList);
//		}
		return downLoadFileList;
    }

    /**
     * 判断Excel的版本,获取Workbook
     * @param in
     * @param filename
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}