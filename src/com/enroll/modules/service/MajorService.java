package com.enroll.modules.service;

import java.util.List;
import java.util.Map;

import com.enroll.modules.pojo.MajorEntity;

/**
 * @author hsc
 *
 * Aug 15, 2017
 */
public interface MajorService {

	/**
	 * 保存专业
	 * @param majorEntity
	 */
	void save(MajorEntity majorEntity);
	
	/**
	 * 修改专业
	 * @param majorEntity
	 */
	void update(MajorEntity majorEntity);
	
	/**
	 * 删除专业
	 */
	void deleteBatch(Long[] majorIds);
	
	/**
	 * 查询专业列表
	 * @param map
	 * @return
	 */
	List<MajorEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 根据专业ID，查询专业
	 * @param id
	 * @return
	 */
	MajorEntity queryObject(Long id);
	
}
