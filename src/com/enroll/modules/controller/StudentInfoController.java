package com.enroll.modules.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enroll.common.utils.ClassUtils;
import com.enroll.common.utils.PageUtils;
import com.enroll.common.utils.Query;
import com.enroll.common.utils.R;
import com.enroll.modules.pojo.ClassEntity;
import com.enroll.modules.pojo.StuInfoEntity;
import com.enroll.modules.pojo.SysUserEntity;
import com.enroll.modules.service.ClassService;
import com.enroll.modules.service.MajorService;
import com.enroll.modules.service.StudentEnrollService;
import com.enroll.modules.service.StudentInfoService;
import com.enroll.modules.service.SysUserService;
/**
 * 报名信息处理
 * 
 * @author hsc
 *
 * Sep 13, 2017
 */
@RestController
@RequestMapping("/school/info")
public class StudentInfoController extends AbstractController {

	@Autowired
	private StudentInfoService studentInfoService;
	
	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private StudentEnrollService studentEnrollService;

	@Autowired
	private MajorService majorService;

	@Autowired
	private ClassService classService;
	
	/**
	 * 查询学生信息列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list.do")
	@RequiresPermissions("school:info:list")
	public R list(@RequestParam Map<String, Object> params){
		
		Query query = new Query(params);
		List<StuInfoEntity> list = studentInfoService.queryList(query);
				
		int total = studentInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 学生信息
	 */
	@RequestMapping("/info.do")
	@RequiresPermissions("school:info:info")
	public R info(Long id){
		
		StuInfoEntity stuInfoEntity = studentInfoService.queryObject(id);

		return R.ok().put("stuInfoEntity", stuInfoEntity);
	}
	
	/**
	 * 已审核学生信息并录取
	 */
	@RequestMapping("/pass.do")
	@RequiresPermissions("school:info:pass")
	public R pass(@RequestBody SysUserEntity sysUserEntity){
		
		StuInfoEntity stuInfoEntity = studentEnrollService.queryByUserId(sysUserEntity.getUserId());
		if(stuInfoEntity != null){
			String stuNum = ClassUtils.getStuNum(stuInfoEntity);
			StuInfoEntity stuInfo = new StuInfoEntity();
			
			if(ClassUtils.checkClass(stuInfoEntity)){
				ClassEntity classEntity = new ClassEntity();
				classEntity.setClassName(ClassUtils.getClassName(stuInfoEntity));
				classEntity.setClassCode(ClassUtils.getClassCode(stuInfoEntity));
				classEntity.setClassMenberNum(ClassUtils.getClassCurrentNum(stuInfoEntity));
				classEntity.setClassMaxNum(stuInfoEntity.getStuMajor().getMajorMax());
				classEntity.setClassCreateTime(new Date());
				classEntity.setClassMajor(stuInfoEntity.getStuMajor());
				classService.save(classEntity);
				ClassEntity ce = classService.queryObjectByCode(classEntity.getClassCode());
				stuInfo.setStuClass(ce);
			}else{
				ClassEntity ce = classService.queryObjectByCode(ClassUtils.getClassCode(stuInfoEntity));
				ce.setClassMenberNum(ce.getClassMenberNum() + 1);
				classService.update(ce);
				stuInfo.setStuClass(ce);
			}
			stuInfoEntity.getStuMajor().setMajorMaxNum(stuInfoEntity.getStuMajor().getMajorMaxNum() + 1);
			stuInfoEntity.getStuMajor().setMajorCurrentNum(stuInfoEntity.getStuMajor().getMajorCurrentNum() + 1);
			stuInfo.setId(stuInfoEntity.getId());
			stuInfo.setStuNum(stuNum);
			majorService.update(stuInfoEntity.getStuMajor());
			studentEnrollService.update(stuInfo);
		}
		
		sysUserEntity.setEnrollStatus(2);
		
		sysUserService.update(sysUserEntity);
		
		return R.ok();
	}
	
	/**
	 * 已审核学生信息未录取
	 */
	@RequestMapping("/nopass.do")
	@RequiresPermissions("school:info:nopass")
	public R nopass(@RequestBody SysUserEntity sysUserEntity){
		
		StuInfoEntity stuInfoEntity = studentEnrollService.queryByUserId(sysUserEntity.getUserId());
		if(stuInfoEntity != null){
			if(sysUserEntity.getEnrollStatus() == 2){
				StuInfoEntity stuInfo = new StuInfoEntity();
				stuInfo.setId(stuInfoEntity.getId());
				stuInfo.setStuNum(null);
				stuInfo.setStuClass(null);
				studentEnrollService.update(stuInfo);
				stuInfoEntity.getStuMajor().setMajorCurrentNum(stuInfoEntity.getStuMajor().getMajorCurrentNum() - 1);
				majorService.update(stuInfoEntity.getStuMajor());
				ClassEntity ce = classService.queryObject(stuInfoEntity.getStuClass().getId());
				ce.setClassMenberNum(ce.getClassMenberNum() - 1);
				classService.update(ce);
			}
		}
		
		sysUserEntity.setEnrollStatus(3);
		
		sysUserService.update(sysUserEntity);
		
		return R.ok();
	}
	
}
