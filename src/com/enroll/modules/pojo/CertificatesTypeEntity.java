package com.enroll.modules.pojo;

/**
 * @author hsc
 *
 * Aug 22, 2017
 */
public class CertificatesTypeEntity {

	private Long id;
	
	/**
	 * 证件类型
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
