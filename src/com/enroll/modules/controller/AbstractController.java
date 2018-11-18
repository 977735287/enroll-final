package com.enroll.modules.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enroll.modules.pojo.StuInfoEntity;
import com.enroll.modules.pojo.SysUserEntity;

/**
 * @author hsc
 *
 * Jul 20, 2017
 */
public abstract class AbstractController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private Long uploadId;
	private List<StuInfoEntity> list;

	protected SysUserEntity getUser() {
		return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
	
	protected void setUploadId(Long uploadId){
		this.uploadId = uploadId;
	}
	
	protected Long getUploadId(){
		return uploadId;
	}
	
	protected void setStuInfoList(List<StuInfoEntity> list){
		this.list = list;
	}
	
	protected List<StuInfoEntity> getStuInfoList(){
		return list;
	}
}
