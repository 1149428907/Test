package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <P>File name : SysNewsTempShopController.java </P>
 * <P>Author : default </P> 
 * <P>Date : Wed Jul 12 16:09:25 CST 2017</P>
 * <P>Desc : 店铺消息模板</P>
 */
@Controller
@RequestMapping(value="login")
public class LoginController extends BaseController {
  
    
    @RequestMapping(value="login")
    public ModelAndView list(){
    	ModelAndView mv = this.getModelAndView();
    	mv.setViewName("login/login");
    	return mv;
    }

}