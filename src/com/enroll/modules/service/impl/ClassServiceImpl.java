package com.enroll.modules.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enroll.modules.mapper.ClassDao;
import com.enroll.modules.pojo.ClassEntity;
import com.enroll.modules.service.ClassService;

/**
 * @author hsc
 *
 * Feb 20, 2018
 */
@Service
public class ClassServiceImpl implements ClassService {
	
	@Autowired
	private ClassDao classDao;

	@Override
	public void save(ClassEntity classEntity) {
		classDao.save(classEntity);
	}

	@Override
	public void update(ClassEntity classEntity) {
		classDao.update(classEntity);
	}

	@Override
	public List<ClassEntity> queryList(Map<String, Object> map) {
		return classDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return classDao.queryTotal(map);
	}

	@Override
	public ClassEntity queryObject(Long id) {
		return classDao.queryObject(id);
	}

	@Override
	public ClassEntity queryObjectByCode(String classCode) {
		return classDao.queryObjectByCode(classCode);
	}

}
