package com.enroll.common.install;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

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
 *         Aug 21, 2017
 */
public class ImportDB {

	Area area;
	CertificatesType certificatesType;
	NationFile nationFile;
	Nationality nationality;
	PoliticalOutlookFile politicalOutlookFile;
	StuEnrolObject stuEnrolObject;
	StuEnrolType stuEnrolType;
	StuEntranceWay stuEntranceWay;
	StuHKMTOC stuHKMTOC;
	StuMemberRelationShip stuMemberRelationShip;
	StuType stuType;

	@Autowired
	ImportToolsService importToolsService;

	public ImportDB() {
		this.area = new Area();
		this.certificatesType = new CertificatesType();
		this.nationFile = new NationFile();
		this.nationality = new Nationality();
		this.politicalOutlookFile = new PoliticalOutlookFile();
		this.stuEnrolObject = new StuEnrolObject();
		this.stuEnrolType = new StuEnrolType();
		this.stuEntranceWay = new StuEntranceWay();
		this.stuHKMTOC = new StuHKMTOC();
		this.stuMemberRelationShip = new StuMemberRelationShip();
		this.stuType = new StuType();
	}

	public void init() {
		// 添加行政区域数据
		for (Map.Entry<String, String> e : area.getAreaList().entrySet()) {
			AreaEntity areaEntity = new AreaEntity();
			areaEntity.setCode(e.getKey());
			areaEntity.setName(e.getValue());
			areaEntity.setLevel(area.level(e.getKey()));
			if (area.parent(e.getKey())) {
				AreaEntity areaParent = importToolsService.queryObject(area.getParentCode(e.getKey()));
				areaEntity.setParent(areaParent);
			} else {
				areaEntity.setParent(null);
			}
			importToolsService.saveArea(areaEntity);
		}

		// 添加证件类型数据
		for (String name : certificatesType.getList()) {
			CertificatesTypeEntity certificatesTypeEntity = new CertificatesTypeEntity();
			certificatesTypeEntity.setName(name);
			importToolsService.saveCertificatesType(certificatesTypeEntity);
		}

		// 添加民族数据
		for (String name : nationFile.getList()) {
			Nation nation = new Nation();
			nation.setName(name);
			importToolsService.saveNation(nation);
		}

		// 添加国籍数据
		for (String name : nationality.getList()) {
			StudentNationality studentNationality = new StudentNationality();
			studentNationality.setName(name);
			importToolsService.saveNationality(studentNationality);
		}

		// 添加政治面貌数据
		for (String name : politicalOutlookFile.getList()) {
			PoliticalOutlook politicalOutlook = new PoliticalOutlook();
			politicalOutlook.setName(name);
			importToolsService.savePoliticalOutlook(politicalOutlook);
		}

		// 添加招生对象数据
		for (String name : stuEnrolObject.getList()) {
			StudentEnrolObject studentEnrolObject = new StudentEnrolObject();
			studentEnrolObject.setName(name);
			importToolsService.saveEnrolObject(studentEnrolObject);
		}

		// 添加招生类别数据
		for (String name : stuEnrolType.getList()) {
			StudentEnrolType studentEnrolType = new StudentEnrolType();
			studentEnrolType.setName(name);
			importToolsService.saveEnrolType(studentEnrolType);
		}

		// 添加入学方式数据
		for (String name : stuEntranceWay.getList()) {
			StudentEntranceWay studentEntranceWay = new StudentEntranceWay();
			studentEntranceWay.setName(name);
			importToolsService.saveEntranceWay(studentEntranceWay);
		}

		// 添加港澳台侨外数据
		for (String name : stuHKMTOC.getList()) {
			StudentHKMTOC studentHKMTOC = new StudentHKMTOC();
			studentHKMTOC.setName(name);
			importToolsService.saveHKMTOC(studentHKMTOC);
		}

		// 添加与学生关系数据
		for (String name : stuMemberRelationShip.getList()) {
			MemberRelationShip memberRelationShip = new MemberRelationShip();
			memberRelationShip.setName(name);
			importToolsService.saveMemberRelationShip(memberRelationShip);
		}

		// 添加学生类别数据
		for (String name : stuType.getList()) {
			StudentType studentType = new StudentType();
			studentType.setName(name);
			importToolsService.saveStudentType(studentType);
		}
	}

	public static void main(String[] args) {
//		 ImportDB idb = new ImportDB();
//		 idb.init();
	}

}
