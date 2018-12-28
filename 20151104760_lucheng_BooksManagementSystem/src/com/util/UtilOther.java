package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


/**
 * util其他公用类 
 *
 */
public class UtilOther {
	// 定义日志方法
	private static Logger logger = Logger.getLogger(UtilOther.class);
	/**
	 * 创建一个文件夹
	 * @throws Exception
	 */
	public static void createFile(String fileName){
		File file = new File(fileName);
		try {
			if(!file.exists()){
				file.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生存段地址工具类
	 * @param url
	 * @return String
	 */
	public static String getShortUrl(String url) throws Exception{
		try {
			return url;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 得到当前请求过来全文地址不带端口
	 * 如：http://ip/项目名/action?参数
	 * http://fangqikj.vicp.cc/NewsRelease/index_indexData.do?code=04182f0e610a37fa14f142c3651719eF&state=123
	 * @param req
	 * @return String
	 * @throws Exception
	 */
	public static String getRequestUrl(HttpServletRequest req) throws Exception{
		try {
			String queryString = req.getQueryString();
			String path = req.getContextPath();
			String basePath = req.getScheme()+"://"+req.getServerName()+path+req.getServletPath();
			if(UtilValiDate.isEmpty(queryString)){
				basePath +="?"+queryString;
			}
			return basePath;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/** 
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址; 
     *  
     * @param request 
     * @return String
     * @throws IOException 
     */  
    public static String getIpAddress(HttpServletRequest request) throws IOException {  
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址  
        String ip = request.getHeader("X-Forwarded-For");  
        if (logger.isInfoEnabled()) {  
            logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);  
        }  
  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("Proxy-Client-IP");  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("WL-Proxy-Client-IP");  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_CLIENT_IP");  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getRemoteAddr();  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);  
                }  
            }  
        } else if (ip.length() > 15) {  
            String[] ips = ip.split(",");  
            for (int index = 0; index < ips.length; index++) {  
                String strIp = (String) ips[index];  
                if (!("unknown".equalsIgnoreCase(strIp))) {  
                    ip = strIp;  
                    break;  
                }  
            }  
        }  
        return ip;  
    }  
	
    /**
     * 根据IP 获取对应地址
     * @param ip
     * @return Map<String, String>
     */
    public static Map<String, String> getIp_address(String ip) {
		String url_str = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
		BufferedReader in = null;
		try {
			URL url = new URL(url_str);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str = in.readLine();
			return UtilJson.toMap(str);
		} catch (Exception e) {
			 logger.info("使用第三方插件获取地址错误=" + ip);  
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new HashMap<String, String>();
	}
}
