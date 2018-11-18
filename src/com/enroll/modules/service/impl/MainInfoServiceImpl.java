package com.enroll.modules.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enroll.modules.mapper.MainInfoDao;
import com.enroll.modules.pojo.SysMainInfo;
import com.enroll.modules.service.MainInfoService;

/**
 * @author hsc
 *
 * Feb 3, 2018
 */
@Service
public class MainInfoServiceImpl implements MainInfoService {

	@Autowired
	private MainInfoDao mainInfoDao;

	@Override
	public void save(SysMainInfo sysMainInfo) {
		mainInfoDao.save(sysMainInfo);
	}

	@Override
	public void update(SysMainInfo sysMainInfo) {
		mainInfoDao.update(sysMainInfo);
	}

	@Override
	public void deleteBatch(Long[] ids) {
		mainInfoDao.deleteBatch(ids);
	}
	
	@Override
	public SysMainInfo queryObject(Long id) {
		return mainInfoDao.queryObject(id);
	}
}
