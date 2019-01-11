package com.jt.manage.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;
import com.jt.manage.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	/*
	 * 要求:如果文件上传完成后,再次重定向到文件上传页面
	 * 注意事项:
	 * 	1.文件的名称必须和页面保持一致
	 * 	2.重定向写法
	 * 	  1.使用response对象
	 * 	  2.利用redirect重定向
	 * 文件上传步骤:
	 * 	1.先获取文件名称
	 *  2.定义文件上传的路径
	 *  3.调用mvc工具类输出文件
	 */
	@RequestMapping("/file")
	public String file(MultipartFile fileName) throws IllegalStateException, IOException{
		//1.获取文件名称    abc.jpg
		String name = fileName.getOriginalFilename();
		//2.定义上传路径
		String path = "E:/jt-upload";
		//3.判断文件是否存在 如果不存在则新建文件夹
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		//E:/jt-upload/abc.jpg
		fileName.transferTo(new File(path + "/" + name));
		//response.sendRedirect("/file.jsp");
		return "redirect:/file.jsp"; //重定向
		//return "forward:/file.jsp";  //转发
	}
	
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PicUploadResult uploadFile(MultipartFile uploadFile){
		
		return fileService.uploadFile(uploadFile);
	}
	
	
	
	
	
	
	
	
	
	
}
