package com.enroll.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.enroll.modules.pojo.StuInfoEntity;

/**
 * @author hsc
 *
 *         Mar 20, 2018
 */
public class ExcelUtils {

	@SuppressWarnings("deprecation")
	public static void exportExcel(List<StuInfoEntity> list, String sheetName, String fileName,
			HttpServletResponse response) throws Exception {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
		//单元格自适应宽度
//		sheet.autoSizeColumn(0, true);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("性别");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("出生日期 ");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("证件号码");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("姓名拼音");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("班级名称");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("学生学号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);
		cell.setCellValue("学习形式");
		cell.setCellStyle(style);
		cell = row.createCell((short) 8);
		cell.setCellValue("就读方式 ");
		cell.setCellStyle(style);
		cell = row.createCell((short) 9);
		cell.setCellValue("婚姻状况");
		cell.setCellStyle(style);
		cell = row.createCell((short) 10);
		cell.setCellValue("火车区间 ");
		cell.setCellStyle(style);
		cell = row.createCell((short) 11);
		cell.setCellValue("是否随迁子女");
		cell.setCellStyle(style);
		cell = row.createCell((short) 12);
		cell.setCellValue("详细地址 ");
		cell.setCellStyle(style);
		cell = row.createCell((short) 13);
		cell.setCellValue("所属派出所名称");
		cell.setCellStyle(style);
		cell = row.createCell((short) 14);
		cell.setCellValue("户口性质");
		cell.setCellStyle(style);
		cell = row.createCell((short) 15);
		cell.setCellValue("学生居住地类型");
		cell.setCellStyle(style);
		cell = row.createCell((short) 16);
		cell.setCellValue("入学年月");
		cell.setCellStyle(style);
		cell = row.createCell((short) 17);
		cell.setCellValue("健康状况");
		cell.setCellStyle(style);
		cell = row.createCell((short) 18);
		cell.setCellValue("联系电话");
		cell.setCellStyle(style);
		cell = row.createCell((short) 19);
		cell.setCellValue("学生来源");
		cell.setCellStyle(style);
		cell = row.createCell((short) 20);
		cell.setCellValue("招生方式");
		cell.setCellStyle(style);
		cell = row.createCell((short) 21);
		cell.setCellValue("学生类型");
		cell.setCellStyle(style);
		cell = row.createCell((short) 22);
		cell.setCellValue("证件类型");
		cell.setCellStyle(style);
		cell = row.createCell((short) 23);
		cell.setCellValue("入学方式");
		cell.setCellStyle(style);
		cell = row.createCell((short) 24);
		cell.setCellValue("国籍");
		cell.setCellStyle(style);
		cell = row.createCell((short) 25);
		cell.setCellValue("港澳台侨外");
		cell.setCellStyle(style);
		cell = row.createCell((short) 26);
		cell.setCellValue("出生地行政区划码");
		cell.setCellStyle(style);
		cell = row.createCell((short) 27);
		cell.setCellValue("生源地行政区划码");
		cell.setCellStyle(style);
		cell = row.createCell((short) 28);
		cell.setCellValue("籍贯行政区划码");
		cell.setCellStyle(style);
		cell = row.createCell((short) 29);
		cell.setCellValue("户口行政区划码");
		cell.setCellStyle(style);
		cell = row.createCell((short) 30);
		cell.setCellValue("专业");
		cell.setCellStyle(style);
		cell = row.createCell((short) 31);
		cell.setCellValue("学制");
		cell.setCellStyle(style);
		cell = row.createCell((short) 32);
		cell.setCellValue("民族");
		cell.setCellStyle(style);
		cell = row.createCell((short) 33);
		cell.setCellValue("政治面貌");
		cell.setCellStyle(style);
		cell = row.createCell((short) 34);
		cell.setCellValue("招生对象");
		cell.setCellStyle(style);
		cell = row.createCell((short) 35);
		cell.setCellValue("联招合作类型");
		cell.setCellStyle(style);
		cell = row.createCell((short) 36);
		cell.setCellValue("家庭成员1姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 37);
		cell.setCellValue("家庭成员1联系方式");
		cell.setCellStyle(style);
		cell = row.createCell((short) 38);
		cell.setCellValue("家庭成员1是否监护人");
		cell.setCellStyle(style);
		cell = row.createCell((short) 39);
		cell.setCellValue("家庭成员1关系");
		cell.setCellStyle(style);
		cell = row.createCell((short) 40);
		cell.setCellValue("家庭成员2姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 41);
		cell.setCellValue("家庭成员2联系方式");
		cell.setCellStyle(style);
		cell = row.createCell((short) 42);
		cell.setCellValue("家庭成员2是否监护人");
		cell.setCellStyle(style);
		cell = row.createCell((short) 43);
		cell.setCellValue("家庭成员2关系");
		cell.setCellStyle(style);

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			//单元格自适应宽度
			sheet.autoSizeColumn((short)(i + 1), true);
			StuInfoEntity stuInfo = (StuInfoEntity) list.get(i);

			String birthday = "";
			String entranceTime = "";
			String sex = "";
			String allDay = "";
			String studyWay = "";
			String married = "";
			String suiqian = "";
			String houseHoldType = "";
			String domicile = "";
			String healthCondition = "";
			String current = "";
			String enrolWay = "";
			if (stuInfo.getStuBirthday() != null) {
				birthday = DateUtils.format(stuInfo.getStuBirthday());
			}
			if (stuInfo.getStuEntranceTime() != null) {
				entranceTime = DateUtils.getYearMonth(stuInfo.getStuEntranceTime());
			}
			if (stuInfo.getStuSex() == 0) {
				sex = "男";
			} else if (stuInfo.getStuSex() == 1) {
				sex = "女";
			}
			if (stuInfo.getStuIsAllDay() == 0) {
				allDay = "全日制";
			} else if (stuInfo.getStuIsAllDay() == 1) {
				allDay = "非全日制";
			}
			if (stuInfo.getStuStudyWay() == 0) {
				studyWay = "走读 ";
			} else if (stuInfo.getStuStudyWay() == 1) {
				studyWay = "住校";
			}
			if (stuInfo.getStuIsMarried() == 0) {
				married = "已婚";
			} else if (stuInfo.getStuIsMarried() == 1) {
				married = "未婚";
			}
			if (stuInfo.getStuIsSuiqian() == 0) {
				suiqian = "是";
			} else if (stuInfo.getStuIsSuiqian() == 1) {
				suiqian = "否";
			}
			if (stuInfo.getStuHouseHoldType() == 0) {
				houseHoldType = "农业户口";
			} else if (stuInfo.getStuHouseHoldType() == 1) {
				houseHoldType = "非农业户口";
			}
			if (stuInfo.getStuDomicile() == 0) {
				domicile = "农村";
			} else if (stuInfo.getStuDomicile() == 1) {
				domicile = "乡镇";
			} else if (stuInfo.getStuDomicile() == 2) {
				domicile = "县城";
			} else if (stuInfo.getStuDomicile() == 3) {
				domicile = "城市";
			}
			if (stuInfo.getStuHealthCondition() == 0) {
				healthCondition = "健康良好";
			} else if (stuInfo.getStuHealthCondition() == 1) {
				healthCondition = "一般较弱";
			} else if (stuInfo.getStuHealthCondition() == 2) {
				healthCondition = "有慢性病";
			} else if (stuInfo.getStuHealthCondition() == 3) {
				healthCondition = "残疾";
			}
			if (stuInfo.getStuIsCurrent() == 0) {
				current = "应届";
			} else if (stuInfo.getStuIsCurrent() == 1) {
				current = "非应届";
			}
			if (stuInfo.getStuEnrolWay() == 0) {
				enrolWay = "统一招生";
			} else if (stuInfo.getStuEnrolWay() == 1) {
				enrolWay = "自主招生";
			}

			String className = "";
			String stuType = "";
			String stuCertificatesType = "";
			String stuEntranceWay = "";
			String stuNationality = "";
			String stuHKMTOC = "";
			String stuBirthArea = "";
			String stuArea = "";
			String stuNationArea = "";
			String stuHouseArea = "";
			String stuMajor = "";
			String stuLength = "";
			String stuNation = "";
			String stuPoliticalOutlook = "";
			String stuEnrolObject = "";
			String stuEnrolType = "";
			String stuFamilyMember1Name = "";
			String stuFamilyMember1Phone = "";
			String stuFamilyMember1Guardian = "";
			String stuFamilyMember1RL = "";
			String stuFamilyMember2Name = "";
			String stuFamilyMember2Phone = "";
			String stuFamilyMember2Guardian = "";
			String stuFamilyMember2RL = "";
//			if (stuInfo.getStuClass() != null) {
//				className = stuInfo.getStuClass().getClassName();
//			}
			if (stuInfo.getStuClassName() != null) {
				className = stuInfo.getStuClassName();
			}
			if (stuInfo.getStuType() != null) {
				stuType = stuInfo.getStuType().getName();
			}
			if (stuInfo.getStuCertificatesType() != null) {
				stuCertificatesType = stuInfo.getStuCertificatesType().getName();
			}
			if (stuInfo.getStuEntranceWay() != null) {
				stuEntranceWay = stuInfo.getStuEntranceWay().getName();
			}
			if (stuInfo.getStuNationality() != null) {
				stuNationality = stuInfo.getStuNationality().getName();
			}
			if (stuInfo.getStuHKMTOC() != null) {
				stuHKMTOC = stuInfo.getStuHKMTOC().getName();
			}
			if (stuInfo.getStuBirthArea() != null) {
				stuBirthArea = stuInfo.getStuBirthArea().getName();
			}
			if (stuInfo.getStuArea() != null) {
				stuArea = stuInfo.getStuArea().getName();
			}
			if (stuInfo.getStuNationArea() != null) {
				stuNationArea = stuInfo.getStuNationArea().getName();
			}
			if (stuInfo.getStuHouseArea() != null) {
				stuHouseArea = stuInfo.getStuHouseArea().getName();
			}
			if (stuInfo.getStuMajor() != null) {
				stuMajor = stuInfo.getStuMajor().getMajorName();
			}
			if (stuInfo.getStuLength() != null) {
				stuLength = stuInfo.getStuLength().getLengthName();
			}
			if (stuInfo.getStuNation() != null) {
				stuNation = stuInfo.getStuNation().getName();
			}
			if (stuInfo.getStuPoliticalOutlook() != null) {
				stuPoliticalOutlook = stuInfo.getStuPoliticalOutlook().getName();
			}
			if (stuInfo.getStuEnrolObject() != null) {
				stuEnrolObject = stuInfo.getStuEnrolObject().getName();
			}
			if (stuInfo.getStuEnrolType() != null) {
				stuEnrolType = stuInfo.getStuEnrolType().getName();
			}
			if (stuInfo.getStuFamilyMember1() != null) {
				stuFamilyMember1Name = stuInfo.getStuFamilyMember1().getName();
				stuFamilyMember1Phone = stuInfo.getStuFamilyMember1().getPhoneNum();
				if (stuInfo.getStuFamilyMember1().getGuardian() == 0) {
					stuFamilyMember1Guardian = "是";
				} else if (stuInfo.getStuFamilyMember1().getGuardian() == 1) {
					stuFamilyMember1Guardian = "否";
				}
				if (stuInfo.getStuFamilyMember1().getRelationShip() != null) {
					stuFamilyMember1RL = stuInfo.getStuFamilyMember1().getRelationShip().getName();
				}
			}
			if (stuInfo.getStuFamilyMember2() != null) {
				stuFamilyMember2Name = stuInfo.getStuFamilyMember2().getName();
				stuFamilyMember2Phone = stuInfo.getStuFamilyMember2().getPhoneNum();
				if (stuInfo.getStuFamilyMember2().getGuardian() == 0) {
					stuFamilyMember2Guardian = "是";
				} else if (stuInfo.getStuFamilyMember2().getGuardian() == 1) {
					stuFamilyMember2Guardian = "否";
				}
				if (stuInfo.getStuFamilyMember2().getRelationShip() != null) {
					stuFamilyMember2RL = stuInfo.getStuFamilyMember2().getRelationShip().getName();
				}
			}

			// 第四步，创建单元格，并设置值
			HSSFCell cell1 = row.createCell((short) 0);
			cell1.setCellValue(stuInfo.getStuName());
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 1);
			cell1.setCellValue(sex);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 2);
			cell1.setCellValue(birthday);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 3);
			cell1.setCellValue(stuInfo.getStuCertificatesNum());
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 4);
			cell1.setCellValue(stuInfo.getStuPinyinName());
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 5);
			cell1.setCellValue(className);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 6);
			cell1.setCellValue(stuInfo.getStuNum());
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 7);
			cell1.setCellValue(allDay);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 8);
			cell1.setCellValue(studyWay);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 9);
			cell1.setCellValue(married);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 10);
			cell1.setCellValue(stuInfo.getStuTrainSection());
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 11);
			cell1.setCellValue(suiqian);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 12);
			cell1.setCellValue(stuInfo.getStuDetailAdress());
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 13);
			cell1.setCellValue(stuInfo.getStuPoliceStation());
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 14);
			cell1.setCellValue(houseHoldType);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 15);
			cell1.setCellValue(domicile);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 16);
			cell1.setCellValue(entranceTime);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 17);
			cell1.setCellValue(healthCondition);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 18);
			cell1.setCellValue(stuInfo.getStuPhoneNum());
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 19);
			cell1.setCellValue(current);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 20);
			cell1.setCellValue(enrolWay);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 21);
			cell1.setCellValue(stuType);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 22);
			cell1.setCellValue(stuCertificatesType);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 23);
			cell1.setCellValue(stuEntranceWay);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 24);
			cell1.setCellValue(stuNationality);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 25);
			cell1.setCellValue(stuHKMTOC);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 26);
			cell1.setCellValue(stuBirthArea);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 27);
			cell1.setCellValue(stuArea);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 28);
			cell1.setCellValue(stuNationArea);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 29);
			cell1.setCellValue(stuHouseArea);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 30);
			cell1.setCellValue(stuMajor);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 31);
			cell1.setCellValue(stuLength);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 32);
			cell1.setCellValue(stuNation);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 33);
			cell1.setCellValue(stuPoliticalOutlook);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 34);
			cell1.setCellValue(stuEnrolObject);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 35);
			cell1.setCellValue(stuEnrolType);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 36);
			cell1.setCellValue(stuFamilyMember1Name);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 37);
			cell1.setCellValue(stuFamilyMember1Phone);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 38);
			cell1.setCellValue(stuFamilyMember1Guardian);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 39);
			cell1.setCellValue(stuFamilyMember1RL);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 40);
			cell1.setCellValue(stuFamilyMember2Name);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 41);
			cell1.setCellValue(stuFamilyMember2Phone);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 42);
			cell1.setCellValue(stuFamilyMember2Guardian);
			cell1.setCellStyle(style);
			cell1 = row.createCell((short) 43);
			cell1.setCellValue(stuFamilyMember2RL);
			cell1.setCellStyle(style);
		}

		// 第六步，下载文件
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/msexcel");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/wr6ite loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
}
