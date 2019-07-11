package com.ai.ringball.framework.thread;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 项目名称：TempCode <br>
 * <br>
 * 
 * 类名称：Main <br>
 * <br>
 * 
 * 创建人：Chokkint <br>
 * <br>
 * 
 * 创建时间：2018-10-1 下午10:58:33 <br>
 * <br>
 * 
 * 版本：1.0 <br>
 * <br>
 * 
 * 功能描述：
 */

public class Main {
	final static int BSIZE = 1024 * 1024;
	final static int DATACACHENUM = 10000;

	static int currThreadCount = 0;
	static int maxThreadCount = 9;

	static File roomFilterLogFile = new File("roomFilter.log");
	static File csvFile = new File("D:/temp/20181010/data.csv");


	/**
	 * 
	 * 功能描述：加载CSV <br>
	 * <br>
	 * 
	 * @param callBack
	 * @throws Exception
	 *             void <br>
	 *             <br>
	 *             版本：1.0 <br>
	 *             <br>
	 *             创建人：Chokkint <br>
	 *             <br>
	 *             创建时间：2018-10-1 下午11:01:14
	 */
	@SuppressWarnings("resource")
	public static void loadCSV(CallBackMe<Void> callBack) throws Exception {
		FileChannel inChannel = null;

		try {
			String enterStr = "\n";

			FileInputStream fileInputStream = new FileInputStream(csvFile);
			inChannel = fileInputStream.getChannel();

			ByteBuffer buffer = ByteBuffer.allocate(BSIZE);

			StringBuilder newlinesBui = new StringBuilder();
			int num = 0;
			while (inChannel.read(buffer) != -1) {
				buffer.flip();

				// 数据组合.
				String content = new String(buffer.array());
				newlinesBui.append(content).toString();

				int fromIndex = 0;
				int endIndex = -1;
				// 循环找到 \n
				while ((endIndex = newlinesBui.indexOf(enterStr, fromIndex)) > -1) {
					// 得到一行
					String line = newlinesBui.substring(fromIndex, endIndex);

					num++;
//					callBack.call(num, line);

					fromIndex = endIndex + 1;
				}

				newlinesBui.delete(0, fromIndex);
				buffer.clear();
			}

		} finally {
			if (inChannel != null) {
				inChannel.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		final ExecutorService threadPool = Executors.newFixedThreadPool(maxThreadCount); // 线程池

		final List<Future<String>> threadResultList = new ArrayList<Future<String>>(); // 线程返回集合

		final WriteExcelHandle writeSqlFile = new WriteExcelHandle(DATACACHENUM);
		
		CounterWatch stopWatch = new CounterWatch(); // 计时器
		stopWatch.start();
//
//		loadCSV(new CallBackMe<Void>() {
//
//			@Override
//			public Void call(int num, String str) {
//				String[] strs = str.split(",");
//
//				try {
//					
//			        Map<String, String> dataMap=new HashMap<String, String>();
//			        dataMap.put("name", strs[0].trim());
//			        dataMap.put("card", strs[1]);
//			        dataMap.put("gender", strs[2]);
//			        dataMap.put("birthday", strs[3]);
//			        dataMap.put("address", strs[4]);
//			        dataMap.put("zip", strs[5]);
//			        dataMap.put("mobile", strs[6]);
//			        @SuppressWarnings("rawtypes")
//					List<Map> list=new ArrayList<Map>();
//			        list.add(dataMap);
//
//					Future<String> future = threadPool.submit(new TaskWithResult(writeSqlFile,list));
//
//					threadResultList.add(future);
//					
//				} catch (Exception e) {
//					writeLog("录入错误的数据：:0", Arrays.toString(strs));
//					writeLog("错误的原因：:0", e.getMessage());
//				}
//				return null;
//			}
//		});

//		writeSqlFile.flush(); // 刷新缓存数据

		threadPool.shutdown(); // 关闭线程池
		
		threadPool.awaitTermination(10, TimeUnit.MINUTES);

		stopWatch.stop(); // 停止计时

		System.out.println(String.format("任务完成时间:%s s", stopWatch.getTime()/1000));

	}

	/**
	 * 
	 * 功能描述：写日志 <br>
	 * <br>
	 * 
	 * @param str
	 * @param values
	 *            void <br>
	 *            <br>
	 *            版本：1.0 <br>
	 *            <br>
	 *            创建人：Chokkint <br>
	 *            <br>
	 *            创建时间：2018-10-1 下午11:04:00
	 */
	public static void writeLog(String str, Object... values) {
		// FileUtils.doWriteFile(roomFilterLogFile.getAbsolutePath(), tm(str,
		// values) + "\r\n", null, false);
	}

}
