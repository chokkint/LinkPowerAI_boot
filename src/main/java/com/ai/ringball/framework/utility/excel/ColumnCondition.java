package com.ai.ringball.framework.utility.excel;

public class ColumnCondition {

	private boolean isNull = true;

	private int type = -1;

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public ColumnCondition(boolean isNull, int type) {
		this.isNull = isNull;
		this.type = type;
	}

	public ColumnCondition(boolean isNull) {
		this.isNull = isNull;
	}

	public ColumnCondition(int type) {
		this.type = type;
	}

	public static String getTypeCN(int type) {
		String[] typecn = { "字符串", "数字", "日期", "布尔" };
		return typecn[type];
	}

	public static final int TYPE_STRING = 0;
	public static final int TYPE_NUMBER = 1;
	public static final int TYPE_DATE = 2;
	public static final int TYPE_BOOLEAN = 3;

}
