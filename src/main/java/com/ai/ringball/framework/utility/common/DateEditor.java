package com.ai.ringball.framework.utility.common;

import com.ai.ringball.framework.constants.SysConstants;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEditor extends PropertyEditorSupport {

	private static final String DATEFORMAT = "yyyy-MM-dd";
	private static final String TIMEFORMAT = "yyyy-MM-dd HH:mm:ss";

	private DateFormat dateFormat;
	private boolean allowEmpty = true;

	public DateEditor() {
	}

	public DateEditor(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public DateEditor(DateFormat dateFormat, boolean allowEmpty) {
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
	}

	/**
	 * Parse the Date from the given text, using the specified DateFormat.
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		} else {
			try {
				if (this.dateFormat != null)
					setValue(this.dateFormat.parse(text));
				else {
					if (text.contains(":"))
						setValue(new SimpleDateFormat(TIMEFORMAT).parse(text));
					else
						setValue(new SimpleDateFormat(DATEFORMAT).parse(text));
				}
			} catch (ParseException ex) {
				// 处理用户手动输入日期格式为YYYYMMDD情况
				if (text.length() == 8) {
					String newText = text.substring(0, 4) + "-" + text.substring(4, 6) + "-" + text.substring(6, 8);
					try {
						if (this.dateFormat != null)
							setValue(this.dateFormat.parse(newText));
						else {
							setValue(new SimpleDateFormat(DATEFORMAT).parse(newText));
						}
					} catch (ParseException ex2) {
						throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
					}
				} else {
					throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
				}
			}
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		DateFormat dateFormat = this.dateFormat;
		if (dateFormat == null)
			dateFormat = new SimpleDateFormat(TIMEFORMAT);
		return (value != null ? dateFormat.format(value) : SysConstants.CONSTANT_NULL_STRING);
	}
}
