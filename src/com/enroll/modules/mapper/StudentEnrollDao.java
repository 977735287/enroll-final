package com.enroll.modules.mapper;

import com.enroll.modules.pojo.StuInfoEntity;
import com.enroll.modules.pojo.StudentFamilyMemberEntity;

/**
 * @author hsc
 *
 * Sep 6, 2017
 */
public interface StudentEnrollDao extends BaseDao<StuInfoEntity> {
	
	void saveFamilyMember(StudentFamilyMemberEntity studentFamilyMemberEntity);
	
	void updateFamilyMember(StudentFamilyMemberEntity studentFamilyMemberEntity);
}
