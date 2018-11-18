package com.enroll.modules.pojo;

/**
 * @author hsc
 *
 * Aug 14, 2017
 */
public class MemberRelationShip {

	private Long id;
	
	/**
	 * 与学生的关系
	 */
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
