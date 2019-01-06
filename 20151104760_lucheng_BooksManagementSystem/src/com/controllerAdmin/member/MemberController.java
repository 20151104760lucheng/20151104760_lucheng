package com.controllerAdmin.member;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.controllerAdmin.ControllerUtil;
import com.servicesAdmin.member.Member;
import com.util.SysFinal;
import com.util.UtilJson;
import com.util.UtilValiDate;
/**
 *  controller 实现类 
 */ 
@Controller
public class MemberController  extends ControllerUtil {

	@Resource
	private Member  memberImp;

	/************************系统生成方法开始******************************/

	@RequestMapping(value ="/member_index.do")
	public String index(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			return this.tBodyUtil(memberImp.tBody(mapStr),mapStr,"form/member/member_index.jsp",req,resp);
		} catch (Exception e) {
			this.errorResp(e,resp);
		}
		return this.returnError();
	}

	@RequestMapping(value ="/member_addPage.do")
	public String addPage(HttpServletRequest req,HttpServletResponse resp) {
		try {
			return this.returnPage("form/member/member_addPage.jsp",req,resp);
		} catch (Exception e) {
			this.errorResp(e,resp);
		}
		return this.returnError();
	}

	@RequestMapping(value ="/member_updateData.do")
	public String updateData(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			if (UtilValiDate.isEmpty(mapStr.get("id"))) {
				Map<String, Object> mapObj = memberImp.update_Data(mapStr);
				return this.updateDataUpdate(mapObj,"form/member/member_tr.jsp",req,resp);
			} else { 
				Map<String, Object> mapObj = memberImp.add_Data(mapStr);
				return this.updateDataUpdate(mapObj,"form/member/member_tr.jsp",req,resp);
			} 	
		} catch (Exception e) {
			this.errorResp(e,resp);
		}
		return this.returnError();
	}

	@RequestMapping(value ="/member_findById.do")
	public String findById(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			return this.findByIdUtil(memberImp.find_Map(mapStr),"form/member/member_findById.jsp", req, resp);
		} catch (Exception e) {
			this.errorResp(e,resp);
		}
		return this.returnError();
	}

	@RequestMapping(value ="/member_editPage.do")
	public String editPage(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			return this.editPageUtil(memberImp.find_Map(mapStr),"form/member/member_editPage.jsp",req,resp);
		} catch (Exception e) {
			this.errorResp(e,resp);
		}
		return this.returnError();
	}

	@RequestMapping(value ="/member_deleteData.do")
	public void deleteData(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			memberImp.delete_Data(mapStr);
			this.respBack(UtilJson.mapToJson(mapStr),resp);
		} catch (Exception e) {
			this.errorResp(e,resp);
		}
		
	}

	/************************系统生成方法完毕******************************/
}

