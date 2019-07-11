package com.ai.ringball.framework.utility.common;

import com.ai.ringball.framework.base.ExportService;
import com.ai.ringball.framework.utility.excel.WriteExcelArgs;

import java.util.List;
import java.util.Map;

public class ExlUtils {

	public static void toExl(WriteExcelArgs writeExcelArgs, ExportService exportService, Map<String, Object> map) {

		PageUtils page = new PageUtils();

		page.setPageSize(100);

		page.setPageNo(1);
		map.put("page", page);
		@SuppressWarnings("unused")
		List<Object> os = exportService.queryEntity(map);
		// 1.object to exl file
		while (page.hasNext()) {
			page.setPageNo(page.getPageNo() + 1);
			os = exportService.queryEntity(map);
			// 1.object to exl file
		}

	}

}
