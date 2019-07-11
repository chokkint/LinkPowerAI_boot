package com.ai.ringball.framework.base;

import java.util.List;
import java.util.Map;

public interface SimpleMapper {

	/**
	 * 表名key
	 */
	public static final String TABLE_NAME = "ods_table_name";

	/**
	 * 字段名key
	 */
	public static final String TABLE_COLUMN_NAMES = "ods_table_column_names";

	/**
	 * 字段名数组Key
	 */
	public static final String TABLE_COLUMN_ARRAY = "ods_table_column_array";

	/**
	 * 日期查询条件SQL语句Key
	 */
	public static final String WHERE_DATE_SQL = "where_date_sql";

	/**
	 * 高级查询条件SQL语句Key
	 */
	public static final String WHERE_UPPER_SQL = "where_upper_sql";

	/**
	 * 直接执行SQL
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> executeSQL(String sql);

	/**
	 * 查询
	 * 
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> select(Map<String, Object> params);

	/**
	 * 查询
	 * 
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectByPage(Map<String, Object> params);

}
