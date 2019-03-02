package com.eb.dianlianbao_server.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.axis.utils.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class ImgUtils {
	public static String uploadUrlImg(String imgUrl,HttpServletRequest req) throws Throwable {

		URL url = new URL(imgUrl);
		// 打开链接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置请求方式"GET"
		conn.setRequestMethod("GET");
		// 超时响应时间
		conn.setConnectTimeout(10 * 1000);
		// 通过输入流获取图片数
		InputStream inStream = conn.getInputStream();
		// 得到图片的二进制数据，以二进制封装得到数据，具有通用
		byte[] data = readInputStream(inStream);
		// 个文件对象用来保存图片，默认保存当前工程根目
		String time = UUID.randomUUID()+ ".png";
		String realPath = Contants.uploadImagesPath;
		if (StringUtils.isEmpty(realPath)) {
			realPath = req.getServletContext().getRealPath("/") + "upload//images//";
		}
		File imageFile = new File(realPath + time);
		// 创建输出
		FileOutputStream outStream = new FileOutputStream(imageFile);
		// 写入数据
		outStream.write(data);
		// 关闭输出
		outStream.close();
		return time;
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建Buffer
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完
		int len = 0;
		// 使用�?个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流�?buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长�?
			outStream.write(buffer, 0, len);
		}
		// 关闭输入
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}

	public static List<String> saveFile(HttpServletRequest req, MultipartFile file, List<String> list) {
		int x;//定义两变量
		Random ne=new Random();//实例化一个random的对象ne
		x=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
		String imgs = String.valueOf(new Date().getTime() + x) + ".png";
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
				String filePath = req.getServletContext().getRealPath("/") + "/upload" + File.separator + "images";
				System.out.println(filePath);
                list.add(imgs);
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();
                	//转存文件
                saveDir = new File(filePath+File.separator+imgs);
                file.transferTo(saveDir);
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
	}

	 public static BufferedImage getImgInfo(String img, String imgPath) {

			File f = new File(imgPath+img);
			try {
				return ImageIO.read(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
}
