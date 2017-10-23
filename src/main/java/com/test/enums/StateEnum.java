package com.test.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: StateEnum 
 * @Description: 状态枚举
 * @author: 张天德
 * @date: 2017年3月30日 下午2:13:30
 */
public enum StateEnum {
	NODEL("未删除","0"),
	DEL("已删除","1"),
	TEMP("禁用","2")
	;
	
	
	 // 成员变量
    private String name;
    private String code;
    
    private StateEnum(){
    	
    }
    
 // 构造方法
    private StateEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }
    
 // 普通方法
    public static String getName(String code) {
        for (StateEnum c : StateEnum.values()) {
            if (code.equals(c.getCode())) {
                return c.getName();
            }
        }
        return null;
    }
    
    //枚舉轉換爲list方法
    public static List<Map<String,Object>> enumToList(){
    	List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    	for(StateEnum ste:StateEnum.values()){
    		Map<String,Object> m=new HashMap<String,Object>();
    		m.put("name", ste.getName());
    		m.put("code", ste.getCode());
    		list.add(m);
    	}
    	return list;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
