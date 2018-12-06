package com.servicesAdmin.login;

import java.util.Map;

/**
 * 登录
 *
 */
public interface LoginServer {
	
	/**
	 * 用户登录
	 * @param mapStr
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public Map<String, Object> login(Map<String,String> mapStr) throws Exception;
	
}
