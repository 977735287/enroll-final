package com.enroll.modules.mapper;

import com.enroll.modules.pojo.StuInfoEntity;

/**
 * @author hsc
 *
 * Sep 15, 2017
 */
public interface StudentInfoManageDao extends BaseDao<StuInfoEntity> {

	int deleteFamilyMember(Long id);
	
	int deleteUF(Long id);
}
