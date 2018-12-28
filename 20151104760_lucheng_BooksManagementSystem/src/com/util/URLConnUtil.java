package com.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 发送post 或get 操作类 
 *
 */
public class URLConnUtil {
	private static Logger logger = Logger.getLogger(URLConnUtil.class);
	/**
	 * 向指定URL发送请求
	 * 
	 * @param url  发送请求的URL
	 * @param sign  0表示get 1表示post
	 * @param data  参数 没有可以为null
	 * @return String 所代表远程资源的响应结果
	 */
	public static String sendUrl(Map<String,String> mapStr) {
		logger.info("发送请求"+mapStr.get("url"));
		StringBuffer sb = new StringBuffer();
		BufferedReader in = null;
		try {
			URL realUrl = new URL(mapStr.get("url"));
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			if("1".equals(mapStr.get("sign"))){//判断是否发送post请求
				 // 发送POST请求必须设置如下两行
				connection.setDoOutput(true);
				connection.setDoInput(true);
			}
			if(UtilValiDate.isEmpty(mapStr.get("data"))){ //发送参数
				OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(),"utf-8");
	            writer.write(mapStr.get("data"));
	            writer.flush();
			}
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection
					.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			logger.error("发送GET请求出现异常！" + e.getMessage());
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				logger.error("发送GET请求关闭in失败！" + e2.getMessage());
			}
		}
		return sb.toString();
	}
}
