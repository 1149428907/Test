package com.test.beetl;

import java.io.IOException;
import java.util.Map;

import org.beetl.core.Tag;
import org.springframework.stereotype.Component;

/**
 * beetl分页 
 * 
 * @author td
 *
 */
@Component
public class Pagination extends Tag {

	private int currentPage;
	private int showCount;
	private int totalResult;
	private String actionId;

	/**
	 * 
	 * <P>
	 * Author : 张天德
	 * </P>
	 * <P>
	 * Date : 2016年3月31日
	 * </P>
	 * 
	 * @see org.beetl.core.Tag#render()
	 * @param currentPage
	 * @param showCount
	 * @param totalResult
	 */
	@Override
	public void render() {
		Object[] array = this.args;
		String tagHtml = "请检查参数设置：<#pagination  currentPage=\"0\" showCount=\"15\" totalResult=\"0\" searchFormId=\"\">";
		if (array.length > 1) {
			int cPage = 1;
			int sCount = 15;
			int tResult = 0;
			String aId = "";
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) this.args[1];
			if (map.containsKey("currentPage") && null != map.get("currentPage")) {
				cPage = Integer.valueOf(map.get("currentPage").toString());
			}
			if (map.containsKey("showCount") && null != map.get("showCount")) {
				sCount = Integer.valueOf(map.get("showCount").toString());
			}
			if (map.containsKey("totalResult") && null != map.get("totalResult")) {
				tResult = Integer.valueOf(map.get("totalResult").toString());
			}
			if (map.containsKey("searchFormId") && null != map.get("searchFormId")) {
				aId = map.get("searchFormId").toString();
			}
			if (tResult < 1) {
				tagHtml = "";
			} else {
				Pagination p = new Pagination(cPage, sCount, tResult, aId);
				tagHtml = p.getTagHtml();
			}
		}
		try {
			this.bw.writeString(tagHtml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Pagination() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pagination(int currentPage, int showCount, int totalResult, String actionId) {
		super();
		this.currentPage = currentPage;
		this.showCount = showCount;
		this.totalResult = totalResult;
		this.actionId = actionId;
	}

	public int getCurrentPage() {
		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (currentPage > getTotalPage()) {
			currentPage = getTotalPage();
		}
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return (totalResult % showCount) == 0 ? (totalResult / showCount) : (totalResult / showCount + 1);
	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getTagHtml() {

		// 第一页与当前页之间有多少个页面
		int per = currentPage - 2;

		// 当前页与最后一页有多少个页面
		int suf = getTotalPage() - currentPage - 1;

		// 中间页码的开始 页
		int start = currentPage - 3;

		// 中间页码的结束页
		int end = currentPage + 2;

		if (start <= 1) {
			// 如果中间开始页小于1 -->则中间开始页为2
			start = 2;
		}
		if (end >= getTotalPage()) {
			// 如果中间结束页大于于 总页数 -->则中间结束页为 totalPage-1
			end = getTotalPage() - 1;
		}
		StringBuffer sb = new StringBuffer("");

		// --------------------------------------------start--------------------------------------------------
		// 拼接总页数
		sb.append("<span class=\"m-r-md media-middle\">共<i>" + totalResult
				+ "</i>条</span><span>每页显示</span><select name=\"\" value=\"10\" class=\"m-l-sm m-r-sm\">");
		sb.append("<option value=\"\">"+getShowCount()+"</option>");
		sb.append("</select>条&nbsp;&nbsp;");

		// 拼接 页数逻辑
		sb.append("<ul class=\"pagination m-l-sm\">");
		sb.append("<li><a " + getActivePage(1) + " href=\"javascript:;\" onclick=\"submit_pagForm(1)\">1</a></li>");
		if (per > 3) {
			sb.append("<span>...</span>");
		}
		for (int i = start; i <= end; i++) {
			sb.append("<li><a  " + getActivePage(i) + " href=\"javascript:;\" onclick=\"submit_pagForm(" + i + ")\">"
					+ i + "</a></li>\n");
		}
		if (suf > 2) {
			sb.append("<span>...</span>");
		}
		if (getTotalPage() > 1) {
			sb.append("<li><a " + getActivePage(getTotalPage()) + " href=\"javascript:;\" onclick=\"submit_pagForm("
					+ getTotalPage() + ")\">" + getTotalPage() + "</a></li>\n");
		}

		sb.append("</ul>");

		// 拼接跳转
		sb.append(
				"<span class=\"media-middle\">跳到<input type=\"text\" id=\"tiaozId\" value=\"\" onchange=\"showValue(this.value)\" class=\"number\" onkeyup=\"value=value.replace(/[^\\d]/g,'')\" onbeforepaste=\"clipboardData.setData('text',clipboardData.getData('text').replace(/[^\\d]/g,''))\"/>页");
		sb.append(
				"<a href=\"javascript:;\" class=\"inline center page-confirm\" onclick=\"tiaoz_pagForm()\">确定</a></span>");
				// -------------------------------------------end--------------------------------------------------

		// 拼接script
		sb.append("<script type=\"text/javascript\">\n");
		// 拼接函数
		sb.append("function submit_pagForm(value){\n");
		sb.append("$('input[name=\"currentPage\"]').val(value)\n");
		sb.append("$(\"#" + actionId + "\").submit();\n");
		sb.append("	}\n");

		// 拼接跳转函数
		sb.append("function tiaoz_pagForm(){\n");
		// 获取跳转的值
		sb.append("var tiaozNum=$('#tiaozId').val()\n");
		sb.append("if(tiaozNum ==''){return false;}\n");
		sb.append("if(tiaozNum>" + getTotalPage() + "){tiaozNum=" + getTotalPage() + ";}\n");
		sb.append("if(tiaozNum<1){tiaozNum=1;}\n");
		sb.append("$('input[name=\"currentPage\"]').val(tiaozNum)\n");
		if ("".equals(actionId)) {
			sb.append("$(\"form:first\").submit();\n");
		} else {
			sb.append("$(\"#" + actionId + "\").submit();\n");
		}
		sb.append("	}\n");

		sb.append("$(function(){\n");
		if ("".equals(actionId)) {
			sb.append("if( $(\"input[name='currentPage']\").length==0){\n");
			sb.append("$(\"form:first\").append(\"<input type=\\\"hidden\\\" name=\\\"currentPage\\\"/>\");\n");
			sb.append("}");
		} else {
			sb.append("if( $(\"input[name='currentPage']\").length==0){\n");
			sb.append("$(\"#" + actionId + "\").append(\"<input type=\\\"hidden\\\" name=\\\"currentPage\\\" />\");\n");
			sb.append("}");
		}
		sb.append("	});\n");

		sb.append("</script>\n");
		return sb.toString();

	}

	private String getActivePage(int i) {
		return i == currentPage ? "class=\"active\"" : "";
	}

}