package com.jt.manage.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;

@Service
public class FileServiceImpl implements FileService {
	
	private String localPath = "E:/jt-upload/";//本地磁盘路径
	private String urlPath = "http://image.jt.com/";
	/*
	 * 1.判断文件是否为图片  .jpg|png|gif
	 * 2.判断是否为恶意程序
	 * 3.分文件存储
	 * 4.保证文件不重名
	 */
	@Override
	public PicUploadResult uploadFile(MultipartFile uploadFile) {
		PicUploadResult result = new PicUploadResult();
		//1.获取图片名称   ABC.JPG|PNG|GIF
		String fileName = uploadFile.getOriginalFilename();
		fileName = fileName.toLowerCase();//将字符全部小写
		//2.使用正则表达式判断
		if(!fileName.matches("^.*(jpg|png|gif)$")){
			result.setError(1);//表示不是图片
			return result;
		}
		//3.判断图片是否为恶意程序
		try {
			BufferedImage bufferedImage = 
					ImageIO.read(uploadFile.getInputStream());
			
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			
			if(width == 0 || height ==0){
				result.setError(1);
				return result;
			}
			
			//4.为了实现分文件存储 yyyy/MM/dd
			String dateDir = 
		new SimpleDateFormat("yyyy/MM/dd")
		.format(new Date()).toString();
			
			//创建文件夹   E:/jt-upload/2018/11/11
			String fileDir = localPath + dateDir;
			File dirFile = new File(fileDir);
		
			if(!dirFile.exists()){
				dirFile.mkdirs();
			}
			
			/*
			 * 5.防止文件重名
			 * 	 5.1截取文件的后缀
			 *   5.2使用UUID当文件名 + 随机数3位
			 */
			String fileType = 
					fileName.substring(fileName.lastIndexOf("."));
			
			String UUIDName = UUID.randomUUID().toString()
					.replace("-", "");
			int randomNum = new Random().nextInt(1000);
			
			//fa907e5e9f9a11e891a854ee75ecd89b123.jpg
			String realFileName = UUIDName + randomNum + fileType;
			
			//实现文件上传
			/**
			 * E:jt-upload/yyyy/MM/dd/54ee75ecd89b123.jpg
			 */
			String realLocalPath = fileDir + "/" + realFileName;
			uploadFile.transferTo(new File(realLocalPath));
			
			//设定图片宽度和高度
			result.setHeight(height +"");
			result.setWidth(width + "");
			
			/**
			 * 现图片回显 定义网络请求路径
			 * http://image.jt.com/yyyy/MM/ddasdfasdfadsfasdf.jpg
			 */
			String realUrlPath = urlPath + dateDir + "/" + realFileName;
			result.setUrl(realUrlPath);
		} catch (Exception e) {
			e.printStackTrace();
			//不是图片
			result.setError(1);
			return result;
		}
		return result;
	}
}
