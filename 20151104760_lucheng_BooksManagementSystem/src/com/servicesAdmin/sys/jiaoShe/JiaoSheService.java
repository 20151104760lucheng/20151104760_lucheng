package com.servicesAdmin.sys.jiaoShe;

import java.util.List;
import java.util.Map;

/**
 * 角色service 接口
 * 
 */
public interface JiaoSheService {
	/**
	 * 得到tBody数据
	 * 
	 * @param mapStr
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> tBody(Map<String, String> mapStr)
			throws Exception;

	/**
	 * 根据ID条件查询数据
	 * 
	 * @param mapStr
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public Map<String, Object> find_Map(Map<String, String> mapStr)
			throws Exception;

	/**
	 * 添加数据
	 * 
	 * @param mapStr
	 * @throws Exception
	 */
	public void add_Data(Map<String, String> mapStr) throws Exception;

	/**
	 * 根据条件更新数据
	 * @param mapStr
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	public Map<String, Object> update_Data(Map<String, String> mapStr) throws Exception;

	/**
	 * 根据条件删除数据
	 * 
	 * @param mapStr
	 * @throws Exception
	 */
	public void delete_Data(Map<String, String> mapStr) throws Exception;

	/**
	 * 查询出当前的所有可用功能和当前角色已经绑定的功能
	 * 
	 * @param mapStr
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public Map<String, Object> getGongNengPage(Map<String, String> mapStr)
			throws Exception;

	/**
	 * 将功能角色保存到 qx_js_gnsb 中
	 * 
	 * @param bean_JS
	 * @throws Exception
	 */
	public void add_GongNengJiaoShe(Map<String, String> mapStr)
			throws Exception;
}
