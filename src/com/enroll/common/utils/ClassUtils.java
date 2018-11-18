package com.enroll.common.utils;

import java.util.Date;

import com.enroll.common.exception.RRException;
import com.enroll.modules.pojo.StuInfoEntity;

/**
 * @author hsc
 *
 *         Feb 21, 2018
 */
public class ClassUtils {

	// 学校代码
	public final static String SCHOOL_CODE = "02";

	// 获取学生的学号
	public static String getStuNum(StuInfoEntity stuInfoEntity) {

		checkMajorVail(stuInfoEntity);

		int num = stuInfoEntity.getStuMajor().getMajorMaxNum() / (stuInfoEntity.getStuMajor().getMajorMax() + 1) + 1;

		String classNum = num > 9 ? "" + num : "0" + num;

		int num1 = stuInfoEntity.getStuMajor().getMajorMaxNum() % stuInfoEntity.getStuMajor().getMajorMax() + 1;

		String stuNum = num1 > 9 ? "" + num1 : "0" + num1;

		return DateUtils.getYear(new Date()) + SCHOOL_CODE + stuInfoEntity.getStuMajor().getMajorCode() + classNum
				+ stuNum;
	}

	// 获取班级的名称
	public static String getClassName(StuInfoEntity stuInfoEntity) {

		checkMajorVail(stuInfoEntity);

		int num = stuInfoEntity.getStuMajor().getMajorMaxNum() / (stuInfoEntity.getStuMajor().getMajorMax() + 1) + 1;

		return stuInfoEntity.getStuMajor().getMajorName() + num + "班";
	}

	// 获取班级编号
	public static String getClassCode(StuInfoEntity stuInfoEntity) {

		checkMajorVail(stuInfoEntity);

		int num = stuInfoEntity.getStuMajor().getMajorMaxNum() / (stuInfoEntity.getStuMajor().getMajorMax() + 1) + 1;

		String classNum = num > 9 ? "" + num : "0" + num;

		return DateUtils.getYear(new Date()) + SCHOOL_CODE + stuInfoEntity.getStuMajor().getMajorCode() + classNum;
	}

	// 获取班级当前人数
	public static int getClassCurrentNum(StuInfoEntity stuInfoEntity) {

		checkMajorVail(stuInfoEntity);

		int num = stuInfoEntity.getStuMajor().getMajorMaxNum() % stuInfoEntity.getStuMajor().getMajorMax() + 1;

		return num;
	}

	// 检查是否需要创建班级
	public static boolean checkClass(StuInfoEntity stuInfoEntity) {

		int num = stuInfoEntity.getStuMajor().getMajorMaxNum() % stuInfoEntity.getStuMajor().getMajorMax() + 1;

		if (num == 1)
			return true;

		return false;
	}
	
	public static void checkMajorVail(StuInfoEntity stuInfoEntity){

		if (stuInfoEntity.getStuMajor() == null) {
			throw new RRException("获取专业信息失败");
		}

		if (stuInfoEntity.getStuMajor().getMajorMax() == 0) {
			throw new RRException("专业班级人数异常！");
		}
	}
	
	public static void main(String[] args){

		System.out.println(0 % 10 + 1);
	}

}
