package com.enroll.modules.mapper;

import java.util.List;

import com.enroll.modules.pojo.SysMenuEntity;

/**
 * @author hsc
 *
 * Jul 20, 2017
 */
public interface SysMenuDao extends BaseDao<SysMenuEntity> {

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenuEntity> queryListParentId(Long parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> queryNotButtonList();
	
	/**
	 * 查询用户的权限列表
	 */
	List<SysMenuEntity> queryUserList(Long userId);
	
	int deleteRoleMenuBatch(Object[] id);
}
