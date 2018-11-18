package com.enroll.modules.mapper;

import com.enroll.modules.pojo.AreaEntity;
import com.enroll.modules.pojo.CertificatesTypeEntity;
import com.enroll.modules.pojo.MemberRelationShip;
import com.enroll.modules.pojo.Nation;
import com.enroll.modules.pojo.PoliticalOutlook;
import com.enroll.modules.pojo.StudentEnrolObject;
import com.enroll.modules.pojo.StudentEnrolType;
import com.enroll.modules.pojo.StudentEntranceWay;
import com.enroll.modules.pojo.StudentHKMTOC;
import com.enroll.modules.pojo.StudentNationality;
import com.enroll.modules.pojo.StudentType;

/**
 * @author hsc
 *
 * Aug 21, 2017
 */
public interface ImportToolsDao {
	
	void saveArea(AreaEntity areaEntity);
	void saveCertificatesType(CertificatesTypeEntity certificatesTypeEntity);
	void saveNation(Nation nation);
	void saveNationality(StudentNationality studentNationality);
	void savePoliticalOutlook(PoliticalOutlook politicalOutlook);
	void saveEnrolObject(StudentEnrolObject studentEnrolObject);
	void saveEnrolType(StudentEnrolType studentEnrolType);
	void saveEntranceWay(StudentEntranceWay studentEntranceWay);
	void saveHKMTOC(StudentHKMTOC studentHKMTOC);
	void saveMemberRelationShip(MemberRelationShip memberRelationShip);
	void saveStudentType(StudentType studentType);

	AreaEntity queryObject(Object id);
	AreaEntity select(Integer id);
}
