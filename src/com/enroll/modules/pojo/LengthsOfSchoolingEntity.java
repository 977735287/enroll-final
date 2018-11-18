package com.enroll.modules.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 学制实体
 * 
 * @author hsc
 *
 * Aug 14, 2017
 */
public class LengthsOfSchoolingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	/**
	 * 学制名称
	 */
	private String lengthName;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLengthName() {
		return lengthName;
	}

	public void setLengthName(String lengthName) {
		this.lengthName = lengthName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
