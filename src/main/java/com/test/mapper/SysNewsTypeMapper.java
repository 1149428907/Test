 package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.test.bean.SysNewsType;
import com.test.page.PageData;

/**
 * <P>File name : ISysNewsTypeService.java </P>
 * <P>Author : default </P> 
 * <P>Date : Fri Jul 07 09:54:35 CST 2017</P>
 * <P>Desc : 消息类型</P>
 */
@Mapper 
public interface SysNewsTypeMapper extends BaseMapper<SysNewsType>{
	
	/**
	 * 
	 * <P>Author : jdd  </P>      
	 * <P>Date : 2017年7月7日 </P>
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	List<PageData> listOrderByCode(PageData pd)throws Exception;
	
}


