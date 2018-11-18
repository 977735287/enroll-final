package com.enroll.modules.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enroll.modules.mapper.MajorDao;
import com.enroll.modules.pojo.MajorEntity;
import com.enroll.modules.service.MajorService;

/**
 * @author hsc
 *
 * Aug 15, 2017
 */
@Service
public class MajorServiceImpl implements MajorService{

	@Autowired
	private MajorDao majorDao;

	@Override
	public void save(MajorEntity majorEntity) {			
		majorEntity.setCreateTime(new Date());	
		majorDao.save(majorEntity);
	}

	@Override
	public void update(MajorEntity majorEntity) {	
		majorDao.update(majorEntity);
	}

	@Override
	public void deleteBatch(Long[] ids) {
		majorDao.deleteBatch(ids);
	}

	@Override
	public List<MajorEntity> queryList(Map<String, Object> map) {	
		return majorDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {	
		return majorDao.queryTotal(map);
	}

	@Override
	public MajorEntity queryObject(Long id) {
		return majorDao.queryListById(id).get(0);
	}
	
}
