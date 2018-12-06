package com.controllerAdmin.login;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.controllerAdmin.ControllerUtil;
import com.custom.interceptor.AccessRequired;
import com.servicesAdmin.login.LoginServer;
import com.servicesAdmin.sys.zhangHu.ZhangHuService;
import com.util.UtilJson;
import com.util.UtilValiDate;

@Controller
public class LoginController extends ControllerUtil {
	@Resource
	private LoginServer loginServerImp;
	@Resource
	private ZhangHuService zhangHuServiceImp;
	/**
	 * 登录验证
	 * @return String
	 */
	@RequestMapping(value = "/login_valiDate.do")
	@AccessRequired //添加过滤器
	public String valiDate(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			logger.info("用户登录"+mapStr);
			if(!UtilValiDate.isEmpty(mapStr.get("zh_zhm")) || !UtilValiDate.isEmpty(mapStr.get("zh_zhmm"))){
				logger.info("登录结果：用户名或密码为空!");
				req.setAttribute("infoError", "用户名或密码为空!");
				return "form/login/login.jsp";
			}
			Map<String, Object> mapLogin = loginServerImp.login(mapStr);
			if (mapLogin.size() != 0) {
				logger.info("登录结果：成功登录!");
				req.getSession().setAttribute("zhangHu",mapLogin.get("mapLogin"));
				req.setAttribute("menuData", mapLogin.get("gongNengJson"));
				return "form/main/main.jsp" ;
			} else {
				logger.info("登录结果：当前帐号不存在或被禁用!");
				req.setAttribute("infoError", "当前帐号不存在或被禁用!");
				return "form/login/login.jsp";
			}
		} catch (Exception e) {
			this.errorResp(e,resp);
		}
		return this.returnError();
	}
	
	/**
	 * 登录验证
	 * @return String
	 */
	@RequestMapping(value = "/register_valiDate.do")
	@AccessRequired //添加过滤器
	public String register(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			logger.info("用户注册"+mapStr);
			if(!UtilValiDate.isEmpty(mapStr.get("zh_zhm")) || !UtilValiDate.isEmpty(mapStr.get("zh_zhmm"))){
				logger.info("注册结果：用户名或密码为空!");
				req.setAttribute("infoError", "用户名或密码为空!");
				return "form/login/login.jsp";
			}
			if(!zhangHuServiceImp.isExistName(mapStr)){
					logger.info("注册结果：注册成功!");
					zhangHuServiceImp.add_Data(mapStr);
					req.setAttribute("infoError", "注册成功!");
					return "form/login/login.jsp" ;
			}else {
				logger.info("注册结果：当前帐号已被使用!");
				req.setAttribute("infoError", "当前帐号已被使用!");
				return "form/login/login.jsp";
			}
		} catch (Exception e) {
			this.errorResp(e,resp);
		}
		return this.returnError();
	}

	/**
	 * 跳转到login页面
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/login_result.do")
	@AccessRequired //添加过滤器
	public String result(HttpServletRequest req,HttpServletResponse resp) {
		req.getSession().invalidate();
		return "form/login/login.jsp";
	}
	

}
