package com.enroll.modules.service;

import java.util.List;
import java.util.Map;

import com.enroll.modules.pojo.StuInfoEntity;

/**
 * @author hsc
 *
 * Sep 15, 2017
 */
public interface StudentInfoManageService {

	/**
	 * 查询学生列表
	 * @param map
	 * @return
	 */
	List<StuInfoEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 根据学生信息ID，查询学生信息
	 * @param id
	 * @return
	 */
	StuInfoEntity queryObject(Long id);
	
	/**
	 * 删除学生信息
	 */
	void deleteBatch(Long[] ids, String url);
}
