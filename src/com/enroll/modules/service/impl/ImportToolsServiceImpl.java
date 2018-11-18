package com.enroll.modules.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enroll.modules.mapper.ImportToolsDao;
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
import com.enroll.modules.service.ImportToolsService;

/**
 * @author hsc
 *
 * Aug 15, 2017
 */
@Service
public class ImportToolsServiceImpl implements ImportToolsService {
	
	@Autowired
	ImportToolsDao importToolsDao;

	@Override
	public void saveArea(AreaEntity areaEntity) {
		importToolsDao.saveArea(areaEntity);
	}

	@Override
	public void saveCertificatesType(CertificatesTypeEntity certificatesTypeEntity) {
		importToolsDao.saveCertificatesType(certificatesTypeEntity);
	}

	@Override
	public void saveNation(Nation nation) {
		importToolsDao.saveNation(nation);
	}

	@Override
	public void saveNationality(StudentNationality studentNationality) {
		importToolsDao.saveNationality(studentNationality);
	}

	@Override
	public void savePoliticalOutlook(PoliticalOutlook politicalOutlook) {
		importToolsDao.savePoliticalOutlook(politicalOutlook);
	}

	@Override
	public void saveEnrolObject(StudentEnrolObject studentEnrolObject) {
		importToolsDao.saveEnrolObject(studentEnrolObject);
	}

	@Override
	public void saveEnrolType(StudentEnrolType studentEnrolType) {
		importToolsDao.saveEnrolType(studentEnrolType);
	}

	@Override
	public void saveEntranceWay(StudentEntranceWay studentEntranceWay) {
		importToolsDao.saveEntranceWay(studentEntranceWay);
	}

	@Override
	public void saveHKMTOC(StudentHKMTOC studentHKMTOC) {
		importToolsDao.saveHKMTOC(studentHKMTOC);
	}

	@Override
	public void saveMemberRelationShip(MemberRelationShip memberRelationShip) {
		importToolsDao.saveMemberRelationShip(memberRelationShip);
	}

	@Override
	public void saveStudentType(StudentType studentType) {
		importToolsDao.saveStudentType(studentType);
	}

	@Override
	public AreaEntity queryObject(Object id) {
		return importToolsDao.queryObject(id);
	}

}
