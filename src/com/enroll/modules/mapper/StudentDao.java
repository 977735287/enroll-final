package com.enroll.modules.mapper;

import com.enroll.modules.pojo.AreaEntity;
import com.enroll.modules.pojo.CertificatesTypeEntity;
import com.enroll.modules.pojo.MemberRelationShip;
import com.enroll.modules.pojo.Nation;
import com.enroll.modules.pojo.PoliticalOutlook;
import com.enroll.modules.pojo.StudentEnrolObject;
import com.enroll.modules.pojo.StudentEnrolType;
import com.enroll.modules.pojo.StudentEntranceWay;
import com.enroll.modules.pojo.StudentFamilyMemberEntity;
import com.enroll.modules.pojo.StudentHKMTOC;
import com.enroll.modules.pojo.StudentNationality;
import com.enroll.modules.pojo.StudentType;
import com.enroll.modules.pojo.UploadFileEntity;

/**
 * @author hsc
 *
 * Sep 6, 2017
 */
public interface StudentDao {

	/**
	 * 根据主键查数据,给多对一用
     * @param id
     * @return
	 */
	public UploadFileEntity selectUploadFile(Integer id);
	public StudentType selectStudentType(Integer id);
	public CertificatesTypeEntity selectCertificatesType(Integer id);
	public StudentEntranceWay selectStudentEntranceWay(Integer id);
	public StudentNationality selectStudentNationality(Integer id);
	public StudentHKMTOC selectStudentHKMTOC(Integer id);
	public AreaEntity selectArea(Integer id);
	public Nation selectNation(Integer id);
	public PoliticalOutlook selectPoliticalOutlook(Integer id);
	public StudentEnrolObject selectStudentEnrolObject(Integer id);
	public StudentEnrolType selectStudentEnrolType(Integer id);
	public StudentFamilyMemberEntity selectFamilyMember(Integer id);
	
	public MemberRelationShip selectMemberRelationShip(Integer id);
	
}
