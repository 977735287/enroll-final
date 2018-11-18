package com.enroll.modules.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enroll.modules.mapper.LengthsOfSchoolDao;
import com.enroll.modules.pojo.LengthsOfSchoolingEntity;
import com.enroll.modules.service.LengthsOfSchoolService;

/**
 * @author hsc
 *
 * Aug 14, 2017
 */
@Service
public class LengthsOfSchoolServiceImpl implements LengthsOfSchoolService {
	
	@Autowired
	private LengthsOfSchoolDao lengthsOfSchoolDao;

	@Override
	public void save(LengthsOfSchoolingEntity lengthsOfSchoolingEntity) {			
		lengthsOfSchoolingEntity.setCreateTime(new Date());	
		lengthsOfSchoolDao.save(lengthsOfSchoolingEntity);
	}

	@Override
	public void update(LengthsOfSchoolingEntity lengthsOfSchoolingEntity) {	
		lengthsOfSchoolDao.update(lengthsOfSchoolingEntity);
	}

	@Override
	public void deleteBatch(Long[] ids) {
		lengthsOfSchoolDao.deleteBatch(ids);
	}

	@Override
	public List<LengthsOfSchoolingEntity> queryList(Map<String, Object> map) {	
		return lengthsOfSchoolDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {	
		return lengthsOfSchoolDao.queryTotal(map);
	}

	@Override
	public LengthsOfSchoolingEntity queryObject(Long id) {
		return lengthsOfSchoolDao.queryObject(id);
	}

}
