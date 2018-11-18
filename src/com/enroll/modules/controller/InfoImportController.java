package com.enroll.modules.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enroll.common.utils.ExcelUtil;
import com.enroll.common.utils.R;
import com.enroll.modules.pojo.AreaEntity;
import com.enroll.modules.pojo.CertificatesTypeEntity;
import com.enroll.modules.pojo.LengthsOfSchoolingEntity;
import com.enroll.modules.pojo.MajorEntity;
import com.enroll.modules.pojo.Nation;
import com.enroll.modules.pojo.PoliticalOutlook;
import com.enroll.modules.pojo.StuInfoEntity;
import com.enroll.modules.pojo.StudentEnrolObject;
import com.enroll.modules.pojo.StudentEnrolType;
import com.enroll.modules.pojo.StudentEntranceWay;
import com.enroll.modules.pojo.StudentHKMTOC;
import com.enroll.modules.pojo.StudentNationality;
import com.enroll.modules.pojo.StudentType;
import com.enroll.modules.pojo.SysUserEntity;
import com.enroll.modules.service.StudentEnrollService;
import com.enroll.modules.service.StudentInfoService;
import com.enroll.modules.service.SysUserService;

/**
 * @author hsc
 *
 * Mar 23, 2018
 */
@Controller
@RequestMapping("/sys/information")
public class InfoImportController {

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private StudentEnrollService studentEnrollService;
	
	@Autowired
	private StudentInfoService studentInfoService;
	
	/**
	 * 数据导入
	 */
	@ResponseBody
	@RequestMapping("/import1.do")
	public R import1(){
		
		File file = new File("C:/Users/Rose/Desktop/test.xlsx");
		ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(file);
		for(int i = 1 ;i < result.size() ;i++){
			SysUserEntity user = new SysUserEntity();
			user.setUsername(result.get(i).get(4).toString());
			user.setPassword("123456");
			user.setMobile(result.get(i).get(33).toString());
			List<Long> roleIdList = new ArrayList<Long>();
			roleIdList.add(3L);			
			user.setEnrollStatus(2);
			user.setStatus(1);
			user.setCreateUserId(1L);
			user.setRoleIdList(roleIdList);
			
			sysUserService.save(user);
			System.out.println("已保存学生用户：" + user.getUsername()+"--"+user.getMobile());
		}
		
		return R.ok();
	}
	
