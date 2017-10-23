package com.test.until;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.test.page.PageData;

/**
 * <P>
 * File name : StringUtil.java
 * </P>
 * <P>
 * Author : zhangyq
 * </P>
 * <P>
 * Date : 2015年7月3日下午5:39:32
 * </P>
 * <P>
 * Desc : TODO
 * </P>
 * <P>
 * Update History :张天德 添加一些通用的方法
 * <ul>
 * <li></li>
 * <li></li>
 * </ul>
 * </P>
 */
public final class StringUtil {

	/**
	 * 
	 * @Title: isEmpty
	 * @Description: 判读字符串是否为空
	 * @param s
	 * @return
	 * @return: boolean
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s) || "null".equals(s) || "\"null\"".equals(s);
	}

	/**
	 * 
	 * @Title: stringToOutputJsonResult
	 * @Description: pd 映射对象
	 * @param (pd,object)
	 * @return object
	 * @return: OutputJsonResult
	 */
	@SuppressWarnings("rawtypes")
	public static Object pdToObj(PageData pd, Object o) {
		if (pd == null || o == null) {
			return null;
		}
		o = JSONObject.parseObject(JSONObject.toJSONBytes(pd), o.getClass());
		return o;
	}
	
	/**
	 * 
	 * @Title: getMySqlDate 
	 * @Description: 获取mysql时间
	 * @param dateStr :"yyyy-MM-dd HH:mm:ss"
	 * @return
	 * @return: String
	 * @throws ParseException 
	 */
	public static String  getMySqlDate(String dateStr){

		if(StringUtil.isEmpty(dateStr)){
			return null;	
		}
		
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Calendar MyDate = Calendar.getInstance();
		 MyDate.setTime(date);
		 return df.format(MyDate.getTime()).substring(0);//这里的adddate已经转换为固定的格式。 
	}
	
	/**
	 * 
	 * @Title: getNowTime
	 * @Description: 获取mysql 当前时间
	 * @return
	 * @return: String
	 * @throws ParseException 
	 */
	public static String  getNowTime(){
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Calendar MyDate = Calendar.getInstance();
		 return df.format(MyDate.getTime()).substring(0);//这里的adddate已经转换为固定的格式。 
	}
	
	
	/**
	 * StringUtil.getSqlInStrByStrArray()<BR>
	 * <P>Author :  zhangyq </P>  
	 * <P>Date : 2016年6月15日下午6:14:05</P>
	 * <P>Desc : 数组字符串转换为SQL in 字符串拼接 </P>
	 * @param strArray	数组字符串
	 * @return  SQL in 字符串
	 */
	public static String getSqlInStrByStrArray(String str) {
		StringBuffer temp = new StringBuffer();
		if(StringUtil.isEmpty(str)){
			return "('')";
		}
		temp.append("(");
		if(!StringUtil.isEmpty(str)){
			String[] strArray=str.split(",");
			if (strArray != null && strArray.length > 0 ) {
				for (int i = 0; i < strArray.length; i++) {
					temp.append("'");
					temp.append(strArray[i]);
					temp.append("'");
					if (i !=  (strArray.length-1) ) {
						temp.append(",");
					}
				}
			}
		}
		temp.append(")");
		return temp.toString();
	}
	
	public static String getSqlInStrByStrArray(String str,String reg) {
		StringBuffer temp = new StringBuffer();
		if(StringUtil.isEmpty(str)){
			return "('')";
		}
		temp.append("(");
		if(!StringUtil.isEmpty(str)){
			String[] strArray=str.split(reg);
			if (strArray != null && strArray.length > 0 ) {
				for (int i = 0; i < strArray.length; i++) {
					temp.append("'");
					temp.append(strArray[i]);
					temp.append("'");
					if (i !=  (strArray.length-1) ) {
						temp.append(",");
					}
				}
			}
		}
		temp.append(")");
		return temp.toString();
	}

	/**
	 * StringUtil.isNumeric()<BR>
	 * <P>Author :  jdd  </P>  
	 * <P>Date : 2017年5月18日下午3:31:20</P>
	 * <P>Desc : 判断是否位数字 </P>
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		if(StringUtil.isEmpty(str)){
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
