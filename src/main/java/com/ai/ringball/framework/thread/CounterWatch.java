package com.ai.ringball.framework.thread;

/**
 * 
 * 项目名称：TempCode <br>
 * <br>
 * 
 * 类名称：StopWatch2 <br>
 * <br>
 * 
 * 创建人：Chokkint <br>
 * <br>
 * 
 * 创建时间：2018-10-1 下午11:06:01 <br>
 * <br>
 * 
 * 版本：1.0 <br>
 * <br>
 * 
 * 功能描述：类似Apache的计时器类
 */
public class CounterWatch {
	long begin;
	long end;

	public void start() {
		begin = System.currentTimeMillis();
	}

	public void stop() {
		end = System.currentTimeMillis();
	}

	public long getTime() {
		return end - begin;
	}
}
