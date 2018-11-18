package com.enroll.modules.mapper;

import com.enroll.modules.pojo.SysUserTokenEntity;

/**
 * @author hsc
 *
 * Jul 19, 2017
 */
public interface SysUserTokenDao extends BaseDao<SysUserTokenEntity>{
	
    SysUserTokenEntity queryByUserId(Long userId);

    SysUserTokenEntity queryByToken(String token);

}
