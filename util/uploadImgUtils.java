package com.eb.dianlianbao_server.util;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
public class uploadImgUtils {
	/**
	 * 多图上传
	 *
	 * @param imgs
	 * @param req
	 * @param rep
	 * @throws Throwable
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadImgs", method = RequestMethod.POST)
	public void uploadMoreImg(@RequestParam(value = "imgs") MultipartFile[] imgs, HttpServletRequest req,
			HttpServletResponse rep) throws Throwable {
		rep.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		PrintWriter writer = rep.getWriter();
		JSONObject object = new JSONObject();
		JSONObject objectData = new JSONObject();
		String times = "";
		String time = "";
		String  realPath = req.getServletContext().getRealPath("/") + "upload/images/";
		try {
			for (int i = 0; i < imgs.length; i++) {
				time = String.valueOf(new Date().getTime())+i + ".png";
				times = times + time + ",";
				File f = new File(realPath + time);
				FileUtils.copyInputStreamToFile(imgs[i].getInputStream(), f);
			}
			object.put("code", 200);
			object.put("message", "上传成功");
			objectData.put("name", times.substring(0, times.length() - 1));
			object.put("data", objectData);
		} catch (Exception e) {
			e.printStackTrace();
			// long date = new Date().getTime() / 1000;
			object.put("code", 201);
			object.put("message", "上传失败，服务器路径：" + realPath);
			object.put("data", objectData);
		}

		writer.println(object.toString());
		writer.flush();
		writer.close();
	}


	public static List<String> saveFile(HttpServletRequest req, MultipartFile file, List<String> list) {
		// TODO Auto-generated method stub
		int x;//定义两变量
		Random ne=new Random();//实例化一个random的对象ne
		x=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
				String imgs = String.valueOf(new Date().getTime() + x) + ".png";
                String filePath =req.getServletContext().getRealPath("/")+ "upload"+File.separator+"images"+File.separator+imgs;
                String url = filePath.substring(filePath.lastIndexOf(File.separator)+1) ;
                System.out.println("上传成功："+url);
                list.add(url);
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();
                	//转存文件
                file.transferTo(saveDir);
                return list;
            } catch (Exception e) {
				LogUtils.logError("上传失败!", e);
				e.printStackTrace();
            }
        }
		return null;
	}
}
