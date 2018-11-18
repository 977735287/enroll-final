package com.enroll.modules.service;

import java.util.List;
import java.util.Map;

import com.enroll.modules.pojo.StuInfoEntity;

/**
 * @author hsc
 *
 * Mar 20, 2018
 */
public interface StudentInfoListService {
	
	/**
	 * 查询学生列表
	 * 
	 * @param map
	 * @return
	 */
	List<StuInfoEntity> queryList(Map<String, Object> map);

	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
}
