package com.enroll.modules.service;

import java.util.List;
import java.util.Map;

import com.enroll.modules.pojo.StuInfoEntity;
import com.enroll.modules.pojo.StudentFamilyMemberEntity;

/**
 * @author hsc
 *
 * Sep 5, 2017
 */
public interface StudentEnrollService {

	/**
	 * 保存
	 * @param stuInfoEntity
	 */
	void save(StuInfoEntity stuInfoEntity);
	
	void save(StudentFamilyMemberEntity studentFamilyMemberEntity);
	
	/**
	 * 修改
	 * @param stuInfoEntity
	 */
	void update(StuInfoEntity stuInfoEntity);
	
	void update(StudentFamilyMemberEntity studentFamilyMemberEntity);
	
	/**
	 * 根据用户id查询信息
	 * @param stuInfoEntity
	 */
	StuInfoEntity queryByUserId(Long id);
	
	/**
	 * 根据id查询信息
	 * @param stuInfoEntity
	 */
	StuInfoEntity queryObject(Long id);
	
	/**
	 * 查询
	 * @param map
	 * @return
	 */
	List<StuInfoEntity> queryList(Map<String, Object> map);
	
}
