package com.enroll.modules.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enroll.common.utils.R;
import com.enroll.modules.pojo.SysUserEntity;
import com.enroll.modules.service.SysUserService;

/**
 * @author hsc
 *
 * Sep 16, 2017
 */
@RestController
@RequestMapping("/sys/register")
public class SysRegisterController extends AbstractController {
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 保存用户
	 */
	@RequestMapping("/save.do")
	public R save(@RequestBody SysUserEntity user){
//		ValidatorUtils.validateEntity(user, AddGroup.class);
		
		List<Long> roleIdList = new ArrayList<Long>();
		roleIdList.add(3L);
		
		user.setEnrollStatus(0);
//		user.setMessage("尚未通过报名，请报名");
		user.setStatus(1);
		user.setCreateUserId(1L);
		user.setRoleIdList(roleIdList);
		
		sysUserService.save(user);
		
		return R.ok();
	}
}
