package com.ai.ringball.framework.thread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 
 * 项目名称：TempCode <br>
 * <br>
 * 
 * 类名称：WriteSqlHandle2 <br>
 * <br>
 * 
 * 创建人：Chokkint <br>
 * <br>
 * 
 * 创建时间：2018-10-1 下午11:05:37 <br>
 * <br>
 * 
 * 版本：1.0 <br>
 * <br>
 * 
 * 功能描述：写SQL处理器
 */
public class WriteExcelHandle {
	ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	WriteLock writeLock = readWriteLock.writeLock();

	List<String> cacheList;

	int currItemCount = 0;

	int dataCacheNum;

	public WriteExcelHandle() {
		cacheList = new ArrayList<String>();
	}

	public WriteExcelHandle(int dataCacheNum) {
		this.dataCacheNum = dataCacheNum;
		cacheList = new ArrayList<String>(dataCacheNum);
	}

	public boolean isCacheExpires() {
		return currItemCount >= dataCacheNum;
	}

	public boolean add(String sqlStr) {
		try {
			writeLock.lock();
			cacheList.add(sqlStr);
			currItemCount++;
			return isCacheExpires();
		} finally {
			writeLock.unlock();
		}
	}

	public List<File> save(String extParam1, Map<String, Map<String, Object>> dataMap) throws Exception {
		List<File> fileList = new ArrayList<File>();
		try {
			writeLock.lock();

			CounterWatch stopWatch = new CounterWatch();
			stopWatch.start();

			WriteExcel ex = new WriteExcel();
			fileList = ex.writeExcel(extParam1, dataMap);

			stopWatch.stop();

//			System.out.println(String.format("%s，消费完成，耗费时间:%s ms,消费数据长度:%s", Thread.currentThread().getName(),
//					stopWatch.getTime(), cacheList.size()));

		} finally {
			writeLock.unlock();
		}
		return fileList;
	}

	public void flush() throws Exception {
		System.out.println(
				String.format("flush线程：%s, 需要保存数据的集合长度:%s", Thread.currentThread().getName(), cacheList.size()));

//		for (String str : cacheList) {
			//bw.write(str + "\r\n");
//		}

		System.out.println(
				String.format("flush线程：%s, 消费完成，消费数据长度：%s", Thread.currentThread().getName(), cacheList.size()));

		cacheList.clear(); // 清空数据

		//closeWrite(bw);
	}

//	private void closeWrite(BufferedWriter bw) throws Exception {
//		bw.flush();
//		bw.close();
//	}	
}
