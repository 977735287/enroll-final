package com.enroll.modules.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enroll.modules.mapper.StudentEnrollDao;
import com.enroll.modules.pojo.StuInfoEntity;
import com.enroll.modules.pojo.StudentFamilyMemberEntity;
import com.enroll.modules.service.StudentEnrollService;

/**
 * @author hsc
 *
 * Sep 6, 2017
 */
@Service
public class StudentEnrollServiceImpl implements StudentEnrollService {

	@Autowired
    private StudentEnrollDao studentEnrollDao;
	
	@Override
	public void save(StuInfoEntity stuInfoEntity) {
		studentEnrollDao.save(stuInfoEntity);
	}
	
	@Override
	public void save(StudentFamilyMemberEntity studentFamilyMemberEntity) {
		studentEnrollDao.saveFamilyMember(studentFamilyMemberEntity);
	}

	@Override
	public void update(StuInfoEntity stuInfoEntity) {
		studentEnrollDao.update(stuInfoEntity);
	}
	
	@Override
	public void update(StudentFamilyMemberEntity studentFamilyMemberEntity) {
		studentEnrollDao.updateFamilyMember(studentFamilyMemberEntity);
	}
	
	@Override
	public StuInfoEntity queryObject(Long id) {
		return studentEnrollDao.queryObject(id);
	}
	
	@Override
	public StuInfoEntity queryByUserId(Long id) {
		if(studentEnrollDao.queryListById(id) != null){
			if(studentEnrollDao.queryListById(id).size() != 0){
				return studentEnrollDao.queryListById(id).get(0);
			}
		}
		return null;
	}

	@Override
	public List<StuInfoEntity> queryList(Map<String, Object> map) {
		
		return studentEnrollDao.queryList(map);
	}
}
