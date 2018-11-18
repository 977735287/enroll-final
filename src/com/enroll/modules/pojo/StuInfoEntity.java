package com.enroll.modules.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生信息
 * 
 * @author hsc
 *
 *         Aug 12, 2017
 */
public class StuInfoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 学生信息ID
	 */
	private Long id;

	/**
	 * 学生姓名
	 */
	private String stuName;

	/**
	 * 性别 0:男 1:女
	 */
	private int stuSex;

	/**
	 * 出生日期 由证件生成
	 */
	private Date stuBirthday;

	/**
	 * 证件类型
	 */
	private CertificatesTypeEntity stuCertificatesType;

	/**
	 * 证件号码
	 */
	private String stuCertificatesNum;

	/**
	 * 专业
	 */
	private MajorEntity stuMajor;

	/**
	 * 专业方向(选填)
	 */
	private String stuMajorDirection;

	/**
	 * 学制
	 */
	private LengthsOfSchoolingEntity stuLength;

	/**
	 * 入学年月 格式yyyy-dd
	 */
	private Date stuEntranceTime;

	/**
	 * 学生学号
	 */
	private String stuNum;

	/**
	 * 学生姓名拼音
	 */
	private String stuPinyinName;

	/**
	 * 学生英文姓名(选填)
	 */
	private String stuEnglishName;

	/**
	 * 班级名称
	 */
	private String stuClassName;

	/**
	 * 学生类别
	 */
	private StudentType stuType;

	/**
	 * 学习形式 是否全日制 0：是 1：否
	 */
	private int stuIsAllDay;

	/**
	 * 入学方式
	 */
	private StudentEntranceWay stuEntranceWay;

	/**
	 * 就读方式 0：走读 1：住校
	 */
	private int stuStudyWay;

	/**
	 * 港澳台侨外
	 */
	private StudentHKMTOC stuHKMTOC;

	/**
	 * 民族
	 */
	private Nation stuNation;

	/**
	 * 政治面貌
	 */
	private PoliticalOutlook stuPoliticalOutlook;

	/**
	 * 健康状况 0：健康良好 1：一般较弱 2：有慢性病 3：残疾
	 */
	private int stuHealthCondition;

	/**
	 * 婚姻状况 0：是 1：否
	 */
	private int stuIsMarried;

	/**
	 * 生源地行政区划码
	 */
	private AreaEntity stuArea;

	/**
	 * 籍贯行政区划码
	 */
	private AreaEntity stuNationArea;

	/**
	 * 出生地行政区划码
	 */
	private AreaEntity stuBirthArea;

	/**
	 * 户口性质 0：农业户口 1：非农业户口
	 */
	private int stuHouseHoldType;

	/**
	 * 学生居住地类型 0：农村 1：乡镇 2：县城 3：城市
	 */
	private int stuDomicile;

	/**
	 * 户口所在地行政区划码
	 */
	private AreaEntity stuHouseArea;

	/**
	 * 户口所在地区县以下详细地址
	 */
	private String stuDetailAdress;

	/**
	 * 联系电话
	 */
	private String stuPhoneNum;

	/**
	 * 所属派出所名称
	 */
	private String stuPoliceStation;

	/**
	 * 火车区间 A-B格式
	 */
	private String stuTrainSection;

	/**
	 * 学生来源 -是否应届 0：是 1：否
	 */
	private int stuIsCurrent;

	/**
	 * 招生对象
	 */
	private StudentEnrolObject stuEnrolObject;

	/**
	 * 教学点(选填)
	 */
	private String stuTeachingPoint;

	/**
	 * 是否随迁子女 0：是 1：否
	 */
	private int stuIsSuiqian;

	/**
	 * 是否跨省招生(选填) 0：是 1：否
	 */
	private int stuIsTransProvincial;

	/**
	 * 招生方式 0：统一招生 1：自主招生
	 */
	private int stuEnrolWay;

	/**
	 * 准考证号(选填)
	 */
	private String stuZKH;

	/**
	 * 考生号(选填)
	 */
	private String stuExamineeNumber;

	/**
	 * 考试总分(选填)
	 */
	private String stuTotalScore;

	/**
	 * 联招合作类型
	 */
	private StudentEnrolType stuEnrolType;

	/**
	 * 联招合作办学形式(联招合作类型输入非联合办学为可选项否则为必填项)
	 */
	private String stuSchoolingType;

	/**
	 * 联招合作学校代码(联招合作类型输入非联合办学为可选项否则为必填项)
	 */
	private String stuSchoolCode;

	/**
	 * 分段培养方式(选填)
	 */
	private String stuSubsectionCulture;

	/**
	 * 电子信箱/其他联系方式(选填)
	 */
	private String stuOtherContact;

	/**
	 * 家庭电话(选填)
	 */
	private String stuHomeTel;

	/**
	 * 家庭现地址(选填)
	 */
	private String stuHomeAddrNow;

	/**
	 * 家庭邮政编码(选填)
	 */
	private String stuPostalCode;

	/**
	 * 国籍
	 */
	private StudentNationality stuNationality;

	/**
	 * 家庭成员1
	 */
	private StudentFamilyMemberEntity stuFamilyMember1;

	/**
	 * 家庭成员2
	 */
	private StudentFamilyMemberEntity stuFamilyMember2;

	/**
	 * 文件上传
	 */
	private UploadFileEntity stuUploadFile;

	/**
	 * 学生所在班级
	 */
	private ClassEntity stuClass;

	/**
	 * 学生用户
	 */
	private SysUserEntity user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public int getStuSex() {
		return stuSex;
	}

	public void setStuSex(int stuSex) {
		this.stuSex = stuSex;
	}

	public Date getStuBirthday() {
		return stuBirthday;
	}

	public void setStuBirthday(Date stuBirthday) {
		this.stuBirthday = stuBirthday;
	}

	public CertificatesTypeEntity getStuCertificatesType() {
		return stuCertificatesType;
	}

	public void setStuCertificatesType(CertificatesTypeEntity stuCertificatesType) {
		this.stuCertificatesType = stuCertificatesType;
	}

	public String getStuCertificatesNum() {
		return stuCertificatesNum;
	}

	public void setStuCertificatesNum(String stuCertificatesNum) {
		this.stuCertificatesNum = stuCertificatesNum;
	}

	public MajorEntity getStuMajor() {
		return stuMajor;
	}

	public void setStuMajor(MajorEntity stuMajor) {
		this.stuMajor = stuMajor;
	}

	public String getStuMajorDirection() {
		return stuMajorDirection;
	}

	public void setStuMajorDirection(String stuMajorDirection) {
		this.stuMajorDirection = stuMajorDirection;
	}

	public LengthsOfSchoolingEntity getStuLength() {
		return stuLength;
	}

	public void setStuLength(LengthsOfSchoolingEntity stuLength) {
		this.stuLength = stuLength;
	}

	public Date getStuEntranceTime() {
		return stuEntranceTime;
	}

	public void setStuEntranceTime(Date stuEntranceTime) {
		this.stuEntranceTime = stuEntranceTime;
	}

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	public String getStuPinyinName() {
		return stuPinyinName;
	}

	public void setStuPinyinName(String stuPinyinName) {
		this.stuPinyinName = stuPinyinName;
	}

	public String getStuEnglishName() {
		return stuEnglishName;
	}

	public void setStuEnglishName(String stuEnglishName) {
		this.stuEnglishName = stuEnglishName;
	}

	public String getStuClassName() {
		return stuClassName;
	}

	public void setStuClassName(String stuClassName) {
		this.stuClassName = stuClassName;
	}

	public StudentType getStuType() {
		return stuType;
	}

	public void setStuType(StudentType stuType) {
		this.stuType = stuType;
	}

	public int getStuIsAllDay() {
		return stuIsAllDay;
	}

	public void setStuIsAllDay(int stuIsAllDay) {
		this.stuIsAllDay = stuIsAllDay;
	}

	public StudentEntranceWay getStuEntranceWay() {
		return stuEntranceWay;
	}

	public void setStuEntranceWay(StudentEntranceWay stuEntranceWay) {
		this.stuEntranceWay = stuEntranceWay;
	}

	public int getStuStudyWay() {
		return stuStudyWay;
	}

	public void setStuStudyWay(int stuStudyWay) {
		this.stuStudyWay = stuStudyWay;
	}

	public StudentHKMTOC getStuHKMTOC() {
		return stuHKMTOC;
	}

	public void setStuHKMTOC(StudentHKMTOC stuHKMTOC) {
		this.stuHKMTOC = stuHKMTOC;
	}

	public Nation getStuNation() {
		return stuNation;
	}

	public void setStuNation(Nation stuNation) {
		this.stuNation = stuNation;
	}

	public PoliticalOutlook getStuPoliticalOutlook() {
		return stuPoliticalOutlook;
	}

	public void setStuPoliticalOutlook(PoliticalOutlook stuPoliticalOutlook) {
		this.stuPoliticalOutlook = stuPoliticalOutlook;
	}

	public int getStuHealthCondition() {
		return stuHealthCondition;
	}

	public void setStuHealthCondition(int stuHealthCondition) {
		this.stuHealthCondition = stuHealthCondition;
	}

	public int getStuIsMarried() {
		return stuIsMarried;
	}

	public void setStuIsMarried(int stuIsMarried) {
		this.stuIsMarried = stuIsMarried;
	}

	public AreaEntity getStuArea() {
		return stuArea;
	}

	public void setStuArea(AreaEntity stuArea) {
		this.stuArea = stuArea;
	}

	public AreaEntity getStuNationArea() {
		return stuNationArea;
	}

	public void setStuNationArea(AreaEntity stuNationArea) {
		this.stuNationArea = stuNationArea;
	}

	public AreaEntity getStuBirthArea() {
		return stuBirthArea;
	}

	public void setStuBirthArea(AreaEntity stuBirthArea) {
		this.stuBirthArea = stuBirthArea;
	}

	public int getStuHouseHoldType() {
		return stuHouseHoldType;
	}

	public void setStuHouseHoldType(int stuHouseHoldType) {
		this.stuHouseHoldType = stuHouseHoldType;
	}

	public int getStuDomicile() {
		return stuDomicile;
	}

	public void setStuDomicile(int stuDomicile) {
		this.stuDomicile = stuDomicile;
	}

	public AreaEntity getStuHouseArea() {
		return stuHouseArea;
	}

	public void setStuHouseArea(AreaEntity stuHouseArea) {
		this.stuHouseArea = stuHouseArea;
	}

	public String getStuDetailAdress() {
		return stuDetailAdress;
	}

	public void setStuDetailAdress(String stuDetailAdress) {
		this.stuDetailAdress = stuDetailAdress;
	}

	public String getStuPhoneNum() {
		return stuPhoneNum;
	}

	public void setStuPhoneNum(String stuPhoneNum) {
		this.stuPhoneNum = stuPhoneNum;
	}

	public String getStuPoliceStation() {
		return stuPoliceStation;
	}

	public void setStuPoliceStation(String stuPoliceStation) {
		this.stuPoliceStation = stuPoliceStation;
	}

	public String getStuTrainSection() {
		return stuTrainSection;
	}

	public void setStuTrainSection(String stuTrainSection) {
		this.stuTrainSection = stuTrainSection;
	}

	public int getStuIsCurrent() {
		return stuIsCurrent;
	}

	public void setStuIsCurrent(int stuIsCurrent) {
		this.stuIsCurrent = stuIsCurrent;
	}

	public StudentEnrolObject getStuEnrolObject() {
		return stuEnrolObject;
	}

	public void setStuEnrolObject(StudentEnrolObject stuEnrolObject) {
		this.stuEnrolObject = stuEnrolObject;
	}

	public String getStuTeachingPoint() {
		return stuTeachingPoint;
	}

	public void setStuTeachingPoint(String stuTeachingPoint) {
		this.stuTeachingPoint = stuTeachingPoint;
	}

	public int getStuIsSuiqian() {
		return stuIsSuiqian;
	}

	public void setStuIsSuiqian(int stuIsSuiqian) {
		this.stuIsSuiqian = stuIsSuiqian;
	}

	public int getStuIsTransProvincial() {
		return stuIsTransProvincial;
	}

	public void setStuIsTransProvincial(int stuIsTransProvincial) {
		this.stuIsTransProvincial = stuIsTransProvincial;
	}

	public int getStuEnrolWay() {
		return stuEnrolWay;
	}

	public void setStuEnrolWay(int stuEnrolWay) {
		this.stuEnrolWay = stuEnrolWay;
	}

	public String getStuZKH() {
		return stuZKH;
	}

	public void setStuZKH(String stuZKH) {
		this.stuZKH = stuZKH;
	}

	public String getStuExamineeNumber() {
		return stuExamineeNumber;
	}

	public void setStuExamineeNumber(String stuExamineeNumber) {
		this.stuExamineeNumber = stuExamineeNumber;
	}

	public String getStuTotalScore() {
		return stuTotalScore;
	}

	public void setStuTotalScore(String stuTotalScore) {
		this.stuTotalScore = stuTotalScore;
	}

	public StudentEnrolType getStuEnrolType() {
		return stuEnrolType;
	}

	public void setStuEnrolType(StudentEnrolType stuEnrolType) {
		this.stuEnrolType = stuEnrolType;
	}

	public String getStuSchoolingType() {
		return stuSchoolingType;
	}

	public void setStuSchoolingType(String stuSchoolingType) {
		this.stuSchoolingType = stuSchoolingType;
	}

	public String getStuSchoolCode() {
		return stuSchoolCode;
	}

	public void setStuSchoolCode(String stuSchoolCode) {
		this.stuSchoolCode = stuSchoolCode;
	}

	public String getStuSubsectionCulture() {
		return stuSubsectionCulture;
	}

	public void setStuSubsectionCulture(String stuSubsectionCulture) {
		this.stuSubsectionCulture = stuSubsectionCulture;
	}

	public String getStuOtherContact() {
		return stuOtherContact;
	}

	public void setStuOtherContact(String stuOtherContact) {
		this.stuOtherContact = stuOtherContact;
	}

	public String getStuHomeTel() {
		return stuHomeTel;
	}

	public void setStuHomeTel(String stuHomeTel) {
		this.stuHomeTel = stuHomeTel;
	}

	public String getStuHomeAddrNow() {
		return stuHomeAddrNow;
	}

	public void setStuHomeAddrNow(String stuHomeAddrNow) {
		this.stuHomeAddrNow = stuHomeAddrNow;
	}

	public String getStuPostalCode() {
		return stuPostalCode;
	}

	public void setStuPostalCode(String stuPostalCode) {
		this.stuPostalCode = stuPostalCode;
	}

	public StudentNationality getStuNationality() {
		return stuNationality;
	}

	public void setStuNationality(StudentNationality stuNationality) {
		this.stuNationality = stuNationality;
	}

	public StudentFamilyMemberEntity getStuFamilyMember1() {
		return stuFamilyMember1;
	}

	public void setStuFamilyMember1(StudentFamilyMemberEntity stuFamilyMember1) {
		this.stuFamilyMember1 = stuFamilyMember1;
	}

	public StudentFamilyMemberEntity getStuFamilyMember2() {
		return stuFamilyMember2;
	}

	public void setStuFamilyMember2(StudentFamilyMemberEntity stuFamilyMember2) {
		this.stuFamilyMember2 = stuFamilyMember2;
	}

	public UploadFileEntity getStuUploadFile() {
		return stuUploadFile;
	}

	public void setStuUploadFile(UploadFileEntity stuUploadFile) {
		this.stuUploadFile = stuUploadFile;
	}

	public ClassEntity getStuClass() {
		return stuClass;
	}

	public void setStuClass(ClassEntity stuClass) {
		this.stuClass = stuClass;
	}

	public SysUserEntity getUser() {
		return user;
	}

	public void setUser(SysUserEntity user) {
		this.user = user;
	}
}