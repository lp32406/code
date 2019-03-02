package com.eb.dianlianbao_server.util;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	private static final Logger log = Logger.getLogger(FileUtils.class);

	public static void saveFile(HttpServletRequest request, MultipartFile file, List<String> list) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
				String fileName = String.valueOf(new Date().getTime()) + ".png";
				String realPath = uploadMoreImg(request);
				if (StringUtils.isEmpty(realPath)) {
					realPath = Contants.UPLOAD_PATH;
				}
				log.warn("realPath:" + realPath);
				list.add(fileName);
				File saveDir = new File(realPath + fileName);
				if (!saveDir.getParentFile().exists())
					saveDir.getParentFile().mkdirs();
				// 转存文件
				file.transferTo(saveDir);
			} catch (Exception e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			log.warn("file is not empty");
		}
	}

	public static String uploadMoreImg(HttpServletRequest req) throws Throwable {
		String realPath = null;
		realPath = req.getServletContext().getRealPath("/") + "/upload/images/";
		return realPath;
	}
}
