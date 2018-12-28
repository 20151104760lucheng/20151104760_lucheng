package com.controllerAdmin.sys.gongNeng;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.controllerAdmin.ControllerUtil;
import com.servicesAdmin.sys.gongNeng.GongNengService;
import com.servicesAdmin.sys.gongNeng.feiLei.GongNengFeiLeiService;
import com.util.SysFinal;
import com.util.UtilJson;
import com.util.UtilValiDate;

/**
 * 功能树action
 * 
 * 
 */
@Controller
public class GongNengController extends ControllerUtil{
	@Resource
	private GongNengService gongNengServiceImp;
	@Resource
	private GongNengFeiLeiService gongNengFeiLeiServiceImp;
	
	/**
	 * 系统进入主页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/gongNeng_index.do")
	public String index(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			req.setAttribute("map", mapStr);
			req.setAttribute("listMap", gongNengServiceImp.tBody(mapStr));
			return this.returnPage("/form/sys/gongNeng/gongNeng/index.jsp", req, resp);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
		return this.returnError();
	}

	/**
	 * 添加录入数据页面
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/gongNeng_addPage.do")
	public String addPage(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			req.setAttribute("mapFL", mapStr);
			return this.returnPage("/form/sys/gongNeng/gongNeng/addPage.jsp", req, resp);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
		return this.returnError();
	}

	/**
	 * 添加和修改
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/gongNeng_updateData.do")
	public String updateData(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			logger.info("功能更新操作"+mapStr);
			if (UtilValiDate.isEmpty(mapStr.get("gn_id"))) {
				if ("y".equals(mapStr.get(SysFinal.ISUPDATE_KEY))){
					return this.updateDataUpdate(gongNengServiceImp.update_Data(mapStr),"/form/sys/gongNeng/gongNeng/tr.jsp", req, resp);
				}
			} else {
				gongNengServiceImp.add_Data(mapStr);
			}
			return this.updateDataAdd(mapStr,"/form/sys/gongNeng/gongNeng/tr.jsp", req, resp);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
		return this.returnError();
	}

	/**
	 * 查询一条数据
	 * 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/gongNeng_findById.do")
	public String findById(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			return this.findByIdUtil(gongNengServiceImp.find_Map(mapStr),"/form/sys/gongNeng/gongNeng/findById.jsp", req, resp);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
		return this.returnError();
	}
	
	/**
	 * 跳转到编辑页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/gongNeng_editPage.do")
	public String editPage(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			return this.editPageUtil(gongNengServiceImp.find_Map(mapStr),"/form/sys/gongNeng/gongNeng/editPage.jsp", req, resp);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
		return this.returnError();
	}

	/**
	 * 选择功能分类 页面
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/gongNeng_selectGongNengFenLeiPage.do")
	public String selectGongNengFenLeiPage(HttpServletRequest req,HttpServletResponse resp) {
		try {
			return "gongNengFenLei_index.do";
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
		return this.returnError();
	}

	/**
	 * 选择功能分类
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/gongNeng_selectGongNengFenLei.do")
	public String selectGongNengFenLei(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = new HashMap<String, String>();
			this.pageReq(gongNengFeiLeiServiceImp.listMap(mapStr),mapStr, req);
			return "gongNengFenLei_tbody.do";
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
		return this.returnError();
	}


	/**
	 * 删除一条数据
	 * 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/gongNeng_deleteData.do")
	public void deleteData(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			gongNengServiceImp.delete_Data(mapStr);
			this.respBack(UtilJson.mapToJson(mapStr), resp);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
	}
}
