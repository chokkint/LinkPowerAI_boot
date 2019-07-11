package com.ai.ringball.framework.base;

import com.ai.ringball.framework.utility.common.PropertyUtil;
import com.mongodb.ServerAddress;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class CommonBizService {

//	@Autowired
//	private CommonBizMapper commonBizMapper;

	/**
	 * 功能说明:从数据库系统参数配置表中获取相关mongoDB连接配置信息
	 * 
	 * @return mongoDB连接配置信息存储对象
	 */
	public ServerAddress getServerAddressObject() {

		Properties pro = PropertyUtil.getResourceFile("jdbc/jdbc.properties");

		String serverIp = pro.getProperty("mongo.host");
		String serverPort = pro.getProperty("mongo.port");
		// String userName = pro.getProperty("mongo.username");
		// String password = pro.getProperty("mongo.password");

		ServerAddress addr = new ServerAddress(serverIp, Integer.valueOf(serverPort));
		return addr;
	}
}
