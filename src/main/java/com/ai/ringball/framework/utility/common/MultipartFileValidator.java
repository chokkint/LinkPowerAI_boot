package com.ai.ringball.framework.utility.common;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileValidator {

	private final static long MAX_SIZE = 1024 * 1024;

	/**
	 * 文件大小上限
	 */
	private long maxSize = MAX_SIZE;

	/**
	 * 可接受的文件content-type
	 */
	private List<String> allowedContentTypes;

	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		throw new Exception("请设定符合上传的文件格式.");
	}

	/**
	 * 验证上传文件是否合法，如果不合法那么会抛出异常
	 * 
	 * @param file
	 *            用户上传的文件封装类
	 * @throws Exception
	 */
	public void validate(MultipartFile file) throws Exception {
		if (file == null) {
			throw new Exception("文件未找到");
		}
		String contentType = file.getContentType();
		if (file.getSize() > maxSize)
			throw new Exception("文件大小超过系统要求的" + maxSize / 1024 / 1024 + "M");
		if (!allowedContentTypes.contains(contentType))
			throw new Exception("文件格式不符合要求");
	}

	/**
	 * 设置文件上传大小上限
	 * 
	 * @param maxSize
	 *            文件上传大小上限
	 */
	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * 设置可接受的文件content-type数组
	 * 
	 * @param allowedContentTypes
	 *            可接受的文件content-type数组
	 */
	public void setAllowedContentTypes(String... contentTypes) {
		allowedContentTypes = new ArrayList<String>();
		for (String contentType : contentTypes) {
			allowedContentTypes.add(contentType);
		}
	}
}
