package com.servicesAdmin.sys.zhangHu;

import java.util.List;
import java.util.Map;

/**
 * 账户service 接口
 * 
 * 
 */
public interface ZhangHuService {
	/**
	 * 得到tBody数据
	 * @param mapStr
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public List<Map<String, Object>> tBody(Map<String,String> mapStr) throws Exception;

	/**
	 * 根据ID条件查询数据
	 * @param mapStr
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public Map<String, Object> find_Map(Map<String,String> mapStr) throws Exception;
	

	/**
	 * 得到当前帐户是否存在  存在true  不存在false
	 * @param mapStr
	 * @return boolean            
	 * @throws Exception
	 */
	public boolean isExistName(Map<String, String> mapStr) throws Exception;
	
	/**
	 * 往<帐户验证表>添加一条数据
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
	 * 账户禁用和解禁操作
	 * 
	 * @param mapStr
	 * @throws Exception
	 */
	public void updateLock(Map<String,String> mapStr) throws Exception;

	
	/**
	 * 重置账户密码
	 * 
	 * @param mapStr
	 * @throws Exception
	 */
	public void updateResetPaw(Map<String,String> mapStr) throws Exception;
	
	
	/**
	 * 查询出当前的所有可用角色和当前账户已经绑定的角色
	 * @param mapStr
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public Map<String, Object> getJiaoShePage(Map<String,String> mapStr)
			throws Exception;

	/**
	 * 将角色账户保存到 QX_JS_ZHB 中
	 * @param mapStr
	 * @throws Exception
	 */
	public void add_JiaoSheZhangHu(Map<String,String> mapStr) throws Exception;
	
	/**
	 * 修改密码之后验证旧密码是否正确
	 * 
	 * @param mapStr
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isPassword(Map<String, String> mapStr) throws Exception;

	/**
	 * 修改密码
	 * @param mapStr
	 * @return boolean
	 * @throws Exception
	 */
	public boolean update_Password(Map<String, String> mapStr) throws Exception;
	
	public void delete(Map<String, String> mapStr) throws Exception;
}
