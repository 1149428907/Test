package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.bean.SysNewsType;
import com.test.common.TestException;
import com.test.enums.StateEnum;
import com.test.mapper.SysNewsTypeMapper;
import com.test.page.PageData;
import com.test.until.DateUtil;
import com.test.until.StringUtil;
import com.test.until.UuidUtil;


@Service
public class SysNewsTypeService  {

    @Autowired
	private SysNewsTypeMapper mapper;
    
		/*
	 * 增加
	 */
	public int save(PageData pd) throws Exception {
		String id = pd.getString("id");
		String state = pd.getString("state");
		String typeNo = pd.getString("typeNo");
		if(StringUtil.isEmpty(id)){
			pd.put("id", UuidUtil.get32UUID());
		}
		if(StringUtil.isEmpty(state)){
			pd.put("state", StateEnum.NODEL.getCode());
		}
		if(StringUtil.isEmpty(typeNo)){
			pd.put("typeNo", getCode());
		}
		pd.put("createDate", DateUtil.getTime());
		return mapper.save(pd);
	}

	private String getCode() throws Exception{
		String typeNo = "100000000";
		PageData pd = new PageData();
		List<PageData> list = mapper.listOrderByCode(pd);
		if(list != null && list.size() >0){
			PageData obj = list.get(0);
			String no = obj.getString("type_no");
			if(!StringUtil.isEmpty(no) && StringUtil.isNumeric(no)){
				typeNo = Long.parseLong(no)+1+"";
			}
		}
		return typeNo;
	}
	
	/*
	 * 删除
	 */
	public int delete(PageData pd) throws Exception {
		return mapper.delete(pd);
	}

	/*
	 * 编辑
	 */
	public int edit(PageData pd) throws Exception {
		pd.put("updateDate", DateUtil.getTime());
		return mapper.edit(pd);
	}

	/*
	 * 分页
	 */
	public List<SysNewsType> listPage(PageData pd) throws Exception {
		return mapper.listPage(pd);
	}

	/*
	 * 查询所有
	 */
	public List<SysNewsType> listAll(PageData pd) throws Exception {
		return mapper.listAll(pd);
	}

	/*
	 * 查询详情
	 */
	public SysNewsType findById(PageData pd) throws Exception {
		return mapper.findById(pd);
	}

	public int saveOrEditType(PageData pd) throws Exception {
		int result = 0;
		String id = pd.getString("id");
		String typeName = pd.getString("typeName");
		SysNewsType type = null;
		if(!StringUtil.isEmpty(id) ){
			type = this.findById(pd);
		}
		if(type == null){
			if(!StringUtil.isEmpty(typeName)){
				PageData findpd = new PageData();
				findpd.put("typeName", typeName);
				if(!findForCanUseName(findpd)){
					throw new TestException("类型名称已经使用！");
				}
			}
			//保存
			int count = this.save(pd);
			if(count < 1){
				throw new TestException("保存失败！");
			}
		}else{
			if(!StringUtil.isEmpty(typeName)){
				PageData findpd = new PageData();
				findpd.put("typeName", typeName);
				findpd.put("id", id);
				if(findForCanUseName(findpd)){
					throw new TestException("类型名称已经使用！");
				}
			}
			//编辑
			int count = this.edit(pd);
			if(count < 1){
				throw new TestException("编辑失败！");
			}
		}
		result = 1;
		return result;
	}

	public int delType(PageData pd) throws Exception {
		int result = 0;
		String id = pd.getString("id");
		if(StringUtil.isEmpty(id)){
			throw new TestException("缺少参数！");
		}
//		PageData findpd = new PageData();
//		findpd.put("typeId", id);
//		List<SysNewsTemplate> list = sysNewsTemplateMapper.listAll(findpd);
//		if(list != null && list.size() > 0){
//			throw new TestException("存在模板，不能删除！");
//		}
		pd.put("state", StateEnum.DEL.getCode());
		pd.put("updateDate", DateUtil.getTime());
		int count = mapper.edit(pd);
		if(count < 1){
			throw new TestException("删除模板类型失败！");
		}
		result = 1;
		return result;
	}
	
	public boolean findForCanUseName(PageData pd) throws Exception{
		boolean result = true;
		String typeName = pd.getString("typeName");
		String id = pd.getString("id");
		if(StringUtil.isEmpty(typeName)){
			throw new TestException("参数错误！");
		}
		PageData findpd = new PageData();
		findpd.put("typeName", typeName);
		List<SysNewsType> list = mapper.listAll(findpd);
		if(list != null && list.size() > 0){
			if(list.size() == 1){
				if(StringUtil.isEmpty(id)){
					result = false;
				}else{
					if(!list.get(0).getId().equals(id)){
						result = false;
					}
				}
			}else{
				result = false;
			}
		}
		return result;
	}
}

