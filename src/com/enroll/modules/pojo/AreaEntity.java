package com.enroll.modules.pojo;

import java.io.Serializable;
import java.util.Set;

/**
 * 行政区
 * 
 * @author hsc
 *
 * Aug 13, 2017
 */
public class AreaEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	/**
	 * 行政区划码
	 */
	private String code;
	
	/**
	 * 行政区名称
	 */
	private String name;
	
	/**
	 * 层级 
	 * 0：省    1：市    2：县/区
	 */
	private int level;
	
	/**
	 * 父行政区
	 */
	private AreaEntity parent;
	
	/**
	 * 子行政区
	 */
	private Set<AreaEntity> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public AreaEntity getParent() {
		return parent;
	}

	public void setParent(AreaEntity parent) {
		this.parent = parent;
	}

	public Set<AreaEntity> getChildren() {
		return children;
	}

	public void setChildren(Set<AreaEntity> children) {
		this.children = children;
	}

}
