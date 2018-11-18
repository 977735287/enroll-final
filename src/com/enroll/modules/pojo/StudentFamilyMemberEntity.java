package com.enroll.modules.pojo;

import java.io.Serializable;
/**
 * @author hsc
 *
 * Aug 14, 2017
 */
public class StudentFamilyMemberEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 家庭成员id
	 */
	private Long id;
	
	/**
	 * 家庭成员姓名
	 */
	private String name;
	
	/**
	 * 是否为该学生监护人
	 * 0:否    1:是
	 */
	private int guardian;
	
	/**
	 * 家庭成员电话
	 */
	private String phoneNum;
	
	/**
	 * 出生年月
	 */
	private String birthday;
	
	/**
	 * 证件类型
	 */
	private String certificatesType;
	
	/**
	 * 证件号码
	 */
	private String certificatesNum;
	
	/**
	 * 健康状况
	 * 0：健康或良好      1：一般或较弱     2：有慢性病     3：残疾
	 */
	private int memberHealthCondition;
	
	/**
	 * 学习或工作单位
	 */
	private String unit;
	
	/**
	 * 职务
	 */
	private String job;
	
	/**
	 * 民族
	 */
	private Nation memberNation;
	
	/**
	 * 政治面貌
	 */
	private PoliticalOutlook memberPoliticalOutlook;
	
	/**
	 * 与报名学生的关系
	 * 1父亲  2母亲  3继父/养父  4继母/养母  5祖父  6祖母  7外祖父  8外祖母  9兄  10姐  11其他  12非亲属
	 */
	private MemberRelationShip relationShip;
	
	/**
	 * 对应的学生信息
	 */
	private StuInfoEntity stuInfo;

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

	public int getGuardian() {
		return guardian;
	}

	public void setGuardian(int guardian) {
		this.guardian = guardian;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCertificatesType() {
		return certificatesType;
	}

	public void setCertificatesType(String certificatesType) {
		this.certificatesType = certificatesType;
	}

	public String getCertificatesNum() {
		return certificatesNum;
	}

	public void setCertificatesNum(String certificatesNum) {
		this.certificatesNum = certificatesNum;
	}

	public int getMemberHealthCondition() {
		return memberHealthCondition;
	}

	public void setMemberHealthCondition(int memberHealthCondition) {
		this.memberHealthCondition = memberHealthCondition;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Nation getMemberNation() {
		return memberNation;
	}

	public void setMemberNation(Nation memberNation) {
		this.memberNation = memberNation;
	}

	public PoliticalOutlook getMemberPoliticalOutlook() {
		return memberPoliticalOutlook;
	}

	public void setMemberPoliticalOutlook(PoliticalOutlook memberPoliticalOutlook) {
		this.memberPoliticalOutlook = memberPoliticalOutlook;
	}

	public MemberRelationShip getRelationShip() {
		return relationShip;
	}

	public void setRelationShip(MemberRelationShip relationShip) {
		this.relationShip = relationShip;
	}

	public StuInfoEntity getStuInfo() {
		return stuInfo;
	}

	public void setStuInfo(StuInfoEntity stuInfo) {
		this.stuInfo = stuInfo;
	}

}
