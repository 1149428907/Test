package com.test.mapper;

import java.util.List;

import com.test.page.PageData;

public interface BaseMapper<T> {
	
   /**
    * 查询所有
    */
   List<T> listAll(PageData pd)throws Exception;
   
   /**
    * 分页查询
    */
   List<T> listPage(PageData pd)throws Exception;
   
   /**
    * 查询详情
    */
   T findById(PageData pd) throws Exception;
   
   /**
    * 保存
    */
   int save(PageData pd)throws Exception;
   
   /**
    * 修改   
    */
   int edit(PageData pd)throws Exception;
   
   /**
    * 删除
    */
   int delete(PageData pd)throws Exception;
}
