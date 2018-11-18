package com.enroll.modules.pojo;

import java.io.Serializable;

/**
 * 首页招生信息展示
 * 
 * @author hsc
 *
 * Feb 3, 2018
 */
public class SysMainInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String message;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
