package com.enroll.modules.service;

import com.enroll.common.utils.R;
import com.enroll.modules.pojo.SysUserTokenEntity;

/**
 * @author hsc
 *
 * Jul 19, 2017
 */
public interface SysUserTokenService {
	
	SysUserTokenEntity queryByUserId(Long userId);

	SysUserTokenEntity queryByToken(String token);
	
	void save(SysUserTokenEntity token);
	
	int update(SysUserTokenEntity token);
	
	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);

}
