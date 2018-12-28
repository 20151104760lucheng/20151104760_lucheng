package com.custom.global;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 配置全局info action  主要包含一些对话框
 *
 */
@Controller
public class GlobalInfoAction {
	// 定义HttpServletRequest
	protected HttpServletRequest req = null;

	/**
	 * 提示确认对话框
	 * @return String 
	 */
	@RequestMapping(value = "/globalInfo_confirmBox.do")
	public String confirmBox(HttpServletRequest req,HttpServletResponse resp){
		req.setAttribute("confirmInfo", req.getParameter("info"));
		return "form_util/page/infoPage.jsp";
	}
}
