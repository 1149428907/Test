package com.test.bean;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * <P>File name : SysNewsType.java </P>
 * <P>Author : default </P> 
 * <P>Date : Fri Jul 07 09:54:35 CST 2017</P>
 * <P>Desc : 消息类型</P>
 */
public class SysNewsType implements Serializable{

 private static final long serialVersionUID = 1L;
 
  private String id;//主键
  private Timestamp createDate;//创建时间
  private Timestamp updateDate;//修改时间
  private String state;//状态
  private String typeScenarios;//应用场景
  private String typeName;//消息类型名称
  private String typeDesc;//描述
  private String typeNo;//类型编号 type_no
  
	
 public String getTypeNo() {
	return typeNo;
}

public void setTypeNo(String typeNo) {
	this.typeNo = typeNo;
}

public void setId(String id){
  this.id = id;
 }
	
 public String getId(){
  return this.id;
 }
 public void setCreateDate(Timestamp createDate){
  this.createDate = createDate;
 }
	
 public Timestamp getCreateDate(){
  return this.createDate;
 }
 public void setUpdateDate(Timestamp updateDate){
  this.updateDate = updateDate;
 }
	
 public Timestamp getUpdateDate(){
  return this.updateDate;
 }
 public void setState(String state){
  this.state = state;
 }
	
 public String getState(){
  return this.state;
 }
 public void setTypeScenarios(String typeScenarios){
  this.typeScenarios = typeScenarios;
 }
	
 public String getTypeScenarios(){
  return this.typeScenarios;
 }
 public void setTypeName(String typeName){
  this.typeName = typeName;
 }
	
 public String getTypeName(){
  return this.typeName;
 }
 public void setTypeDesc(String typeDesc){
  this.typeDesc = typeDesc;
 }
	
 public String getTypeDesc(){
  return this.typeDesc;
 }
}