package com.enroll.modules.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enroll.modules.mapper.StudentInfoManageDao;
import com.enroll.modules.pojo.StuInfoEntity;
import com.enroll.modules.service.StudentInfoManageService;

/**
 * @author hsc
 *
 * Sep 15, 2017
 */
@Service
public class StudentInfoManageServiceImpl implements StudentInfoManageService {

	@Autowired
    private StudentInfoManageDao studentInfoManageDao;

	@Override
	public List<StuInfoEntity> queryList(Map<String, Object> map) {
		
		return studentInfoManageDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		
		return studentInfoManageDao.queryTotal(map);
	}

	@Override
	public StuInfoEntity queryObject(Long id) {
		if(studentInfoManageDao.queryListById(id) != null){
			if(studentInfoManageDao.queryListById(id).size() != 0){
				return studentInfoManageDao.queryListById(id).get(0);
			}
		}
		return null;
	}
	
	@Override
	public void deleteBatch(Long[] ids, String url) {
		for(Long id : ids){
			StuInfoEntity stuInfoEntity = queryObject(id);
			studentInfoManageDao.delete(id);
			if(stuInfoEntity.getStuFamilyMember1() != null){
				studentInfoManageDao.deleteFamilyMember(stuInfoEntity.getStuFamilyMember1().getId());
			}
			if(stuInfoEntity.getStuFamilyMember2() != null){
				studentInfoManageDao.deleteFamilyMember(stuInfoEntity.getStuFamilyMember2().getId());
			}
			if(stuInfoEntity.getStuUploadFile() != null){
				File file = new File(url + stuInfoEntity.getStuUploadFile().getNewName());
				if(file.exists() && file.isFile()){
					file.delete();
				}else{
					System.out.println("file not exists!");
				}
				studentInfoManageDao.deleteUF(stuInfoEntity.getStuUploadFile().getId());
			}
		}
	}

}
