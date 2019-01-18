package com.controllerAdmin.apply;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.controllerAdmin.ControllerUtil;
import com.servicesAdmin.apply.Apply;
import com.servicesAdmin.book.Book;
import com.util.SysFinal;
import com.util.UtilJson;
import com.util.UtilValiDate;
/**
 *  controller 实现类 
 */ 
@Controller
public class ApplyController  extends ControllerUtil {

	@Resource
	private Apply  applyImp;
	@Resource
	private Book  bookImp;

	/************************方法开始******************************/

	@RequestMapping(value ="/apply_index.do")
	public String index(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			if(req.getParameter("isUser")!=null){
				Map<String, Object> account  = (Map<String, Object>) req.getSession().getAttribute("zhangHu");
				mapStr.put("user",account.get("student_id").toString() );
				return this.tBodyUtil(applyImp.tBody(mapStr),mapStr,"form/apply/apply_user_index.jsp",req,resp);
			}
			return this.tBodyUtil(applyImp.tBody(mapStr),mapStr,"form/apply/apply_index.jsp",req,resp);
		} catch (Exception e) {
			this.errorResp(e,resp);
		}
		return this.returnError();
	}

	@RequestMapping(value ="/apply_addPage.do")
	public String addPage(HttpServletRequest req,HttpServletResponse resp) {
		try {
			return this.returnPage("form/apply/apply_addPage.jsp",req,resp);
		} catch (Exception e) {
			this.errorResp(e,resp);
		}
		return this.returnError();
	}

	@RequestMapping(value ="/apply_updateData.do")
	public String updateData(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			if (UtilValiDate.isEmpty(mapStr.get("id"))) {
				Map<String, Object> mapObj = applyImp.update_Data(mapStr);
				//还书
				mapStr.put("status", "0");
				mapStr.put("id", mapStr.get("book_id"));
				bookImp.update_status(mapStr);
				return this.updateDataUpdate(mapObj,"form/apply/apply_tr.jsp",req,resp);
			} else { 
				Map<String, Object> mapObj = applyImp.add_Data(mapStr);
				//修改书籍状态
				mapStr.put("status", "1");
				mapStr.put("id", mapStr.get("book_id"));
				bookImp.update_status(mapStr);
				return this.updateDataUpdate(mapObj,"form/apply/apply_tr.jsp",req,resp);
			} 	
		} catch (Exception e) {
			this.errorResp(e,resp);
		}
		return this.returnError();
	}
	
	@RequestMapping(value ="/apply_xujie.do")
	public String xujie(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			Map<String, Object> mapObj = applyImp.update_xuqi(mapStr);
			return this.updateDataUpdate(mapObj,"form/apply/apply_tr.jsp",req,resp);
		} catch (Exception e) {
			this.errorResp(e,resp);
		}
		return this.returnError();
	}

	@RequestMapping(value ="/apply_findById.do")
	public String findById(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			return this.findByIdUtil(applyImp.find_Map(mapStr),"form/apply/apply_findById.jsp", req, resp);
		} catch (Exception e) {
			this.errorResp(e,resp);
		}
		return this.returnError();
	}

	@RequestMapping(value ="/apply_editPage.do")
	public String editPage(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			return this.editPageUtil(applyImp.find_Map(mapStr),"form/apply/apply_editPage.jsp",req,resp);
		} catch (Exception e) {
			this.errorResp(e,resp);
		}
		return this.returnError();
	}

	@RequestMapping(value ="/apply_deleteData.do")
	public void deleteData(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			applyImp.delete_Data(mapStr);
			this.respBack(UtilJson.mapToJson(mapStr),resp);
		} catch (Exception e) {
			this.errorResp(e,resp);
		}
		
	}

	/************************方法完毕******************************/
}

