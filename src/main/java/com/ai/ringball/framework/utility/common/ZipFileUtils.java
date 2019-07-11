package com.ai.ringball.framework.utility.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

public class ZipFileUtils {

	static private  final Logger logger = Logger.getLogger(ZipFileUtils.class);

	public static File zipFiles(String strZipName, String zipFilePath, List<File> fileList) throws IOException {

		byte[] buffer = new byte[1024];

		String zipFileFullPath = zipFilePath + "/" + strZipName;
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileFullPath)));
		
		logger.debug("服务器端生成ZIP文件(" + zipFileFullPath + ")开始!");
		if(fileList != null && fileList.size() > 0){
			for (int i = 0; i < fileList.size(); i++) {
				FileInputStream fis = new FileInputStream(fileList.get(i));
				out.putNextEntry(new ZipEntry(fileList.get(i).getName()));

				int len;
				// 读入需要下载的文件的内容，打包到ZIP文件
				while ((len = fis.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				out.closeEntry();
				fis.close();
			}
		}
		out.close();
		logger.debug("服务器端生成ZIP文件(" + zipFileFullPath + ")结束!");
		
		return new File(zipFileFullPath);
	}
	
	public static void cleanUpFiles(List<File> fileList){
		logger.debug("清理服务器上临时文件开始!");
		for(File file:fileList){
			logger.debug(file.getName()+"删除"+file.delete());
		}
		logger.debug("清理服务器上临时文件结束!");
	}
}
