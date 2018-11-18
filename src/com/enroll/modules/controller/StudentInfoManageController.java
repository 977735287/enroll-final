package com.enroll.modules.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enroll.common.utils.PageUtils;
import com.enroll.common.utils.Query;
import com.enroll.common.utils.R;
import com.enroll.modules.pojo.StuInfoEntity;
import com.enroll.modules.service.StudentInfoManageService;

/**
 * @author hsc
 *
 * Sep 15, 2017
 */
@RestController
@RequestMapping("/school/manage/info")
public class StudentInfoManageController extends AbstractController {
	
	@Autowired
	private StudentInfoManageService studentInfoManageService;

	/**
	 * 查询列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list.do")
	@RequiresPermissions("school:manage:info:list")
	public R list(@RequestParam Map<String, Object> params){
		
		Query query = new Query(params);
		List<StuInfoEntity> list = studentInfoManageService.queryList(query);
				
		int total = studentInfoManageService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 学生信息
	 */
	@RequestMapping("/info.do")
	@RequiresPermissions("school:manage:info:info")
	public R info(Long id){
		
		StuInfoEntity stuInfoEntity = studentInfoManageService.queryObject(id);
		
		return R.ok().put("stuInfoEntity", stuInfoEntity);
	}
	
	/**
	 * 删除学生信息
	 * @param params
	 * @return
	 */
	@RequestMapping("/delete.do")
	@RequiresPermissions("school:manage:info:delete")
	public R delete(@RequestBody Long[] ids, HttpServletRequest request){
		String url = request.getSession().getServletContext().getRealPath("/upload/images/");

		studentInfoManageService.deleteBatch(ids, url);
		
		return R.ok();
	}
	
}
