package com.enroll.modules.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enroll.modules.mapper.StudentInfoDao;
import com.enroll.modules.pojo.AreaEntity;
import com.enroll.modules.pojo.Nation;
import com.enroll.modules.pojo.StuInfoEntity;
import com.enroll.modules.service.StudentInfoService;

/**
 * @author hsc
 *
 * Sep 13, 2017
 */
@Service
public class StudentInfoServiceImpl implements StudentInfoService {
	
	@Autowired
    private StudentInfoDao sysdentInfoDao;

	@Override
	public List<StuInfoEntity> queryList(Map<String, Object> map) {
		
		return sysdentInfoDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		
		return sysdentInfoDao.queryTotal(map);
	}

	@Override
	public StuInfoEntity queryObject(Long id) {		
		if(sysdentInfoDao.queryListById(id) != null){
			if(sysdentInfoDao.queryListById(id).size() != 0){
				return sysdentInfoDao.queryListById(id).get(0);
			}
		}
		return null;
	}
	
	@Override
	public List<AreaEntity> queryAreaList(){
		return sysdentInfoDao.queryAreaList();
	}
	
	@Override
	public List<Nation> queryNationList(){
		return sysdentInfoDao.queryNationList();
	}

}
