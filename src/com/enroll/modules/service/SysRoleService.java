package com.enroll.modules.service;

import java.util.List;
import java.util.Map;

import com.enroll.modules.pojo.SysRoleEntity;

/**
 * 角色
 *
 * @author hsc
 *
 * Jul 20, 2017
 */
public interface SysRoleService {
	
	SysRoleEntity queryObject(Long roleId);
	
	List<SysRoleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRoleEntity role);
	
	void update(SysRoleEntity role);
	
	void deleteBatch(Long[] roleIds);
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);

}
