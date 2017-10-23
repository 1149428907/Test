package com.test.page;

import java.io.Serializable;


public class Page implements Serializable {

	/**
	 * 字段或域定义：<code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_SHOW_COUNT           = 15;
	public static final int DEFAULT_CURRENT_PAGE_INDEX   = 1;

	private int 				showCount                    =  DEFAULT_SHOW_COUNT; // 每页显示记录数
	private int 				totalPage;                             // 总页数
	private int 				totalResult; // 总记录数
	private int 				currentPage; // 当前页
	private int 				currentResult; // 当前记录起始索引
	private boolean 			entityOrField; // true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page page属性
	private String 				pageStr; // 最终页面显示的底部翻页导航，详细见：getPageStr();
	private PageData 			pd        = new PageData();
	
	

	public PageData getPd() {
		return pd;
	}

	public void setPd(PageData pd) {
		this.pd = pd;
	}

	public Page() {
		this.getPageStr();
	}

	public Page(PageData pd) {
		this.currentPage=pd.get("currentPage")==null?Page.DEFAULT_CURRENT_PAGE_INDEX:pd.getInteger("currentPage");
		this.showCount=pd.get("showCount")==null?Page.DEFAULT_SHOW_COUNT:pd.getInteger("showCount");
		this.pd=pd;
	}
	
	public int getTotalPage() {
		if (totalResult % showCount == 0){
			totalPage = totalResult / showCount;
		}else{
			totalPage = totalResult / showCount + 1;
		}
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
		if (currentPage <= 0){
			currentPage = 1;
		}
		if ( currentResult >0  &&  currentPage > getTotalPage()){
			currentPage = getTotalPage();
		}
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if (totalResult > 0) {
			sb.append("	<ul>\n");
			if (currentPage == 1) {
				sb.append("	<li><a>共<font color=red>" + totalResult + "</font>条</a></li>\n");
				sb.append(
						"	<li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\"页码\"/></li>\n");
				sb.append(
						"	<li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn btn-mini btn-success\">跳转</a></li>\n");
				sb.append("	<li><a>首页</a></li>\n");
				sb.append("	<li><a>上页</a></li>\n");
			} else {
				sb.append("	<li><a>共<font color=red>" + totalResult + "</font>条</a></li>\n");
				sb.append(
						"	<li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\"页码\"/></li>\n");
				sb.append(
						"	<li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn btn-mini btn-success\">跳转</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(1)\">首页</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(" + (currentPage - 1)
						+ ")\">上页</a></li>\n");
			}
			int showTag = 5;// 分页标签显示数量
			int startTag = 1;
			if (currentPage > showTag) {
				startTag = currentPage - 1;
			}
			int endTag = startTag + showTag - 1;
			for (int i = startTag; i <= totalPage && i <= endTag; i++) {
				if (currentPage == i)
					sb.append("<li><a><font color='#808080'>" + i + "</font></a></li>\n");
				else
					sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(" + i + ")\">" + i + "</a></li>\n");
			}
			if (currentPage == totalPage) {
				sb.append("	<li><a>下页</a></li>\n");
				sb.append("	<li><a>尾页</a></li>\n");
			} else {
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(" + (currentPage + 1)
						+ ")\">下页</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(" + totalPage + ")\">尾页</a></li>\n");
			}
			sb.append("	<li><a>第" + currentPage + "页</a></li>\n");
			sb.append("	<li><a>共" + totalPage + "页</a></li>\n");

			sb.append(
					"	<li><select title='显示条数' name=\"showCount\"   style=\"width:55px;float:left;\" onchange=\"changeCount(this.value)\">\n");
			sb.append("	<option value=\"" + showCount + "\">" + showCount + "</option>\n");
			sb.append("	<option value=\"10\">10</option>\n");
			sb.append("	<option value=\"20\">20</option>\n");
			sb.append("	<option value=\"30\">30</option>\n");
			sb.append("	<option value=\"40\">40</option>\n");
			sb.append("	<option value=\"50\">50</option>\n");
			sb.append("	<option value=\"60\">60</option>\n");
			sb.append("	<option value=\"70\">70</option>\n");
			sb.append("	<option value=\"80\">80</option>\n");
			sb.append("	<option value=\"90\">90</option>\n");
			sb.append("	<option value=\"99\">99</option>\n");
			sb.append("	</select>\n");
			sb.append("	</li>\n");

			sb.append("</ul>\n");
			sb.append("<script type=\"text/javascript\">\n");

			// 换页函数
			sb.append("function nextPage(page){");
			sb.append(" top.jzts();");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&" + (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}\n");
			sb.append("		else{url += \"?" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
			sb.append("		url = url + page + \"&" + (entityOrField ? "showCount" : "page.showCount") + "=" + showCount
					+ "\";\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
			sb.append("		url = url + page + \"&" + (entityOrField ? "showCount" : "page.showCount") + "=" + showCount
					+ "\";\n");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");

			// 调整每页显示条数
			sb.append("function changeCount(value){");
			sb.append(" top.jzts();");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&" + (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}\n");
			sb.append("		else{url += \"?" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
			sb.append("		url = url + \"1&" + (entityOrField ? "showCount" : "page.showCount") + "=\"+value;\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"1&" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
			sb.append("		url = url + \"&" + (entityOrField ? "showCount" : "page.showCount") + "=\"+value;\n");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");

			// 跳转函数
			sb.append("function toTZ(){");
			sb.append("var toPaggeVlue = document.getElementById(\"toGoPage\").value;");
			sb.append("if(toPaggeVlue == ''){document.getElementById(\"toGoPage\").value=1;return;}");
			sb.append("if(isNaN(Number(toPaggeVlue))){document.getElementById(\"toGoPage\").value=1;return;}");
			sb.append("nextPage(toPaggeVlue);");
			sb.append("}\n");
			sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}
	
	public String getPageStr(boolean isFront) {
		Integer currentPage = this.currentPage;
		Integer showCount = this.showCount;
		Integer totalPage = this.totalPage;
		Integer showNumber = 6;
		Boolean flag = true;
		
		Integer halfNumber = showNumber % 2 == 0 ? showNumber / 2 : showNumber / 2 + 1;
		StringBuffer sb = new StringBuffer();
		if (flag) {
			sb.append("<a href=\"javascript:void(0);\" onClick=\"submit_pagForm('" + 1 + "')\" class=\"first\">首页</a>");
			if (currentPage != 1) {
				sb.append("<a href=\"javascript:void(0);\"  onclick=\"submit_pagForm(" + (currentPage - 1)
						+ ")\"   class=\"prev\">上一页</a>");
			}
			// 当总页数小于七
			if (currentPage < showNumber && totalPage < showNumber) {
				for (int i = 1; i <= totalPage; i++) {
					if (currentPage == i) {
						sb.append("<span class=\"page current\">" + i + "</span>");
					} else {
						sb.append("<a  href=\"javascript:void(0);\" onclick=\"submit_pagForm(" + i
								+ ")\"  class=\"page \">" + i + "</a>");
					}
				}
			} else if (currentPage < showNumber && totalPage >= showNumber) {
				for (int i = 1; i <= showNumber; i++) {
					if (currentPage == i) {
						sb.append("<span class=\"page current\">" + i + "</span>");
					} else {
						sb.append("<a  href=\"javascript:void(0);\" onclick=\"submit_pagForm(" + i
								+ ")\"  class=\"page \">" + i + "</a>");
					}
				}
			} else if ((totalPage - currentPage) > halfNumber) {
				if (showNumber % 2 == 0) {
					for (int i = halfNumber - 1; i > 0; i--) {
						sb.append("<a href=\"javascript:void(0);\" onclick=\"submit_pagForm(" + (currentPage - i)
								+ ")\"  class=\"page \">" + (currentPage - i) + "</a>");
					}
					sb.append("<span class=\"page current\">" + currentPage + "</span>");
					for (int i = 1; i <= halfNumber; i++) {
						sb.append("<a href=\"javascript:void(0);\"  onclick=\"submit_pagForm(" + (currentPage + i)
								+ ")\"  class=\"page \">" + (currentPage + i) + "</a>");

					}
				} else {
					for (int i = halfNumber; i > 1; i--) {
						sb.append("<a href=\"javascript:void(0);\"  onclick=\"submit_pagForm(" + (currentPage - i)
								+ ")\"  class=\"page \">" + (currentPage - i) + "</a>");
					}
					sb.append("<span class=\"page current\">" + currentPage + "</span>");
					for (int i = 1; i < halfNumber; i++) {

						sb.append("<a href=\"javascript:void(0);\" onclick=\"submit_pagForm(" + (currentPage + i)
								+ ")\"  class=\"page \">" + (currentPage + i) + "</a>");

					}
				}

			} else {
				for (int i = (totalPage - showNumber + 1); i <= totalPage; i++) {
					if (currentPage == i) {
						sb.append("<span class=\"page current\">" + i + "</span>");
					} else {
						sb.append("<a  onclick=\"submit_pagForm(" + i
								+ ")\"   href=\"javascript:void(0);\"class=\"page \">" + i + "</a>");
					}
				}
			}
			if ((currentPage - totalPage) != 0) {
				sb.append("<a  href=\"javascript:void(0);\"  onclick=\"submit_pagForm(" + (currentPage + 1)
						+ ")\" class=\"next\">下一页</a>");
			}
			sb.append("<a  href=\"javascript:void(0);\"  onclick=\"submit_pagForm(" + totalPage
					+ ")\" lastPage=\""+ totalPage+"\" class=\"_end_page_\">末页</a>");
			sb.append("<span class=\"pageBox_pd c_2da7e0\">转向</span>");
			sb.append("<input type=\"text\" class=\"text_input inputPage\" name=\"\" id=\"jump\" value=\""+this.currentPage+"\" />");
			sb.append("<span class=\"pageBox_pd c_2da7e0\">页</span>");
			sb.append("<a  onclick=\"jump()\"  href=\"javascript:void(0);\">确定</a>");
		}
		// 拼接script
		sb.append("<script type=\"text/javascript\">\n");
		// 拼接函数
		sb.append("function submit_pagForm(value){\n");
//		sb.append("$('#page.currentPage').val(value)\n");
		sb.append("$(\".pageCurrent\").val(value)\n");
		sb.append("$(\"#pagForm\").submit();\n");
		sb.append("	}\n");
		// 拼接函数
		// 拼接函数
		sb.append("function jump(){\n");
//		sb.append("var jumpPage = $('#jump').val(); if($('#page.currentPage').val() < jumpPage){jumpPage = $('#page.currentPage').val();}$('#page.currentPage').val(jumpPage)\n");
		sb.append("var jumpPage = $('#jump').val(); if($('._end_page_').attr(\"lastPage\") < jumpPage){jumpPage = $('._end_page_').attr(\"lastPage\");}$('.pageCurrent').val(jumpPage)\n");
		sb.append("$(\"#pagForm\").submit();\n");
		sb.append("	}\n");
		sb.append("</script>\n");
		return sb.toString();
	}

	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {

		this.showCount = showCount;
	}

	public int getCurrentResult() {
		currentResult = (getCurrentPage() - 1) * getShowCount();
		if (currentResult < 0)
			currentResult = 0;
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public boolean isEntityOrField() {
		return entityOrField;
	}

	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}

	/*public PageData getPd() {
		return pd;
	}

	public void setPd(PageData pd) {
		this.pd = pd;
	}*/
    /*
     * 获取开始值
     */
	public int getStart_limit(){
		return (getCurrentPage()-1)*showCount+1;
	}
	/*
	 * 获取结束值
	 */
	public int getEnd_limit(){
		return getCurrentPage()*showCount;
	}
}
