package com.enroll.modules.service;

import java.util.List;
import java.util.Map;

import com.enroll.modules.pojo.ClassEntity;

/**
 * @author hsc
 *
 * Feb 20, 2018
 */
public interface ClassService {

	/**
	 * 保存
	 * @param classEntity
	 */
	void save(ClassEntity classEntity);
	
	/**
	 * 更新
	 * @param classEntity
	 */
	void update(ClassEntity classEntity);
	
	/**
	 * 查询列表
	 * @param map
	 */
	List<ClassEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	ClassEntity queryObject(Long id);
	
	/**
	 * 根据班级编号查询
	 * @param classCode
	 * @return
	 */
	ClassEntity queryObjectByCode(String classCode);
}
