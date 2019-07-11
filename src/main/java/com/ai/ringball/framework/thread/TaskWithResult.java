package com.ai.ringball.framework.thread;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 
 * 项目名称：TempCode <br>
 * <br>
 * 
 * 类名称：TaskWithResult <br>
 * <br>
 * 
 * 创建人：Chokkint <br>
 * <br>
 * 
 * 创建时间：2018-10-1 下午11:04:25 <br>
 * <br>
 * 
 * 版本：1.0 <br>
 * <br>
 * 
 * 功能描述：任务与结果
 */
public class TaskWithResult implements Callable<Map<String, Object>> {

	String extParam1;

	WriteExcelHandle handle2;

	Map<String, Map<String, Object>> dataMap;

	public TaskWithResult(WriteExcelHandle handle2, Map<String, Map<String, Object>> dataMap, String extParam1) {
		this.handle2 = handle2;
		this.dataMap = dataMap;
		this.extParam1 = extParam1;
	}

	@Override
	public Map<String, Object> call() throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String fileName = Thread.currentThread().getName();
		List<File> downLoadFileList = new ArrayList<File>();
		downLoadFileList = handle2.save(extParam1, dataMap);

		resultMap.put("threadName", fileName);
		resultMap.put("dataset", downLoadFileList);
		return resultMap;
	}
}