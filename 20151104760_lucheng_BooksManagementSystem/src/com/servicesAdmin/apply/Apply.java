package com.servicesAdmin.apply;
import java.util.List;
import java.util.Map;
/**
 *  service 接口
 */ 
public interface Apply {

/************************方法开始******************************/

public List<Map<String, Object>> tBody(Map<String, String> mapStr)throws Exception;

public Map<String, Object> find_Map(Map<String, String> mapStr)throws Exception;

public Map<String, Object> add_Data(Map<String, String> mapStr) throws Exception;

public Map<String, Object> update_Data(Map<String, String> mapStr) throws Exception;

public void delete_Data(Map<String, String> mapStr) throws Exception;

public Map<String, Object> update_xuqi(Map<String, String> mapStr) throws Exception;

 }

/************************方法完毕******************************/