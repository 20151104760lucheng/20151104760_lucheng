package com.controllerAdmin.sys.jiaoShe;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.controllerAdmin.ControllerUtil;
import com.servicesAdmin.sys.jiaoShe.JiaoSheService;
import com.util.SysFinal;
import com.util.UtilJson;
import com.util.UtilValiDate;
/**
 * 角色action
 *
 */
@Controller
public class JiaoSheController extends ControllerUtil {
	@Resource
	private JiaoSheService jiaoSheServiceImp;

	/**
	 * 系统进入主页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/jiaoShe_index.do")
	public String index(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			return this.tBodyUtil(jiaoSheServiceImp.tBody(mapStr),mapStr,"/form/sys/jiaoShe/index.jsp", req, resp);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
		return this.returnError();
	}

	/**
	 * 添加录入数据页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/jiaoShe_addPage.do")
	public String addPage(HttpServletRequest req,HttpServletResponse resp) {
		return this.returnPage("/form/sys/jiaoShe/addPage.jsp", req, resp);
	}

	/**
	 * 添加和修改
	 * 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/jiaoShe_updateData.do")
	public String updateData(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			logger.info("角色更新操作"+mapStr);
			if (UtilValiDate.isEmpty(mapStr.get("js_id"))) {
				if ("y".equals(mapStr.get(SysFinal.ISUPDATE_KEY)))  {
					return this.updateDataUpdate(jiaoSheServiceImp.update_Data(mapStr),"/form/sys/jiaoShe/tr.jsp", req, resp);
				}
			} else {
				jiaoSheServiceImp.add_Data(mapStr);
			}
			return this.updateDataAdd(mapStr,"/form/sys/jiaoShe/tr.jsp", req, resp);
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
	@RequestMapping(value = "/jiaoShe_findById.do")
	public String findById(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			return this.findByIdUtil(jiaoSheServiceImp.find_Map(mapStr),"/form/sys/jiaoShe/findById.jsp", req, resp);
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
	@RequestMapping(value = "/jiaoShe_editPage.do")
	public String editPage(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			return this.editPageUtil(jiaoSheServiceImp.find_Map(mapStr),"/form/sys/jiaoShe/editPage.jsp", req, resp);
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
	@RequestMapping(value = "/jiaoShe_deleteData.do")
	public void deleteData(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			jiaoSheServiceImp.delete_Data(mapStr);
			this.respBack(UtilJson.mapToJson(mapStr), resp);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
	}
	
	
	/**
	 * 角色绑定功能
	 * @return String
	 */
	@RequestMapping(value = "/jiaoShe_jiaoSheTiedGongNengPage.do")
	public String jiaoSheTiedGongNengPage(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			Map<String, Object>  mapList = jiaoSheServiceImp.getGongNengPage(mapStr);
			req.setAttribute("map", mapStr);
			req.setAttribute("gongNeng", mapList.get("GongNeng"));
			req.setAttribute("jiaoSheGoengNeng", mapList.get("JiaoSheGoengNeng"));
			return "/form/sys/jiaoShe/jiaoSheTiedGongNengPage.jsp";
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
		return this.returnError();
	}
	
	/**
	 * 将功能角色保存到 qx_js_gnsb 中
	 */
	@RequestMapping(value = "/jiaoShe_selectGongNeng.do")
	public void selectGongNeng(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			jiaoSheServiceImp.add_GongNengJiaoShe(mapStr);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
	}
}
