package com.test.page;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 * <P>
 * File name : PageData.java
 * </P>
 * <P>
 * Author : zhangyq
 * </P>
 * <P>
 * Date : 2015年7月3日下午5:34:03
 * </P>
 * <P>
 * Desc : 收集请求数据
 * </P>
 * <P>
 * Update History :
 * <ul>
 * <li></li>
 * <li></li>
 * </ul>
 * </P>
 */
@Alias(value="pd")
public class PageData extends HashMap implements Map {


	private static final long serialVersionUID = 1L;

	Map map = null;
	HttpServletRequest request;

	public PageData(HttpServletRequest request) {
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				StringBuffer valueSb = new StringBuffer();
				for (int i = 0; i < values.length; i++) {
					valueSb.append(values[i]).append(",");
				}
				value = valueSb.substring(0, valueSb.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		map = returnMap;
	}

	public PageData() {
		map = new HashMap();
	}

	/**
	 * 
	 * 构造函数
	 * 
	 * @param map
	 */
	public PageData(Map map) {
		this.map = map;
	}

	@Override
	public Object get(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}

	public String getString(Object key) {
		Object o=get(key);
		if(null==o){
			return "";
		}
		return String.valueOf(  get(key)  );
	}

	public Integer getInteger(Object key) {
		Integer result = -1;
		try {
			result = Integer.parseInt(getString(key));
		} catch (Exception e) {
			// catch exception used default value 
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	
	public PageData putPD(Object key, Object value) {
		PageData p  =new PageData();
		p.put( key,  value);
		return p;
	}
	
	


	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean containsKey(Object key) {

		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {

		return map.containsValue(value);
	}

	@Override
	public Set entrySet() {

		return map.entrySet();
	}

	@Override
	public boolean isEmpty() {

		return map.isEmpty();
	}

	@Override
	public Set keySet() {

		return map.keySet();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void putAll(Map t) {

		map.putAll(t);
	}

	@Override
	public int size() {

		return map.size();
	}

	@Override
	public Collection values() {

		return map.values();
	}

	public String toJsonString() {
		return JSONObject.toJSONString(this,SerializerFeature.WriteMapNullValue);
	}


}
