package com.custom.tag;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.util.UtilStr;
import com.util.UtilValiDate;

/**
 * 自定义标签
 * 
 * 
 */
public class MyTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8816845975296760519L;

	private Map<String, Object> map; //将map转换成百分比
	private List<Map<String, Object>> listMap; //将list转换成百分比
	private String toZero ;
	private Double divisor,dividend; //除数  、被除数
	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<Map<String, Object>> getListMap() {
		return listMap;
	}

	public void setListMap(List<Map<String, Object>> listMap) {
		this.listMap = listMap;
	}

	public Double getDivisor() {
		return divisor;
	}

	public void setDivisor(Double divisor) {
		if(!UtilValiDate.isEmpty(divisor)){
			divisor = 0.0 ;
		}
		this.divisor = divisor;
	}

	public Double getDividend() {
		return dividend;
	}

	

	public String getToZero() {
		return toZero;
	}

	public void setToZero(String toZero) {
		this.toZero = toZero;
	}

	public void setDividend(Double dividend) {
		if(!UtilValiDate.isEmpty(dividend) || dividend==0){
			dividend = 1.0 ;
		}
		this.dividend = dividend;
	}

	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			// map数据不为空
			if (UtilValiDate.isEmpty(this.getMap())) {
				this.mapToJsonStr(sb);
			} else if (UtilValiDate.isEmpty(this.getListMap())) {
				this.listToStr(sb);
			}else if (UtilValiDate.isEmpty(this.getDivisor())) {
				sb.append(UtilStr.getPerDouble(this.getDivisor(), this.getDividend()));
			}else if (!UtilValiDate.isEmpty(this.getToZero())) {
				sb.append(this.emptyToZerothis(this.getToZero()));
			}else if (UtilValiDate.isEmpty(this.getToZero())) {
				sb.append(this.getToZero());
			}
			out.print(sb);
		} catch (Exception e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

	/**
	 * 将map对象转换成json格式字符串
	 * @param sb
	 * @throws Exception
	 */
	private void mapToJsonStr(StringBuffer sb) throws Exception{
		sb.append("{");
		Set<Entry<String, Object>> set = map.entrySet();
		for (Entry<String, Object> entry : set) {
			sb.append("'");
			sb.append(entry.getKey().toUpperCase());
			sb.append("'");
			sb.append(":");
			sb.append("'");
			if(UtilStr.getStr(entry.getValue()).indexOf("<")!=-1){
				sb.append("");
			}else{
				sb.append((entry.getValue() + "").replaceAll("\n", ""));
			}
			
			
			sb.append("'");
			sb.append(",");
		}
		if (sb.length() > 1) {
			sb.delete(sb.length() - 1, sb.length());
		}
		sb.append("}");
	}

	/**
	 * 将list<map> 转换成字符串
	 * 
	 * @param sb
	 */
	private void listToStr(StringBuffer sb) {
		for (Map<String, Object> map : this.getListMap()) {
			Set<Entry<String, Object>> set = map.entrySet();
			for (Entry<String, Object> entry : set) {
				sb.append((entry.getValue() + ","));
			}
		}
		if (sb.length() > 1) {
			sb.delete(sb.length() - 1, sb.length());
		}
	}
	
	/**
	 * 将空字符转变为0
	 * @param str
	 * @return String
	 */
	private String emptyToZerothis(String str) {
		
		if (UtilValiDate.isEmpty(str)) {
			return str;
		}else{
			return "0";
		}
	}
	
	
	

	public int doEndTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public void doInitBody() throws JspTagException {
	}

	public void setBodyContent(BodyContent bodyContent) {
		this.bodyContent = bodyContent;
	}
}
