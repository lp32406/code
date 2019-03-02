package com.eb.dianlianbao_server.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;


public class ExcelUtils {

	/**
	 * 导出Excel
	 * <p></p>
	 * @param <T>
	 * @param sheetTitle
	 * @param title
	 * @param list
	 * @return
	 */
	public static <T> byte[] export(String sheetTitle, String[] title, List<T> list) {

		HSSFWorkbook wb = new HSSFWorkbook();// 创建excel表
		HSSFSheet sheet = wb.createSheet(sheetTitle);
		sheet.setDefaultColumnWidth(20);// 设置默认行宽

		// 表头样式（加粗，水平居中，垂直居中）
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
		// 设置边框样式
		cellStyle.setBorderBottom(BorderStyle.THIN); // 下边框
		cellStyle.setBorderLeft(BorderStyle.THIN);// 左边框
		cellStyle.setBorderTop(BorderStyle.THIN);// 上边框
		cellStyle.setBorderRight(BorderStyle.THIN);// 右边框

		HSSFFont fontStyle = wb.createFont();
		fontStyle.setBold(true);
		cellStyle.setFont(fontStyle);

		// 标题样式（加粗，垂直居中）
		HSSFCellStyle cellStyle2 = wb.createCellStyle();
		cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
		cellStyle2.setFont(fontStyle);

		// 设置边框样式HSSFCellStyle.BORDER_THIN
		cellStyle2.setBorderBottom(BorderStyle.THIN); // 下边框
		cellStyle2.setBorderLeft(BorderStyle.THIN);// 左边框
		cellStyle2.setBorderTop(BorderStyle.THIN);// 上边框
		cellStyle2.setBorderRight(BorderStyle.THIN);// 右边框

		// 字段样式（垂直居中）
		HSSFCellStyle cellStyle3 = wb.createCellStyle();
		cellStyle3.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中

		// 设置边框样式
		cellStyle3.setBorderBottom(BorderStyle.THIN); // 下边框
		cellStyle3.setBorderLeft(BorderStyle.THIN);// 左边框
		cellStyle3.setBorderTop(BorderStyle.THIN);// 上边框
		cellStyle3.setBorderRight(BorderStyle.THIN);// 右边框

//		// 创建表头
//		HSSFRow row = sheet.createRow(0);
//		row.setHeightInPoints(20);// 行高
//
//		HSSFCell cell = row.createCell(0);
//		cell.setCellValue(sheetTitle);
//		cell.setCellStyle(cellStyle);
//
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (title.length - 1)));

		// 创建标题
		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.setHeightInPoints(20);

		HSSFCell hc;
		for (int i = 0; i < title.length; i++) {
			hc = rowTitle.createCell(i);
			hc.setCellValue(title[i]);
			hc.setCellStyle(cellStyle2);
		}

		byte result[] = null;
		ByteArrayOutputStream out = null;

		try {
			// 创建表格数据
			Field[] fields;
			int i = 1;

			for (T obj : list) {
				fields = obj.getClass().getDeclaredFields();
				HSSFRow rowBody = sheet.createRow(i);
				rowBody.setHeightInPoints(20);
				int j = 0;
				for (Field f : fields) {
					f.setAccessible(true);
					Object va = f.get(obj);
					if (null == va) {
						va = "";
					}

					hc = rowBody.createCell(j);
					hc.setCellValue(va.toString());
					hc.setCellStyle(cellStyle3);
					j++;
				}
				i++;
			}
			out = new ByteArrayOutputStream();
			wb.write(out);
			result = out.toByteArray();
		} catch (Exception ex) {
		} finally {
			try {
				if (null != out) {
					out.close();
				}
			} catch (IOException ex) {

			} finally {
				try {
					wb.close();
				} catch (IOException ex) {
				}
			}
		}

		return result;
	}

	public static void exportFile(HttpServletResponse response, byte[] b, String filename) throws UnsupportedEncodingException, IOException {
		// 判断文件名是否有后缀
		if (filename.lastIndexOf(".") == -1) {
			throw new IllegalArgumentException("文件名错误！");
		}

		InputStream is = new ByteArrayInputStream(b);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "iso-8859-1"));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}

}
