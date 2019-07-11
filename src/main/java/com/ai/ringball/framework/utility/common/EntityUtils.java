package com.ai.ringball.framework.utility.common;

import com.ai.ringball.framework.annotation.CommentTag;
import com.ai.ringball.framework.base.BaseEntity;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class EntityUtils {

	public static String getEntityComm(BaseEntity baseEntity) {
		List<Field> fields = ReflectUtils.getFields(baseEntity, BaseEntity.class);
		if (fields == null || fields.size() <= 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);
			CommentTag commentTag = field.getAnnotation(CommentTag.class);
			if (commentTag == null) {
				continue;
			}
			Object fieldValue = ReflectUtils.getFieldValue(baseEntity, field);
			sb.append(commentTag.comment());
			sb.append(":");
			// 取值为null时直接在日志中拼接空字符串
			if (fieldValue == null) {
				sb.append("-");
			} else {
				// 如果取值为日期型的数据，进行格式化YYYY-MM-DD HH:SS:DD，如果为其他类型，直接拼接
				if ("java.util.date".equals(field.getType().getName().toLowerCase())) {
					String tempComm = DateUtils.getDatetime((Date) fieldValue);
					if (tempComm.endsWith("00:00:00")) {
						tempComm = tempComm.substring(0, 10);
					}
					sb.append(tempComm);
				} else {
					sb.append(ReflectUtils.getFieldValue(baseEntity, field));
				}
			}
			if (i == fields.size() - 1) {
				sb.append(",\r\n");
			} else {
				sb.append("\r\n");
			}

		}
		return sb.toString();
	}
}
