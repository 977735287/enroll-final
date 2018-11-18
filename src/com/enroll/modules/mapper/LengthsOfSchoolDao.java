package com.enroll.modules.mapper;

import com.enroll.modules.pojo.LengthsOfSchoolingEntity;

/**
 * @author hsc
 *
 * Aug 14, 2017
 */
public interface LengthsOfSchoolDao extends BaseDao<LengthsOfSchoolingEntity> {
	
	/**
	 * 根据主键查数据,给多对一用
     * @param id
     * @return
	 */
	public LengthsOfSchoolingEntity select(Integer id);
	
}
