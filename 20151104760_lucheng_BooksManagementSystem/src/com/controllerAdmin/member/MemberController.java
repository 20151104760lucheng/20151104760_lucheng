package com.controllerAdmin.member;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.controllerAdmin.ControllerUtil;
import com.custom.interceptor.AccessRequired;
import com.servicesAdmin.member.Member;
import com.servicesAdmin.sys.zhangHu.ZhangHuService;
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
	@Resource
	private ZhangHuService zhangHuServiceImp;

	/************************方法开始******************************/

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

	@AccessRequired
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
	@AccessRequired
	public String updateData(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			if (UtilValiDate.isEmpty(mapStr.get("id"))) {
				if ("y".equals(mapStr.get(SysFinal.ISUPDATE_KEY)))  {	
					Map<String, Object> mapObj = memberImp.update_Data(mapStr);
					return this.updateDataUpdate(mapObj,"form/member/member_tr.jsp",req,resp);
				}	
			} else { 
				Map<String, Object> mapObj = memberImp.add_Data(mapStr);
				mapStr.put("zh_zhm", (String) mapObj.get("username"));
				mapStr.put("zh_zhmm", (mapObj.get("password").toString()));
				//绑定账户为系统账户，角色为读者
				zhangHuServiceImp.add_Data(mapStr);
				Map<String, Object> account = zhangHuServiceImp.find_Map(mapStr);
				mapStr.put("jsids", "20190108105617065");
				mapStr.put("zh_id", account.get("zh_id").toString());
				zhangHuServiceImp.add_JiaoSheZhangHu(mapStr);
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
			Map<String, Object> account  = memberImp.find_Map(mapStr);
			mapStr.put("zh_zhm", (String) account.get("username"));
			account = zhangHuServiceImp.find_Map(mapStr);
			mapStr.put("zh_id", (String) account.get("zh_id"));
			zhangHuServiceImp.delete(mapStr);
			memberImp.delete_Data(mapStr);
			this.respBack(UtilJson.mapToJson(mapStr),resp);
		} catch (Exception e) {
			this.errorResp(e,resp);
		}
		
	}

	/************************方法完毕******************************/
}

