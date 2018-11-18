package com.enroll.modules.mapper;

import com.enroll.modules.pojo.MajorEntity;

/**
 * @author hsc
 *
 * Aug 15, 2017
 */
public interface MajorDao extends BaseDao<MajorEntity> {

	
	/**
	 * 根据主键查数据,给多对一用
	 */
	MajorEntity select(Integer majorId);
}
