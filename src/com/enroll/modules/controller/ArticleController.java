package com.enroll.modules.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enroll.common.annotation.SysLog;
import com.enroll.common.utils.PageUtils;
import com.enroll.common.utils.Query;
import com.enroll.common.utils.R;
import com.enroll.modules.pojo.ArticleEntity;
import com.enroll.modules.service.ArticleService;

/**
 * 公告
 * 
 * @author hsc
 *
 * Sep 20, 2017
 */
@RestController
@RequestMapping("/school/article")
public class ArticleController extends AbstractController {
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 查询公告列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list.do")
//	@RequiresPermissions("school:article:list")
	public R list(@RequestParam Map<String, Object> params){
		
		Query query = new Query(params);
		List<ArticleEntity> articleList = articleService.queryList(query);

		int total = articleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(articleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 查询公告列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/artList.do")
	public R artList(){
		
		R r = new R();
		
		List<ArticleEntity> articleList = articleService.queryList(new HashMap<String, Object>());
		r.put("articleList", articleList);
		
		List<ArticleEntity> noticeList = new ArrayList<ArticleEntity>();
		List<ArticleEntity> policyList = new ArrayList<ArticleEntity>();
		
		if(articleList != null && !articleList.isEmpty()){
			for(ArticleEntity a : articleList){
				if(a.getArtType() == 0){
					policyList.add(a);
				}else if(a.getArtType() == 1){
					noticeList.add(a);
				}
			}
		}
		r.put("noticeList", noticeList);
		r.put("policyList", policyList);
				
		return r;
	}
	
	/**
	 * 公告信息
	 */
	@RequestMapping("/info.do")
	public R info(Long id){
		
		ArticleEntity articleEntity = articleService.queryObject(id);
		
		return R.ok().put("article", articleEntity);
	}
	
	/**
	 * 保存公告
	 * @param articleEntity
	 * @return
	 */
	@SysLog("保存公告")
	@RequestMapping("/save.do")
	@RequiresPermissions("school:article:save")
	public R save(@RequestBody ArticleEntity articleEntity){
		
		articleEntity.setArtAuthor(getUser());
		articleService.save(articleEntity);
		
		return R.ok();
	}
	
	/**
	 * 修改公告
	 * @param articleEntity
	 * @return
	 */
	@SysLog("修改公告")
	@RequestMapping("/update.do")
	@RequiresPermissions("school:article:update")
	public R update(@RequestBody ArticleEntity articleEntity){
		
		articleService.update(articleEntity);
		
		return R.ok();
	}
	
	/**
	 * 删除公告
	 * @param params
	 * @return
	 */
	@SysLog("删除公告")
	@RequestMapping("/delete.do")
	@RequiresPermissions("school:article:delete")
	public R delete(@RequestBody Long[] ids){
		
		articleService.deleteBatch(ids);
		
		return R.ok();
	}

}
