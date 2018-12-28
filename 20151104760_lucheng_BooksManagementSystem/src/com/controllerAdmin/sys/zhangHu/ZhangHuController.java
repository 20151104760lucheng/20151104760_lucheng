package com.controllerAdmin.sys.zhangHu;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.controllerAdmin.ControllerUtil;
import com.custom.interceptor.AccessRequired;
import com.servicesAdmin.sys.zhangHu.ZhangHuService;
import com.util.SysFinal;
import com.util.UtilJson;
import com.util.UtilValiDate;


/**
 * 账户管理
 *
 */
@Controller
public class ZhangHuController extends ControllerUtil{
	@Resource
	private ZhangHuService zhangHuServiceImp;
	

	/**
	 * 系统进入主页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/zhangHu_index.do")
	public String index(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			return this.tBodyUtil(zhangHuServiceImp.tBody(mapStr),mapStr,"form/sys/zhangHu/index.jsp",req,resp);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
		return this.returnError();
	}

	/**
	 * 查询账户名是否已经注册
	 */
	@RequestMapping(value = "/zhangHu_isExistName.do")
	@AccessRequired //添加过滤器
	public void isExistName(HttpServletRequest req,HttpServletResponse resp){
		try {
			String zh_zhm = req.getParameter("zh_zhm");
			Map<String,String> mapStr = new HashMap<String, String>();
			mapStr.put("zh_zhm", zh_zhm);
			this.respBack(!zhangHuServiceImp.isExistName(mapStr), resp);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
	}

	/**
	 * 添加录入数据页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	@AccessRequired
	@RequestMapping(value = "/zhangHu_addPage.do")
	public String addPage(HttpServletRequest req,HttpServletResponse resp){
		return this.returnPage("/form/sys/zhangHu/addPage.jsp", req, resp);
	}
	/**
	 * 这里只做帐户修改(不做帐户添加，所以这里不用判断ID)
	 * 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/zhangHu_updateData.do")
	@AccessRequired //添加过滤器
	public String updateData(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			logger.info("账户更新操作"+mapStr);
			if (UtilValiDate.isEmpty(mapStr.get("zh_id"))) {
				if ("y".equals(mapStr.get(SysFinal.ISUPDATE_KEY)))  {
					mapStr.put("zt", req.getParameter("zt"));
					return this.updateDataUpdate(zhangHuServiceImp.update_Data(mapStr),"/form/sys/zhangHu/tr.jsp", req, resp);
				}
			} else {
				mapStr.put("zt", "2");
				zhangHuServiceImp.add_Data(mapStr);
			}
			return this.updateDataAdd(mapStr,"/form/sys/zhangHu/tr.jsp", req, resp);
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
	@RequestMapping(value = "/zhangHu_findById.do")
	public String findById(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			return this.findByIdUtil(zhangHuServiceImp.find_Map(mapStr),"/form/sys/zhangHu/findById.jsp", req, resp);
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
	@RequestMapping(value = "/zhangHu_editPage.do")
	public String editPage(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			return this.editPageUtil(zhangHuServiceImp.find_Map(mapStr),"/form/sys/zhangHu/editPage.jsp", req, resp);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
		return this.returnError();
	}
	
	/**
	 * 账户禁用和解禁操作
	 */
	@RequestMapping(value = "/zhangHu_updateLock.do")
	public void updateLock(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			zhangHuServiceImp.updateLock(mapStr);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
	}

	
	/**
	 * 重置账户密码
	 */
	@RequestMapping(value = "/zhangHu_updateResetPaw.do")
	public void updateResetPaw(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = new HashMap<String, String>();
			mapStr.put("zh_id", req.getParameter("zh_id"));
			zhangHuServiceImp.updateResetPaw(mapStr);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
	}
	
	
	
	/**
	 * 账户绑定角色
	 * @return String
	 */
	@RequestMapping(value = "/zhangHu_zhangHuTiedjiaoShePage.do")
	public String zhangHuTiedjiaoShePage(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			Map<String, Object>  mapList = zhangHuServiceImp.getJiaoShePage(mapStr);
			req.setAttribute("map", mapStr);
			req.setAttribute("jiaoShe", mapList.get("JiaoShe"));
			req.setAttribute("zhangHuJiaoShe", mapList.get("ZhangHuJiaoShe"));
			return "/form/sys/zhangHu/zhangHuTiedjiaoShePage.jsp";
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
		return this.returnError();
	}
	
	/**
	 * 将账户角色保存到 QX_JS_ZHB 中
	 * @return String
	 */
	@RequestMapping(value = "/zhangHu_selectJiaoShe.do")
	public void selectJiaoShe(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			zhangHuServiceImp.add_JiaoSheZhangHu(mapStr);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
	}
	
	/**
	 * 打开修改密码页面
	 * @return String
	 */
	@RequestMapping(value = "/zhangHu_openUpdatePasswordPage.do")
	public String openUpdatePasswordPage(HttpServletRequest req,HttpServletResponse resp){
		return this.returnPage("/form/sys/zhangHu/openUpdatePasswordPage.jsp", req, resp);
	}
	
	/**
	 *修改密码之前验证旧密码是否正确
	 */
	@RequestMapping(value = "/zhangHu_isPassword.do")
	public void isPassword(HttpServletRequest req,HttpServletResponse resp){
		try {
			String zh_id = req.getParameter("zh_id");
			String zh_zhmm = req.getParameter("zh_zhmm");
			Map<String,String> mapStr = new HashMap<String, String>();
			mapStr.put("zh_id", zh_id);
			mapStr.put("zh_zhmm", zh_zhmm);
			super.respBack(zhangHuServiceImp.isPassword(mapStr), resp);
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
	}

	/**
	 * 修改密码
	 */
	@RequestMapping(value = "/zhangHu_updatePassword.do")
	public void updatePassword(HttpServletRequest req,HttpServletResponse resp){
		try {
			Map<String,String> mapStr = UtilJson.toMap(req.getParameter("mapStr"));
			if (zhangHuServiceImp.update_Password(mapStr)) {
				logger.info("修改密码完成");
			}
		} catch (Exception e) {
			this.errorResp(e, resp);
		}
	}
}
