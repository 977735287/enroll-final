package com.enroll.modules.mapper;

import com.enroll.modules.pojo.ClassEntity;

/**
 * @author hsc
 *
 * Feb 20, 2018
 */
public interface ClassDao extends BaseDao<ClassEntity> {

	ClassEntity queryObjectByCode(String classCode);
	
}
