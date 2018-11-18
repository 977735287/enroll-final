package com.enroll.modules.service;

import java.util.List;
import java.util.Map;

import com.enroll.modules.pojo.ArticleEntity;

/**
 * @author hsc
 *
 * Sep 20, 2017
 */
public interface ArticleService {

	/**
	 * 保存公告
	 * @param majorEntity
	 */
	void save(ArticleEntity articleEntity);
	
	/**
	 * 修改公告
	 * @param majorEntity
	 */
	void update(ArticleEntity articleEntity);
	
	/**
	 * 删除公告
	 */
	void deleteBatch(Long[] ids);
	
	/**
	 * 查询公告列表
	 * @param map
	 * @return
	 */
	List<ArticleEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 根据公告ID，查询公告
	 * @param id
	 * @return
	 */
	ArticleEntity queryObject(Long id);
}
