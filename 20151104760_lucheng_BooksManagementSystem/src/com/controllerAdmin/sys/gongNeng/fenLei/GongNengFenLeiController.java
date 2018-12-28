package com.controllerAdmin.sys.gongNeng.fenLei;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.controllerAdmin.ControllerUtil;
import com.servicesAdmin.sys.gongNeng.feiLei.GongNengFeiLeiService;
import com.util.SysFinal;
import com.util.UtilJson;
import com.util.UtilValiDate;

/**
 * 功能树分类action
 * 
 * 
 */
@Controller
public class GongNengFenLeiController extends ControllerUtil{
	@Resource
	private GongNengFeiLeiService gongNengFeiLeiServiceImp;
	
	/**
	 * 系统进入主页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/gongNengFenLei_index.do")
	public String index(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			return this.tBodyUtil(gongNengFeiLeiServiceImp.tBody(mapStr),mapStr,"/form/sys/gongNeng/fenLei/index.jsp",req,resp);
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
	@RequestMapping(value = "/gongNengFenLei_addPage.do")
	public String addPage(HttpServletRequest req,HttpServletResponse resp) {
		return this.returnPage("/form/sys/gongNeng/fenLei/addPage.jsp", req, resp);
	}

	/**
	 * 功能分类的添加和修改
	 * 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/gongNengFenLei_updateData.do")
	public String updateData(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			logger.info("功能分类更新操作"+mapStr);
			if (UtilValiDate.isEmpty(mapStr.get("gnfl_id"))) {
				if ("y".equals(mapStr.get(SysFinal.ISUPDATE_KEY))){
					Map<String, Object> mapObj = gongNengFeiLeiServiceImp.update_Data(mapStr);
					mapObj.put("imgIsOpne", mapStr.get("imgIsOpne"));
					mapObj.put("imgSrc", mapStr.get("imgSrc"));
					mapObj.put("trIsOpen", mapStr.get("trIsOpen"));
					return this.updateDataUpdate(mapObj, "/form/sys/gongNeng/fenLei/tr.jsp", req, resp);
				}
			} else {
				gongNengFeiLeiServiceImp.add_Data(mapStr);
			}
			return this.updateDataAdd(mapStr, "/form/sys/gongNeng/fenLei/tr.jsp", req, resp);
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
	@RequestMapping(value = "/gongNengFenLei_findById.do")
	public String findById(HttpServletRequest req,HttpServletResponse resp) {
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			return this.findByIdUtil(gongNengFeiLeiServiceImp.find_Map(mapStr),"/form/sys/gongNeng/fenLei/findById.jsp", req, resp);
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
	@RequestMapping(value = "/gongNengFenLei_editPage.do")
	public String editPage(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			return this.editPageUtil(gongNengFeiLeiServiceImp.find_Map(mapStr),"/form/sys/gongNeng/fenLei/editPage.jsp", req, resp);
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
	@RequestMapping(value = "/gongNengFenLei_deleteData.do")
	public void deleteData(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			logger.info("功能分类删除操作"+mapStr);
			gongNengFeiLeiServiceImp.delete_Data(mapStr);
			this.respBack(UtilJson.mapToJson(mapStr), resp);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
	}

}
