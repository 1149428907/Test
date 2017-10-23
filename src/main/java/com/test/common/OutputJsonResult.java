package com.test.common;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.test.enums.ResultStatusEnum;

/**
 * <P>File name : OutputJsonResult.java </P>
 * <P>Author : dgf </P> 
 * <P>Date : 2017年4月12日下午2:39:25 </P>
 * <P>Desc : 封装RESTFUL风格的数据 </P>
 * <P>Update History : <ul>
 *     <li></li>
 *     <li></li> 
 *     </ul>
 * </P>
 */
public class OutputJsonResult<T> implements Serializable {

	/**
	 * 字段或域定义：<code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 4100806146766074774L;

	/**
	 * 状态值 CODE
	 */
	private int returnCode;
	/**
	 * 描述信息
	 */
	private String returnDesc;
	/**
	 * 返回数据
	 */
	private T returnData;
	/**
	 * 返回对象数据
	 */
	private ReturnPage returnPage;

	/**
	 * 构造函数
	 */
	public OutputJsonResult() {
		super();
		this.returnCode = ResultStatusEnum.SUCCESS.getCode();
		this.returnDesc=ResultStatusEnum.SUCCESS.getDesc();
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this,SerializerFeature.WriteMapNullValue);
	}

	public OutputJsonResult(int returnCode, String returnDesc) {
		super();
		this.returnCode = returnCode;
		this.returnDesc = returnDesc;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnDesc() {
		return returnDesc;
	}

	public void setReturnDesc(String returnDesc) {
		this.returnDesc = returnDesc;
	}

	public T getReturnData() {
		return returnData;
	}

	public void setReturnData(T returnData) {
		this.returnData = returnData;
	}

	public ReturnPage getReturnPage() {
		return returnPage;
	}

	public void setReturnPage(ReturnPage returnPage) {
		this.returnPage = returnPage;
	}

}







