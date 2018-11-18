package com.enroll.modules.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hsc
 *
 * Aug 13, 2017
 */
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 状态标识
	 * 0:未报名    1:已报名还未录取    2:已录取     3:未被录取
	 */
	private int status;
	
	/**
	 * 提示信息   每次登录时提示
	 */
	private String message;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 学生信息
	 */
	private StuInfoEntity stuInfo;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public StuInfoEntity getStuInfo() {
		return stuInfo;
	}

	public void setStuInfo(StuInfoEntity stuInfo) {
		this.stuInfo = stuInfo;
	}
	
}
