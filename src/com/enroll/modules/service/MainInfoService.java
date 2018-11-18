package com.enroll.modules.service;

import com.enroll.modules.pojo.SysMainInfo;

/**
 * @author hsc
 *
 * Feb 3, 2018
 */
public interface MainInfoService {

	void save(SysMainInfo sysMainInfo);
	
	void update(SysMainInfo sysMainInfo);
	
	void deleteBatch(Long[] ids);

	SysMainInfo queryObject(Long id);
}
