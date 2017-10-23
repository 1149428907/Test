package com.test.common;

import java.io.Serializable;
/**
 * <P>File name : ReturnPage.java </P>
 * <P>Author : dgf </P> 
 * <P>Date : 2017年4月12日下午2:42:20 </P>
 * <P>Desc : 返回分页数据对象</P>
 * <P>Update History : <ul>
 *     <li></li>
 *     <li></li> 
 *     </ul>
 * </P>
 */
public class ReturnPage implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 每页显示记录数   
	 */
	private int showCount;
	/**
	 * 总页数
	 */
	private int totalPage;  
	/**
	 * 总记录数
	 */
	private int totalResult;  
	/**
	 * 当前页
	 */
	private int currentPage;  
	
	public ReturnPage(){
	}
	/**
	 * 构造函数
	 * @param showCount
	 * @param totalPage
	 * @param totalResult
	 * @param currentPage
	 */
	public ReturnPage(int showCount, int totalPage, int totalResult, int currentPage) {
		super();
		this.showCount = showCount;
		this.totalPage = totalPage;
		this.totalResult = totalResult;
		this.currentPage = currentPage;
	}
	
	public int getShowCount() {
		return showCount;
	}
	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
}
