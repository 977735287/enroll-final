package com.enroll.modules.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enroll.common.utils.R;
import com.enroll.modules.pojo.SysMainInfo;
import com.enroll.modules.service.MainInfoService;

/**
 * @author hsc
 *
 * Feb 3, 2018
 */
@RestController
@RequestMapping("/sys/main")
public class SysMainInfoController {

	@Autowired
	private MainInfoService mainInfoService;
	
	/**
	 * 保存
	 */
	@RequestMapping("/save.do")
	public R save(@RequestBody SysMainInfo sysMainInfo) {
		
		if(sysMainInfo.getMessage() != null)
			mainInfoService.save(sysMainInfo);
		return R.ok();
	}
	
	/**
	 * 更新
	 */
	@RequestMapping("/update.do")
	public R update(@RequestBody SysMainInfo sysMainInfo) {
		
		if(sysMainInfo.getId() != null)
			mainInfoService.update(sysMainInfo);
		return R.ok();
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info.do")
	public R info(Long id) {
		SysMainInfo sysMainInfo = mainInfoService.queryObject(id);
		return R.ok().put("sysMainInfo", sysMainInfo);
	}
}
