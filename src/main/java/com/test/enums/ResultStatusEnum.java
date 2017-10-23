package com.test.enums;

/**
 * 
 * @ClassName: ResultStatusEnum 
 * @Description: 结果枚举
 * @author: 张天德
 * @date: 2017年3月30日 下午2:13:43
 */
public enum ResultStatusEnum {
	SUCCESS("操作成功",200,"操作成功!"),
	FAIL("操作失败",300,"操作失败!"),
	NODATA("未找到信息",400,"未找到信息!"),
	PARAMERROR("参数错误",401,"参数错误!"),
	ModifyERROR("修改数据错误",402,"要修改的数据不存在!"),
	SAVEERROR("保存数据错误",403,"保存数据不成功!"),
	ERROR("服务器内部错误",500,"服务器内部错误!"),
	CODEERROR("程序错误",501,"程序流程异常!"),
	DATASAVED("数据已经存在",300,"数据已经存在"),
	LOGINFAILE("请登录",600,"请登录"),
	;
	
	
	 // 成员变量
    private String name;
    private Integer code;
    private String desc;
    
    private ResultStatusEnum(){
    	
    }
    
 // 构造方法
    private ResultStatusEnum(String name, Integer code,String desc) {
        this.name = name;
        this.code = code;
        this.desc = desc;
    }
    
 // 普通方法
    public static String getName(Integer code) {
        for (ResultStatusEnum c : ResultStatusEnum.values()) {
            if (code.equals(c.getCode())) {
                return c.getName();
            }
        }
        return null;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
