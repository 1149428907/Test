package com.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.test.common.SessionConstant;
import com.test.page.PageData;
import com.test.until.StringUtil;
import com.test.until.UuidUtil;

/**
 * <P>File name : BaseController.java </P>
 * <P>Author : dgf </P> 
 * <P>Date : 2017年4月12日下午2:38:53 </P>
 * <P>Desc : 基础的控制层方法</P>
 * <P>Update History : <ul>
 *     <li></li>
 *     <li></li> 
 *     </ul>
 * </P>
 */
public  class BaseController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * 得到32位的uuid
	 * 
	 * @return
	 */
	public String get32UUID() {
		return UuidUtil.get32UUID();
	}
	
	/**
	 * 得到PageData 
	 */
	public PageData getPageData() {
		PageData pd = new PageData(this.getRequest());
		return pd;
	}
	
	/**
	 * 获取web用户id
	 * @Title: getBusinessUserId 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String getWebUserId(){		
		return  String.valueOf(this.getRequest().getAttribute(SessionConstant.SESSION_USER_ID));
	}
	
	/**
	 * 获取商家后台id
	 * @Title: getBusinessUserId 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String getBusinessUserId(){		
		String id = String.valueOf(this.getRequest().getSession().getAttribute(SessionConstant.SESSION_USER_ID));
		if(StringUtil.isEmpty(id)){
			id = null;
		}
		return id;
	}
	
	/**
	 * 获取运营后台id
	 * @Title: getOperationUserId 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public String getOperationUserId(){
		String id = String.valueOf(this.getRequest().getSession().getAttribute(SessionConstant.SESSION_USER_ID));
		return id;
	}
	
	/**
	 * 
	 * @Title: getCurrentShopId 
	 * @Description: 商家后台，获取session中的店铺id
	 * @return
	 * @return: String
	 */
	public String getCurrentShopId(){
		String shopId=String.valueOf(this.getRequest().getAttribute(SessionConstant.SESSION_SHOP_ID));
		if(StringUtil.isEmpty(shopId)){
		 shopId = String.valueOf(this.getRequest().getSession().getAttribute(SessionConstant.SESSION_SHOP_ID));
		}
		return shopId;
	}
	
	/**
	 * 
	 * @Title: getCurrentShopId 
	 * @Description: 商家后台，获取session中的商家id
	 * @return
	 * @return: String
	 */
	public String getCurrentBusinessId(){
		String businessId = String.valueOf(this.getRequest().getSession().getAttribute(SessionConstant.SESSION_BUSINESS_ID));
		if(StringUtil.isEmpty(businessId)){
			businessId = null;
		}
		return businessId;
	}
	
	/**
	 * BaseController.getCurrentGuidId()<BR>
	 * <P>Author :  jdd  </P>  
	 * <P>Date : 2017年4月27日下午5:58:40</P>
	 * <P>Desc : 获取导购员id </P>
	 * @return
	 */
	public String getCurrentGuidId(){		
		String id = String.valueOf(this.getRequest().getSession().getAttribute(SessionConstant.SESSION_GUIDE_ID));
		if(StringUtil.isEmpty(id)){
			id = "";
		}
		return id;
	}

}










