package com.enroll.modules.controller;

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
import com.enroll.modules.pojo.MajorEntity;
import com.enroll.modules.service.MajorService;

/**
 * 专业
 * 
 * @author hsc
 *
 * Aug 15, 2017
 */
@RestController
@RequestMapping("/school/major")
public class MajorController extends AbstractController {

	@Autowired
	private MajorService majorService;
	
	/**
	 * 查询专业列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list.do")
	@RequiresPermissions("school:major:list")
	public R list(@RequestParam Map<String, Object> params){
		
		Query query = new Query(params);
		List<MajorEntity> majorList = majorService.queryList(query);
		
		int total = majorService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(majorList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/majorList.do")
	public R majorList(){
		
		List<MajorEntity> majorList = majorService.queryList(new HashMap<String, Object>());
		
		return R.ok().put("majorList", majorList);
	}
	
	/**
	 * 专业信息
	 */
	@RequestMapping("/info.do")
	@RequiresPermissions("school:major:info")
	public R info(Long id){
		
		MajorEntity majorEntity = majorService.queryObject(id);
		
		return R.ok().put("major", majorEntity);
	}
	
	/**
	 * 保存专业
	 * @param 
	 * @return
	 */
	@SysLog("保存专业")
	@RequestMapping("/save.do")
	@RequiresPermissions("school:major:save")
	public R save(@RequestBody MajorEntity majorEntity){
		
		majorService.save(majorEntity);
		
		return R.ok();
	}
	
	/**
	 * 修改专业
	 * @param params
	 * @return
	 */
	@SysLog("修改专业")
	@RequestMapping("/update.do")
	@RequiresPermissions("school:major:update")
	public R update(@RequestBody MajorEntity majorEntity){
		
		majorService.update(majorEntity);
		
		return R.ok();
	}
	
	/**
	 * 删除专业
	 * @param params
	 * @return
	 */
	@SysLog("删除专业")
	@RequestMapping("/delete.do")
	@RequiresPermissions("school:major:delete")
	public R delete(@RequestBody Long[] ids){
		
		majorService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
