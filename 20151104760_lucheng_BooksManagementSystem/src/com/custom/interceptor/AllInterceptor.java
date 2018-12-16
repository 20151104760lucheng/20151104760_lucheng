package com.custom.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.util.UtilValiDate;
/**
 * 自定义过滤器 
 *
 */
public class AllInterceptor extends HandlerInterceptorAdapter {
	// 定义日志方法
	protected Logger logger = Logger.getLogger(AllInterceptor.class);
	
	/**
	 * 重写父类方法  配置过滤器 
	 */
	public boolean preHandle(HttpServletRequest req,HttpServletResponse resp, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		AccessRequired annotation = method.getAnnotation(AccessRequired.class);
		if (!UtilValiDate.isEmpty(annotation)) { //判断当前方法是否使用拦截器
			logger.info("进入spring mvc拦截器......");
			if (!UtilValiDate.isEmpty(req.getSession().getAttribute("zhangHu"))) {
				req.getRequestDispatcher("/form_util/page/sessionoOut.jsp").forward(req, resp);
				return false;
			}
		}
		// 没有注解通过拦截
		return true;
	}
}