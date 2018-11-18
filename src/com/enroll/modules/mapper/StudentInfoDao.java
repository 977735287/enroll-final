package com.enroll.modules.mapper;

import java.util.List;

import com.enroll.modules.pojo.AreaEntity;
import com.enroll.modules.pojo.Nation;
import com.enroll.modules.pojo.StuInfoEntity;

/**
 * @author hsc
 *
 * Sep 13, 2017
 */
public interface StudentInfoDao  extends BaseDao<StuInfoEntity> {

	List<AreaEntity> queryAreaList();
	
	List<Nation> queryNationList();
}
