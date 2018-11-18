package com.enroll.modules.service;

import java.util.Set;

import com.enroll.modules.pojo.SysUserEntity;
import com.enroll.modules.pojo.SysUserTokenEntity;


/**
 * @author hsc
 *
 * Jul 20, 2017
 */
public interface ShiroService {

	/**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUserEntity queryUser(Long userId);
}
