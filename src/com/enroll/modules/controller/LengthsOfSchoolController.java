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
import com.enroll.modules.pojo.LengthsOfSchoolingEntity;
import com.enroll.modules.service.LengthsOfSchoolService;

/**
 * 专业学制
 * 
 * @author hsc
 *
 * Aug 14, 2017
 */
@RestController
@RequestMapping("/school/length")
public class LengthsOfSchoolController extends AbstractController {

	@Autowired
	private LengthsOfSchoolService lengthsOfSchoolService;
	
	/**
	 * 查询学制列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list.do")
	@RequiresPermissions("school:length:list")
	public R list(@RequestParam Map<String, Object> params){
		
		Query query = new Query(params);
		List<LengthsOfSchoolingEntity> lengthList = lengthsOfSchoolService.queryList(query);
		int total = lengthsOfSchoolService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(lengthList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 学制列表
	 */
	@RequestMapping("/select.do")
	@RequiresPermissions("school:length:select")
	public R select(){
		Map<String, Object> map = new HashMap<>();

		List<LengthsOfSchoolingEntity> list = lengthsOfSchoolService.queryList(map);
		
		return R.ok().put("list", list);
	}
	
	/**
	 * 学制信息
	 */
	@RequestMapping("/info.do")
	@RequiresPermissions("school:length:info")
	public R info(Long id){
		
		LengthsOfSchoolingEntity lengthsOfSchoolingEntity = lengthsOfSchoolService.queryObject(id);
		
		return R.ok().put("length", lengthsOfSchoolingEntity);
	}
	
	/**
	 * 保存学制
	 * @param 
	 * @return
	 */
	@SysLog("保存学制")
	@RequestMapping("/save.do")
	@RequiresPermissions("school:length:save")
	public R save(@RequestBody LengthsOfSchoolingEntity lengthsOfSchoolingEntity){
		
		lengthsOfSchoolService.save(lengthsOfSchoolingEntity);
		
		return R.ok();
	}
	
	/**
	 * 修改学制
	 * @param params
	 * @return
	 */
	@SysLog("修改学制")
	@RequestMapping("/update.do")
	@RequiresPermissions("school:length:update")
	public R update(@RequestBody LengthsOfSchoolingEntity lengthsOfSchoolingEntity){
		
		lengthsOfSchoolService.update(lengthsOfSchoolingEntity);
		
		return R.ok();
	}
	
	/**
	 * 删除学制
	 * @param params
	 * @return
	 */
	@SysLog("删除学制")
	@RequestMapping("/delete.do")
	@RequiresPermissions("school:length:delete")
	public R delete(@RequestBody Long[] ids){
		
		lengthsOfSchoolService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
