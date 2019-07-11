package com.ai.ringball.framework.utility.common;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongoUtils {

	/**
	 * 功能说明:重写构造函数
	 */
	public MongoUtils() {
	}

	/**
	 * 功能说明:获取Mongo连接对象
	 * 
	 * @return Mongo连接对象
	 */
	public MongoClient getMongoClient(ServerAddress addr, MongoClientOptions options) {
		
		MongoClient mongoClient = null;
		
		if(options == null){
			MongoClientOptions.Builder builder = MongoClientOptions.builder();
			// 与目标数据库能够建立的最大connection数量为50 
			builder.connectionsPerHost(100);  
			// 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待  
			builder.threadsAllowedToBlockForConnectionMultiplier(50); 
			// 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟 
			// 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception 
			// 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败 
			builder.maxWaitTime(1000*60*2);  
			// 与数据库建立连接的timeout设置为1分钟  
			builder.connectTimeout(1000*60*1);   
			//===================================================//
			options = builder.build(); 
		}
		
		try {
			// 连接到 mongodb 服务
			mongoClient = new MongoClient(addr, options);
			System.out.println("Info : Connect to mongodb successfully!");
		} catch (Exception e) {
			System.out.println("Alert : Connect to mongodb Error!!!!!!!!!!!");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return mongoClient;
	}

	/**
	 * 功能说明:获取指定的数据库对象
	 * 
	 * @param mongoClient Mongo连接对象
	 * @return 数据库对象
	 */
	public MongoDatabase getMongoDataBase(MongoClient mongoClient, String DBName) {
		MongoDatabase mongoDataBase = null;
		try {
			if (mongoClient != null) {
				// 连接到数据库
				mongoDataBase = mongoClient.getDatabase(DBName);
				System.out.println("Info : Connect to DataBase[" + DBName + "] successfully!");
			} else {
				throw new RuntimeException("Alert : Connect to DataBase[" + DBName + "] Error, MongoClient不能够为空!");
			}
		} catch (Exception e) {
			System.out.println("Alert :  Connect to DataBase[" + DBName + "] Error!!!!!!!!!!!");
			e.printStackTrace();
		}
		return mongoDataBase;
	}

	/**
	 * 功能说明:关闭指定的Mongo数据库连接对象和Mongo连接对象
	 * 
	 * @param mongoDataBase 数据库连接
	 * @param mongoClient Mongo连接
	 */
	public void closeMongoClient(MongoDatabase mongoDataBase, MongoClient mongoClient) {
		if (mongoDataBase != null) {
			mongoDataBase = null;
		}
		if (mongoClient != null) {
			mongoClient.close();
		}
		System.out.println("Info : CloseMongoClient successfully!");
	}
}