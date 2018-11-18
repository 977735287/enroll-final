package com.enroll.modules.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.enroll.common.annotation.SysLog;
import com.enroll.common.utils.PageUtils;
import com.enroll.common.utils.Query;
import com.enroll.common.utils.R;
//import com.enroll.common.validator.ValidatorUtils;
import com.enroll.modules.pojo.SysRoleEntity;
import com.enroll.modules.service.SysRoleMenuService;
import com.enroll.modules.service.SysRoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * 
 * @author hsc
 *
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	/**
	 * 角色列表
	 */
	@RequestMapping("/list.do")
	@RequiresPermissions("sys:role:list")
	public R list(@RequestParam Map<String, Object> params){
		//如果不是超级管理员，则只查询自己创建的角色列表
//		if(getUserId() != Constant.SUPER_ADMIN){
//			params.put("createUserId", getUserId());
//		}
		
		//查询列表数据
		Query query = new Query(params);
		List<SysRoleEntity> list = sysRoleService.queryList(query);
		int total = sysRoleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 角色列表
	 */
	@RequestMapping("/select.do")
	@RequiresPermissions("sys:role:select")
	public R select(){
		Map<String, Object> map = new HashMap<>();
		
		//如果不是超级管理员，则只查询自己所拥有的角色列表
//		if(getUserId() != Constant.SUPER_ADMIN){
//			map.put("createUserId", getUserId());
//		}
		List<SysRoleEntity> list = sysRoleService.queryList(map);		
		
		return R.ok().put("list", list);
	}
	
	/**
	 * 角色信息
	 */
	@RequestMapping("/info.do")
	@RequiresPermissions("sys:role:info")
	public R info(Long roleId){
		SysRoleEntity role = sysRoleService.queryObject(roleId);
		
		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);
		
		return R.ok().put("role", role);
	}
	
	/**
	 * 保存角色
	 */
	@SysLog("保存角色")
	@RequestMapping("/save.do")
	@RequiresPermissions("sys:role:save")
	public R save(@RequestBody SysRoleEntity role){
//		ValidatorUtils.validateEntity(role);
		role.setCreateUserId(getUserId());
		sysRoleService.save(role);
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
		
		return R.ok();
	}
	
	/**
	 * 修改角色
	 */
	@SysLog("修改角色")
	@RequestMapping("/update.do")
	@RequiresPermissions("sys:role:update")
	public R update(@RequestBody SysRoleEntity role){
//		ValidatorUtils.validateEntity(role);		
		role.setCreateUserId(getUserId());
		sysRoleService.update(role);
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
		
		return R.ok();
	}
	
	/**
	 * 删除角色
	 */
	@SysLog("删除角色")
	@RequestMapping("/delete.do")
	@RequiresPermissions("sys:role:delete")
	public R delete(@RequestBody Long[] roleIds){
		sysRoleService.deleteBatch(roleIds);
		
		return R.ok();
	}
}
