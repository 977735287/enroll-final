package com.enroll.modules.mapper;

import java.util.List;
import java.util.Map;

import com.enroll.modules.pojo.SysUserEntity;

/**
 * @author hsc
 *
 * Jul 19, 2017
 */
public interface SysUserDao extends BaseDao<SysUserEntity> {

	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);
	
	/**
	 * 根据用户邮箱，查询系统用户
	 */
	SysUserEntity queryByEmail(String email);
	
	/**
	 * 根据用户手机，查询系统用户
	 */
	SysUserEntity queryByPhone(String phone);
	
	/**
	 * 修改密码
	 */
	int updatePassword(Map<String, Object> map);
	
	/**
	 * 根据主键查数据,给多对一用
	 */
	SysUserEntity select(Integer id);
	
	int deleteRoleBatch(Object[] id);
}
