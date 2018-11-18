package com.enroll.modules.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 班级
 * 
 * @author hsc
 *
 * Feb 17, 2018
 */
public class ClassEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	/**
	 * 班级名称
	 */
	private String className;
	
	/**
	 * 班级编号
	 */
	private String classCode;
	
	/**
	 * 班级现有人数
	 */
	private int classMenberNum;
	
	/**
	 * 班级最大人数
	 */
	private int classMaxNum;
	
	/**
	 * 班级创建时间
	 */
	private Date classCreateTime;
	
	/**
	 * 班级所属专业
	 */
	private MajorEntity classMajor;

	/**
	 * 班级下的学生信息列表
	 */
	private List<StuInfoEntity> classStuInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public int getClassMenberNum() {
		return classMenberNum;
	}

	public void setClassMenberNum(int classMenberNum) {
		this.classMenberNum = classMenberNum;
	}

	public int getClassMaxNum() {
		return classMaxNum;
	}

	public void setClassMaxNum(int classMaxNum) {
		this.classMaxNum = classMaxNum;
	}

	public Date getClassCreateTime() {
		return classCreateTime;
	}

	public void setClassCreateTime(Date classCreateTime) {
		this.classCreateTime = classCreateTime;
	}

	public MajorEntity getClassMajor() {
		return classMajor;
	}

	public void setClassMajor(MajorEntity classMajor) {
		this.classMajor = classMajor;
	}

	public List<StuInfoEntity> getClassStuInfo() {
		return classStuInfo;
	}

	public void setClassStuInfo(List<StuInfoEntity> classStuInfo) {
		this.classStuInfo = classStuInfo;
	}

}
