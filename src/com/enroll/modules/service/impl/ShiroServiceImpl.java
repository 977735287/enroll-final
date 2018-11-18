package com.enroll.modules.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enroll.common.utils.Constant;
import com.enroll.modules.mapper.SysMenuDao;
import com.enroll.modules.mapper.SysUserDao;
import com.enroll.modules.mapper.SysUserTokenDao;
import com.enroll.modules.pojo.SysMenuEntity;
import com.enroll.modules.pojo.SysUserEntity;
import com.enroll.modules.pojo.SysUserTokenEntity;
import com.enroll.modules.service.ShiroService;

/**
 * @author hsc
 *
 * Jul 20, 2017
 */
@Service
public class ShiroServiceImpl implements ShiroService {
    
	@Autowired
    private SysMenuDao sysMenuDao;
    
	@Autowired
    private SysUserDao sysUserDao;
    
	@Autowired
    private SysUserTokenDao sysUserTokenDao;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            List<SysMenuEntity> menuList = sysMenuDao.queryList(new HashMap<String, Object>());
            permsList = new ArrayList<>(menuList.size());
            for(SysMenuEntity menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            permsList = sysUserDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        return sysUserTokenDao.queryByToken(token);
    }

    @Override
    public SysUserEntity queryUser(Long userId) {
        return sysUserDao.queryObject(userId);
    }
}
