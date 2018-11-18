package com.enroll.modules.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 招生周期
 * 
 * @author hsc
 *
 * Aug 13, 2017
 */
public class EnrolPeriodEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	/**
	 * 年度标识 用户可编写 若用户没有指定则区当前表中本字段最大值+1
	 */
	private int year;
	
	/**
	 * 入学月份
	 */
	private int mmonth;
	
	/**
	 * 周期状态     已关闭的周期不能再次开启
	 * 0:未开启    1:已开启    2:已关闭
	 */
	private int status;
	
	/**
	 * 开始时间    status为1时设置
	 */
	private Date start;
	
	/**
	 * 结束时间    status为2时设置
	 */
	private Date end;

	public String getStatuStr(){
		switch (status) {
		case 0:
			return "未开启";
		case 1:
			return "已开启";
		case 2:
			return "已结束";

		}
		return "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMmonth() {
		return mmonth;
	}

	public void setMmonth(int mmonth) {
		this.mmonth = mmonth;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
}
