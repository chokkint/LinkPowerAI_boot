package com.ai.ringball.framework.utility.echarts;

import com.ai.ringball.framework.constants.SysConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EChartsUtils {

	public static Map<String, Object> setOption_Title(String text, String subtext) {
		Map<String, Object> titleMap = new HashMap<String, Object>();

		// set default data detail
		titleMap.put("x", "center");
		if (text != null && text.length() > 30) {
			titleMap.put("text", text.substring(0, 30) + "\n" + text.substring(30));
		} else {
			titleMap.put("text", text);
		}

		if (subtext != null && !SysConstants.CONSTANT_NULL_STRING.equals(subtext)) {
			titleMap.put("subtext", subtext);
		}

		return titleMap;
	}

	public static Map<String, Object> getDefaultOption_Tooltip(String chartType) {
		Map<String, Object> tooltipMap = new HashMap<String, Object>();

		// set default data detail
		tooltipMap.put("show", true);
		if ("line".equals(chartType) || "bar".equals(chartType)) {
			tooltipMap.put("trigger", "axis");
			// tooltipMap.put("formatter", "{a} <br/>{b} : {c}");
		} else if ("pie".equals(chartType) || "funnel".equals(chartType)) {
			tooltipMap.put("trigger", "item");
			tooltipMap.put("formatter", "{a} <br/>{b} : {c} ({d}%)");
		}

		return tooltipMap;
	}

	public static Map<String, Object> setDefaultOption_Legend(List<Object> data) {
		Map<String, Object> legendMap = new HashMap<String, Object>();

		// set default data detail
		legendMap.put("show", true);
		legendMap.put("orient", "vertical");
		legendMap.put("x", "left");
		legendMap.put("data", data);
		return legendMap;
	}

	public static Map<String, Object> setDefaultOption_Toolbox(String chartType) {
		Map<String, Object> toolboxMap = new HashMap<String, Object>();
		Map<String, Object> featureMap = new HashMap<String, Object>();
		Map<String, Object> dataViewMap = new HashMap<String, Object>();
		Map<String, Object> magicTypeMap = new HashMap<String, Object>();
		Map<String, Object> restoreMap = new HashMap<String, Object>();
		Map<String, Object> saveAsImageMap = new HashMap<String, Object>();
		Map<String, Object> magicTypeTitleMap = new HashMap<String, Object>();

		dataViewMap.put("show", true);
		dataViewMap.put("title", "数据视图|Data View");
		dataViewMap.put("readOnly", true);
		dataViewMap.put("lang", new String[] { "数据视图|Data View", "关闭|close", "刷新|refresh" });

		magicTypeMap.put("show", true);
		if ("line".equals(chartType) || "bar".equals(chartType)) {
			magicTypeMap.put("type", new String[] { "line", "bar" });
		} else if ("pie".equals(chartType) || "funnel".equals(chartType)) {
			magicTypeMap.put("type", new String[] { "pie", "funnel" });
		}
		magicTypeTitleMap.put("line", "折线图切换|Line View");
		magicTypeTitleMap.put("bar", "柱形图切换|Bar View");
		magicTypeTitleMap.put("pie", "饼图切换|Pie View");
		magicTypeTitleMap.put("stack", "堆积|Stack View");
		magicTypeTitleMap.put("tiled", "平铺|Tiled View");
		magicTypeTitleMap.put("funnel", "漏斗图切换|Funnel View");
		magicTypeMap.put("title", magicTypeTitleMap);

		restoreMap.put("show", true);
		restoreMap.put("title", "还原|Restore");

		saveAsImageMap.put("show", true);
		saveAsImageMap.put("title", "保存为图片|Save As Image");

		featureMap.put("dataView", dataViewMap);
		featureMap.put("magicType", magicTypeMap);
		featureMap.put("restore", restoreMap);
		featureMap.put("saveAsImage", saveAsImageMap);

		// set default data detail
		toolboxMap.put("show", true);
		toolboxMap.put("orient", "vertical");
		toolboxMap.put("feature", featureMap);
		return toolboxMap;
	}

	public static Map<String, Object> getDefaultOption_Grid() {
		Map<String, Object> gridMap = new HashMap<String, Object>();

		gridMap.put("height", 250);
		gridMap.put("x", 100);
		gridMap.put("y", 85);
		return gridMap;
	}

	public static Map<String, Object> setDefaultOption_XAxis(List<Object> data) {
		Map<String, Object> xAxisMap = new HashMap<String, Object>();
		Map<String, Object> axisLabelMap = new HashMap<String, Object>();

		// set default data detail
		xAxisMap.put("type", "category");
		xAxisMap.put("data", data);

		// set options for axis label
		axisLabelMap.put("interval", 0);
		axisLabelMap.put("rotate", -70);
		xAxisMap.put("axisLabel", axisLabelMap);
		return xAxisMap;
	}

	public static Map<String, Object> getDefaultOption_YAxis() {
		Map<String, Object> yAxisMap = new HashMap<String, Object>();
		Map<String, Object> axisLabelMap = new HashMap<String, Object>();

		axisLabelMap.put("formatter", "{value}");

		// set default data detail
		yAxisMap.put("type", "value");
		yAxisMap.put("axisLabel", axisLabelMap);
		return yAxisMap;
	}

	public static Map<String, Object> getDefaultOption_Series_ItemStyle() {
		Map<String, Object> itemStyleMap = new HashMap<String, Object>();
		Map<String, Object> normalMap = new HashMap<String, Object>();
		Map<String, Object> labelMap = new HashMap<String, Object>();

		// set default data detail
		labelMap.put("show", true);
		labelMap.put("position", "top");
		labelMap.put("formatter", "{c}");

		// set data
		normalMap.put("label", labelMap);
		itemStyleMap.put("normal", normalMap);

		return itemStyleMap;
	}

	public static Map<String, Object> getDefaultOption_Series_MarkPoint() {
		Map<String, Object> markPointMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

		// set default data detail
		dataMap.put("type", "max");
		dataMap.put("name", "最大值|Max Value");
		dataList.add(dataMap);
		dataMap = new HashMap<String, Object>();
		dataMap.put("type", "min");
		dataMap.put("name", "最小值|Min Value");
		dataList.add(dataMap);

		// set data
		markPointMap.put("data", dataList);

		// set markPoint
		return markPointMap;
	}

	public static Map<String, Object> getDefaultOption_Series_MarkLine() {
		Map<String, Object> markLineMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

		// set default data detail
		dataMap.put("type", "average");
		dataMap.put("name", "平均值|Average Value");
		dataList.add(dataMap);

		// set data
		markLineMap.put("data", dataList);

		return markLineMap;
	}
}
