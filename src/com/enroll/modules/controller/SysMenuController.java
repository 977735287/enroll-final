package com.enroll.modules.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enroll.common.annotation.SysLog;
import com.enroll.common.exception.RRException;
import com.enroll.common.utils.Constant;
import com.enroll.common.utils.Constant.MenuType;
import com.enroll.common.utils.R;
import com.enroll.modules.pojo.SysMenuEntity;
import com.enroll.modules.service.ShiroService;
import com.enroll.modules.service.SysMenuService;

/**
 * @author hsc
 *
 * Jul 20, 2017
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@Autowired
	private ShiroService shiroService;
	
//	private Long userId;
	
	/**
	 * 所有菜单列表
	 */
	@RequestMapping("/list.do")
	@RequiresPermissions("sys:menu:list")
	public List<SysMenuEntity> list(){
		
		List<SysMenuEntity> menuList = null;
		
		if(getUserId() == Constant.SUPER_ADMIN){
			menuList = sysMenuService.queryList(new HashMap<String, Object>());
		}else{
//			menuList = sysMenuService.getUserMenuList(getUserId());
			menuList = sysMenuService.queryList(new HashMap<String, Object>());
		}
		
		return menuList;
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@RequestMapping("/select.do")
	@RequiresPermissions("sys:menu:select")
	public R select(){
		//查询列表数据
		List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();
		
		//添加顶级菜单
		SysMenuEntity root = new SysMenuEntity();
		root.setMenuId(0L);
		root.setName("一级菜单");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * 角色授权菜单
	 */
	@RequestMapping("/perms.do")
	@RequiresPermissions("sys:menu:perms")
	public R perms(){
		//查询列表数据
		List<SysMenuEntity> menuList = null;
		
		//只有超级管理员，才能查看所有管理员列表
		if(getUserId() == Constant.SUPER_ADMIN){
			menuList = sysMenuService.queryList(new HashMap<String, Object>());
		}else{
			menuList = sysMenuService.queryList(new HashMap<String, Object>());
//			menuList = sysMenuService.queryUserList(getUserId());
		}
		
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * 菜单信息
	 */
	@RequestMapping("/info.do")
	@RequiresPermissions("sys:menu:info")
	public R info(Long menuId){
		SysMenuEntity menu = sysMenuService.queryObject(menuId);
		return R.ok().put("menu", menu);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存菜单")
	@RequestMapping("/save.do")
	@RequiresPermissions("sys:menu:save")
	public R save(@RequestBody SysMenuEntity menu){
		
		//数据校验
		verifyForm(menu);
		
		sysMenuService.save(menu);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改菜单")
	@RequestMapping("/update.do")
	@RequiresPermissions("sys:menu:update")
	public R update(@RequestBody SysMenuEntity menu){
		
//		System.out.println(menu.toString());
		//数据校验
		verifyForm(menu);
				
		sysMenuService.update(menu);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除菜单")
	@RequestMapping("/delete.do")
	@RequiresPermissions("sys:menu:delete")
	public R delete(long menuId){
		if(menuId <= 30){
			return R.error("系统菜单，不能删除");
		}

		//判断是否有子菜单或按钮
		List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
		if(menuList.size() > 0){
			return R.error("请先删除子菜单或按钮");
		}

		sysMenuService.deleteBatch(new Long[]{menuId});
		
		return R.ok();
	}
	
	/**
	 * 用户菜单列表
	 */
	@RequestMapping("/user.do")
	public R user(){
		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
		Set<String> permissions = shiroService.getUserPermissions(getUserId());
		return R.ok().put("menuList", menuList).put("permissions", permissions);
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenuEntity menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new RRException("菜单名称不能为空");
		}
		
		if(menu.getParentId() == null){
			throw new RRException("上级菜单不能为空");
		}
		
		//菜单
		if(menu.getType() == MenuType.MENU.getValue()){
			if(StringUtils.isBlank(menu.getUrl())){
				throw new RRException("菜单URL不能为空");
			}
		}
		
		//上级菜单类型
		int parentType = MenuType.CATALOG.getValue();
		if(menu.getParentId() != 0){
			SysMenuEntity parentMenu = sysMenuService.queryObject(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//目录、菜单
		if(menu.getType() == MenuType.CATALOG.getValue() ||
				menu.getType() == MenuType.MENU.getValue()){
			if(parentType != MenuType.CATALOG.getValue()){
				throw new RRException("上级菜单只能为目录类型");
			}
			return ;
		}
		
		//按钮
		if(menu.getType() == MenuType.BUTTON.getValue()){
			if(parentType != MenuType.MENU.getValue()){
				throw new RRException("上级菜单只能为菜单类型");
			}
			return ;
		}
	}
	
}