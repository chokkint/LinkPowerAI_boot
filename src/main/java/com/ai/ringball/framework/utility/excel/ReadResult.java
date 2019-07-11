package com.ai.ringball.framework.utility.excel;

import com.ai.ringball.framework.constants.SysConstants;

import java.util.ArrayList;
import java.util.List;


public class ReadResult {

	private List<List<Object>> data;
	private List<String> errorList;
	private String isSuccess;

	public List<List<Object>> getData() {
		return data;
	}

	public void setData(List<List<Object>> data) {
		this.data = data;
	}

	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public ReadResult() {
		this.data = new ArrayList<List<Object>>();
		this.errorList = new ArrayList<String>();
		this.isSuccess = SysConstants.ERROR_CODE_IMPORT_FILE_SUCCESS;
	}

	public void addError(String message) {
		this.isSuccess = SysConstants.ERROR_CODE_IMPORT_FILE_ERROR;
		this.errorList.add(message);
	}

	public void addData(List<Object> rowData) {
		this.data.add(rowData);
	}
}
