package com.enroll.modules.mapper;

import java.util.List;

import com.enroll.modules.pojo.SysUserRoleEntity;

/**
 * 用户与角色对应关系
 *
 * @author hsc
 *
 * Jul 20, 2017
 */
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
}