	/**
	 * 数据导入
	 * @throws ParseException 
	 */
	@RequestMapping("/import2.do")
	public R import2() throws ParseException{
		List<AreaEntity> areaList = studentInfoService.queryAreaList();
		List<Nation> nationList = studentInfoService.queryNationList();
//		List<AreaEntity> areaList = new ArrayList<>();
//		List<Nation> nationList = new ArrayList<>();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyyMM");
		File file = new File("C:/Users/Rose/Desktop/test.xlsx");
		ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(file);
		for(int i = 1 ;i < result.size() ;i++){
			StuInfoEntity stuInfoEntity = new StuInfoEntity();
			stuInfoEntity.setStuName(result.get(i).get(0).toString());
			if(result.get(i).get(1).toString().equals("男")){
				stuInfoEntity.setStuSex(0);
			}else if(result.get(i).get(1).toString().equals("女")){
				stuInfoEntity.setStuSex(1);
			}
			Date date=simpleDateFormat.parse(result.get(i).get(2).toString());
			stuInfoEntity.setStuBirthday(date);
			CertificatesTypeEntity c = new CertificatesTypeEntity();
			c.setId(1L);
			stuInfoEntity.setStuCertificatesType(c);
			stuInfoEntity.setStuCertificatesNum(result.get(i).get(4).toString());
			stuInfoEntity.setStuPinyinName(result.get(i).get(5).toString());
			stuInfoEntity.setStuClassName(result.get(i).get(6).toString());
			stuInfoEntity.setStuNum(result.get(i).get(7).toString());
			StudentType stuType = new StudentType();
			stuType.setId(3L);
			stuInfoEntity.setStuType(stuType);
			stuInfoEntity.setStuIsAllDay(0);
			StudentEntranceWay stuEntranceWay = new StudentEntranceWay();
			stuEntranceWay.setId(11L);
			stuInfoEntity.setStuEntranceWay(stuEntranceWay);
			stuInfoEntity.setStuStudyWay(1);
			StudentNationality stuNationality = new StudentNationality();
			stuNationality.setId(1L);
			stuInfoEntity.setStuNationality(stuNationality);
			StudentHKMTOC stuHKMTOC = new StudentHKMTOC();
			stuHKMTOC.setId(1L);
			stuInfoEntity.setStuHKMTOC(stuHKMTOC);
			stuInfoEntity.setStuIsMarried(1);
			stuInfoEntity.setStuTrainSection(result.get(i).get(15).toString());
			if(result.get(i).get(16).toString().equals("是")){
				stuInfoEntity.setStuIsSuiqian(0);
			}else if(result.get(i).get(16).toString().equals("否")){
				stuInfoEntity.setStuIsSuiqian(1);
			}
			int k = 0;
			for(AreaEntity a : areaList){
				if(a.getCode().equals(result.get(i).get(17).toString())){
					stuInfoEntity.setStuArea(a);
					k++;
				}
				if(a.getCode().equals(result.get(i).get(18).toString())){
					stuInfoEntity.setStuBirthArea(a);
					k++;
				}
				if(a.getCode().equals(result.get(i).get(19).toString())){
					stuInfoEntity.setStuNationArea(a);
					k++;
				}
				if(a.getCode().equals(result.get(i).get(22).toString())){
					stuInfoEntity.setStuHouseArea(a);
					k++;
				}
				if(k == 4){
					break;
				}
			}
			stuInfoEntity.setStuDetailAdress(result.get(i).get(20).toString());
			stuInfoEntity.setStuPoliceStation(result.get(i).get(21).toString());
			if(result.get(i).get(23).toString().equals("农业户口")){
				stuInfoEntity.setStuHouseHoldType(0);
			}else if(result.get(i).get(23).toString().equals("非农业户口")){
				stuInfoEntity.setStuHouseHoldType(1);
			}
			if(result.get(i).get(24).toString().equals("农村")){
				stuInfoEntity.setStuDomicile(0);
			}else if(result.get(i).get(24).toString().equals("乡镇")){
				stuInfoEntity.setStuDomicile(1);
			}else if(result.get(i).get(24).toString().equals("县城")){
				stuInfoEntity.setStuDomicile(2);
			}else if(result.get(i).get(24).toString().equals("城市")){
				stuInfoEntity.setStuDomicile(3);
			}
			Date date1=simpleDateFormat1.parse(result.get(i).get(25).toString());
			stuInfoEntity.setStuEntranceTime(date1);
			if(result.get(i).get(26).toString().equals("园林技术")){
				MajorEntity majorEntity = new MajorEntity();
				majorEntity.setMajorId(6L);
				stuInfoEntity.setStuMajor(majorEntity);
				LengthsOfSchoolingEntity l = new LengthsOfSchoolingEntity();
				l.setId(2L);
				stuInfoEntity.setStuLength(l);
			}else if(result.get(i).get(26).toString().equals("现代林业技术")){
				MajorEntity majorEntity = new MajorEntity();
				majorEntity.setMajorId(7L);
				stuInfoEntity.setStuMajor(majorEntity);
				LengthsOfSchoolingEntity l = new LengthsOfSchoolingEntity();
				l.setId(2L);
				stuInfoEntity.setStuLength(l);
			}else if(result.get(i).get(26).toString().equals("客户信息服务")){
				MajorEntity majorEntity = new MajorEntity();
				majorEntity.setMajorId(8L);
				stuInfoEntity.setStuMajor(majorEntity);
				LengthsOfSchoolingEntity l = new LengthsOfSchoolingEntity();
				l.setId(1L);
				stuInfoEntity.setStuLength(l);
			}
			for(Nation na : nationList){
				if(na.getName().equals(result.get(i).get(28).toString())){
					stuInfoEntity.setStuNation(na);
				}
			}
			if(result.get(i).get(29).toString().equals("群众")){
				PoliticalOutlook politicalOutlook =new PoliticalOutlook();
				politicalOutlook.setId(2L);
				stuInfoEntity.setStuPoliticalOutlook(politicalOutlook);
			}else if(result.get(i).get(29).toString().equals("中国共产主义青年团团员")){
				PoliticalOutlook politicalOutlook =new PoliticalOutlook();
				politicalOutlook.setId(1L);
				stuInfoEntity.setStuPoliticalOutlook(politicalOutlook);
			}	
			stuInfoEntity.setStuHealthCondition(0);
			if(result.get(i).get(31).toString().equals("应届")){
				stuInfoEntity.setStuIsCurrent(0);
			}else if(result.get(i).get(31).toString().equals("非应届")){
				stuInfoEntity.setStuIsCurrent(1);
			}
			if(result.get(i).get(32).toString().equals("应届初中毕业生")){
				StudentEnrolObject studentEnrolObject = new StudentEnrolObject();
				studentEnrolObject.setId(1L);
				stuInfoEntity.setStuEnrolObject(studentEnrolObject);
			}else if(result.get(i).get(32).toString().equals("往届初中毕业生")){
				StudentEnrolObject StudentEnrolObject = new StudentEnrolObject();
				StudentEnrolObject.setId(3L);
				stuInfoEntity.setStuEnrolObject(StudentEnrolObject);
			}
			stuInfoEntity.setStuPhoneNum(result.get(i).get(33).toString());
			stuInfoEntity.setStuEnrolWay(1);
			StudentEnrolType studentEnrolType = new StudentEnrolType();
			studentEnrolType.setId(1L);
			stuInfoEntity.setStuEnrolType(studentEnrolType);
			stuInfoEntity.setStuZKH(result.get(i).get(36).toString());
			stuInfoEntity.setStuSubsectionCulture(result.get(i).get(42).toString());
			stuInfoEntity.setStuHomeAddrNow(result.get(i).get(45).toString());
			stuInfoEntity.setStuPostalCode(result.get(i).get(46).toString());
			stuInfoEntity.setStuHomeTel(result.get(i).get(47).toString());
			
			SysUserEntity sysUserEntity = new SysUserEntity();
			sysUserEntity.setUserId(i+1L);
			stuInfoEntity.setUser(sysUserEntity);
			studentEnrollService.save(stuInfoEntity);
			
			System.out.println("已保存学生信息：" + stuInfoEntity.getStuCertificatesNum()+"--"+stuInfoEntity.getStuName());
		}
		
		return R.ok();
	}

}
