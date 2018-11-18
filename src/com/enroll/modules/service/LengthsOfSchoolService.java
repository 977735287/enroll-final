package com.enroll.modules.service;

import java.util.List;
import java.util.Map;

import com.enroll.modules.pojo.LengthsOfSchoolingEntity;

/**
 * @author hsc
 *
 * Aug 14, 2017
 */
public interface LengthsOfSchoolService {

	/**
	 * 保存学制
	 * @param lengthsOfSchoolingEntity
	 */
	void save(LengthsOfSchoolingEntity lengthsOfSchoolingEntity);
	
	/**
	 * 修改学制
	 * @param lengthsOfSchoolingEntity
	 */
	void update(LengthsOfSchoolingEntity lengthsOfSchoolingEntity);
	
	/**
	 * 删除学制
	 */
	void deleteBatch(Long[] ids);
	
	/**
	 * 查询学制列表
	 * @param map
	 * @return
	 */
	List<LengthsOfSchoolingEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 根据学制ID，查询学制
	 * @param id
	 * @return
	 */
	LengthsOfSchoolingEntity queryObject(Long id);
	
}
