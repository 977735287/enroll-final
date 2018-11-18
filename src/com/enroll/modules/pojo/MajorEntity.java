package com.enroll.modules.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 专业
 * 
 * @author hsc
 *
 * Aug 12, 2017
 */
public class MajorEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 专业ID
	 */
	private Long majorId;
	
	/**
	 * 专业代码
	 */
	private String majorCode;
	
	/**
	 * 专业名称
	 */
	private String majorName;
	
	/**
	 * 专业当前人数
	 */
	private int majorCurrentNum;
	
	/**
	 * 专业最大人数（所录取的最多的一次计数更新）
	 */
	private int majorMaxNum;
	
	/**
	 * 专业每班最大人数
	 */
	private int majorMax;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 专业学制时长
	 */
	private LengthsOfSchoolingEntity majorLength;
	
	/**
	 * 专业下的所有学生
	 */
	private Set<StuInfoEntity> majorStudents;

	public Long getMajorId() {
		return majorId;
	}

	public void setMajorId(Long majorId) {
		this.majorId = majorId;
	}

	public String getMajorCode() {
		return majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public int getMajorCurrentNum() {
		return majorCurrentNum;
	}

	public void setMajorCurrentNum(int majorCurrentNum) {
		this.majorCurrentNum = majorCurrentNum;
	}

	public int getMajorMaxNum() {
		return majorMaxNum;
	}

	public void setMajorMaxNum(int majorMaxNum) {
		this.majorMaxNum = majorMaxNum;
	}

	public int getMajorMax() {
		return majorMax;
	}

	public void setMajorMax(int majorMax) {
		this.majorMax = majorMax;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public LengthsOfSchoolingEntity getMajorLength() {
		return majorLength;
	}

	public void setMajorLength(LengthsOfSchoolingEntity majorLength) {
		this.majorLength = majorLength;
	}

	public Set<StuInfoEntity> getMajorStudents() {
		return majorStudents;
	}

	public void setMajorStudents(Set<StuInfoEntity> majorStudents) {
		this.majorStudents = majorStudents;
	}
	
}
