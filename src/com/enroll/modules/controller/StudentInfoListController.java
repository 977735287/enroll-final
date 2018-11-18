package com.enroll.modules.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enroll.common.utils.ExcelUtils;
import com.enroll.common.utils.PageUtils;
import com.enroll.common.utils.Query;
import com.enroll.common.utils.R;
import com.enroll.modules.pojo.StuInfoEntity;
import com.enroll.modules.service.StudentInfoListService;
import com.enroll.modules.service.StudentInfoManageService;

/**
 * @author hsc
 *
 * Mar 20, 2018
 */
@RestController
@RequestMapping("/school/list/info")
public class StudentInfoListController extends AbstractController {

	@Autowired
	private StudentInfoManageService studentInfoManageService;
	
	@Autowired
	private StudentInfoListService studentInfoListService;
	
	/**
	 * 查询列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list.do")
	@RequiresPermissions("school:list:info:list")
	public R list(@RequestParam Map<String, Object> params){
		
		Query query = new Query(params);
		List<StuInfoEntity> list = studentInfoListService.queryList(query);
		
		setStuInfoList(studentInfoListService.queryList(params));
				
		int total = studentInfoListService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@RequestMapping("/stuList.do")
	public R stuList(String startTime, String endTime){
		
		Map<String, Object> map= new HashMap<String, Object>();
		if(startTime != null && endTime != null){
			map.put("startTime", startTime);
			map.put("endTime", endTime);
		}
		
		List<StuInfoEntity> list = studentInfoListService.queryList(map);
		
		return R.ok().put("stuList", list);
	}
	
	/**
	 * 学生信息
	 */
	@RequestMapping("/info.do")
	@RequiresPermissions("school:list:info:info")
	public R info(Long id){
		
		StuInfoEntity stuInfoEntity = studentInfoManageService.queryObject(id);
		
		return R.ok().put("stuInfoEntity", stuInfoEntity);
	}
	
	/**
	 * 导出学生信息
	 */
	@RequestMapping("/export.do")
	@RequiresPermissions("school:list:info:export")
	public R export(HttpServletResponse response){
		
		List<StuInfoEntity> list = getStuInfoList();
		try {
			ExcelUtils.exportExcel(list, "学生信息-1", "学生信息表", response);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("导出学生信息出错！");
		}
		
		return R.ok();
	}
}
