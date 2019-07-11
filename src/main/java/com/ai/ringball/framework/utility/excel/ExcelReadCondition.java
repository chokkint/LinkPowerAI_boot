package com.ai.ringball.framework.utility.excel;

import java.util.HashMap;
import java.util.Map;

public class ExcelReadCondition {

	// 每列的条件
	private Map<Integer, ColumnCondition> conditions = new HashMap<Integer, ColumnCondition>();

	// 标题多少行
	private int titleLine = 0;

	// 最大行数
	private int maxRow = 1000;

	// 最大列数
	private int maxColumn = 100;

	// 取的列数
	private int column = 0;

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getTitleLine() {
		return titleLine;
	}

	public void setTitleLine(int titleLine) {
		this.titleLine = titleLine;
	}

	public int getMaxRow() {
		return maxRow;
	}

	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}

	public int getMaxColumn() {
		return maxColumn;
	}

	public void setMaxColumn(int maxColumn) {
		this.maxColumn = maxColumn;
	}

	public Map<Integer, ColumnCondition> getConditions() {
		return conditions;
	}

	public void setConditions(Map<Integer, ColumnCondition> conditions) {
		this.conditions = conditions;
	}

	public void addConditions(int columnNum, ColumnCondition columnCondition) {
		this.conditions.put(columnNum, columnCondition);
	}

	public void addConditions(int columnNum, boolean isNull, int type) {
		this.conditions.put(columnNum, new ColumnCondition(isNull, type));
	}

	public void addConditions(int columnNum, boolean isNull) {
		this.conditions.put(columnNum, new ColumnCondition(isNull));
	}

}
