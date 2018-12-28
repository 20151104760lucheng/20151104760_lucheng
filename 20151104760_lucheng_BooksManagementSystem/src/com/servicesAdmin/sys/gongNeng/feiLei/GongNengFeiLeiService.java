package com.servicesAdmin.sys.gongNeng.feiLei;

import java.util.List;
import java.util.Map;

/**
 * 功能分类service 接口
 * 
 * 
 */
public interface GongNengFeiLeiService{

	/**
	 * 得到tBody数据
	 * 
	 * @param mapStr
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> tBody(Map<String,String> mapStr) throws Exception;
	
	
	/**
	 * 根据条件得到所有的功能分类
	 * 
	 * @param mapStr
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> listMap(Map<String,String> mapStr) throws Exception;

	/**
	 * 根据ID条件查询数据
	 * 
	 * @param mapStr
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public Map<String, Object> find_Map(Map<String,String> mapStr) throws Exception;

	/**
	 * 添加数据
	 * 
	 * @param mapStr
	 * @throws Exception
	 */
	public void add_Data(Map<String,String> mapStr) throws Exception;

	/**
	 * 根据条件更新数据
	 * @param mapStr
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	public Map<String, Object> update_Data(Map<String,String> mapStr) throws Exception;

	/**
	 * 根据条件删除数据
	 * 
	 * @param mapStr
	 * @throws Exception
	 */
	public void delete_Data(Map<String,String> mapStr) throws Exception;

}
