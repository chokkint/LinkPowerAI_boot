package com.ai.ringball.framework.utility.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

public class ExportTxt {
	public static void exportTxt(Object[][] data, String filename, String[] title, HttpServletResponse response) {
		OutputStream output;
		int contentLen = 0;
		try {
			output = response.getOutputStream();
			StringBuffer bf = new StringBuffer();
			for (int i = 0; i < title.length; i++) {
				bf.append(title[i] + ",");
			}
			int titleLen = bf.length();
			bf.deleteCharAt(titleLen - 1);
			bf.append("\r\n");
			bf.append("\r\n");
			for (int j = 0; j < data.length; j++) {
				for (int k = 0; k < data[j].length; k++) {
					Object obj = data[j][k];
					if (obj instanceof Date) {
						data[j][k] = DateUtils.getDatetimeCN((Date) obj);
					}
					bf.append(data[j][k] + ",");
				}
				if (bf.lastIndexOf(",") == bf.length() - 1) {
					contentLen = bf.length();
					bf.deleteCharAt(contentLen - 1);
					bf.append("\r\n");
				}
			}
			output.write(bf.toString().getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void exportTxt(Object[][] data, String filename, String[] title, OutputStream output) {
		int contentLen = 0;
		try {
			StringBuffer bf = new StringBuffer();
			for (int i = 0; i < title.length; i++) {
				bf.append(title[i] + ",");
			}
			int titleLen = bf.length();
			bf.deleteCharAt(titleLen - 1);
			bf.append("\r\n");
			bf.append("\r\n");
			for (int j = 0; j < data.length; j++) {
				for (int k = 0; k < data[j].length; k++) {
					Object obj = data[j][k];
					if (obj instanceof Date) {
						data[j][k] = DateUtils.getDatetimeCN((Date) obj);
					}
					bf.append(data[j][k] + ",");
				}
				if (bf.lastIndexOf(",") == bf.length() - 1) {
					contentLen = bf.length();
					bf.deleteCharAt(contentLen - 1);
					bf.append("\r\n");
				}
			}
			output.write(bf.toString().getBytes());
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
