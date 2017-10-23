/**   
 * Copyright © 2017 上海华禽网络科技有限公司. All rights reserved.
 * 
 * @Title: CookieUtil.java 
 * @Prject: mht-common
 * @Package: com.jingsha.util 
 * @Description: TODO
 * @author: 张天德 
 * @date: 2017年4月4日 上午10:48:00 
 * @version: V1.0   
 */
package com.test.until;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: CookieUtil
 * @Description: java操作cookie类工具
 * @author: 张天德
 * @date: 2017年4月4日 上午10:48:00
 */
public class CookieUtil {

	/**
	 * 设置cookie
	 * 
	 * @param response
	 * @param name
	 *            cookie名字
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            cookie生命周期 以秒为单位
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge, String domain) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		cookie.setDomain(domain);
		response.addCookie(cookie);
	}
	
	/** 
	 * 根据名字获取cookie 
	 * @param request 
	 * @param name cookie名字 
	 * @return 
	 */  
	public static Cookie getCookieByName(HttpServletRequest request,String name){  
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);  
	    if(cookieMap.containsKey(name)){  
	        Cookie cookie = (Cookie)cookieMap.get(name);  
	        return cookie;  
	    }else{  
	        return null;  
	    }     
	} 
	
	/** 
	 * 将cookie封装到Map里面 
	 * @param request 
	 * @return 
	 */  
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){    
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();  
	    Cookie[] cookies = request.getCookies();  
	    if(null!=cookies){  
	        for(Cookie cookie : cookies){  
	            cookieMap.put(cookie.getName(), cookie);  
	        }  
	    }  
	    return cookieMap;  
	} 
	
	/**
	 * 
	 * @Title: delCookieByName 
	 * @Description: 删除cookie
	 * @return: void
	 */
	public static void delCookieByName(HttpServletResponse response,String name){
		addCookie(response,name,null,0,null);
	}
	   
}
