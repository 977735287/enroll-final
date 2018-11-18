package com.enroll.modules.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enroll.modules.mapper.ArticleDao;
import com.enroll.modules.pojo.ArticleEntity;
import com.enroll.modules.service.ArticleService;

/**
 * @author hsc
 *
 * Sep 20, 2017
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleDao articleDao;

	@Override
	public void save(ArticleEntity articleEntity) {
		articleDao.save(articleEntity);
	}

	@Override
	public void update(ArticleEntity articleEntity) {
		articleDao.update(articleEntity);
	}

	@Override
	public void deleteBatch(Long[] ids) {
		articleDao.deleteBatch(ids);
	}

	@Override
	public List<ArticleEntity> queryList(Map<String, Object> map) {		
		return articleDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {		
		return articleDao.queryTotal(map);
	}

	@Override
	public ArticleEntity queryObject(Long id) {
		return articleDao.queryListById(id).get(0);
	}

}
