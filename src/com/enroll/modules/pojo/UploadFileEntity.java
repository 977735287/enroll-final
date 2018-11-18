package com.enroll.modules.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 上传文件
 * 
 * @author hsc
 *
 * Oct 7, 2017
 */
public class UploadFileEntity  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	/**
	 * 文件原名
	 */
	private String originalName;
	
	/**
	 * 文件新名
	 */
	private String newName;
	
	/**
	 * 文件存放路径
	 */
	private String url;
	
	/**
	 * 文件大小
	 */
	private Long fileSize;
	
	/**
	 * 文件类型
	 */
	private String fileType;
	
	/**
	 * 类型
	 * 0:图片  1:文档   2:其他
	 */
	private int type;
	
//	/**
//	 * 所对应的学生信息
//	 */
//	private StuInfoEntity stuInfo;
	
	/**
	 * 上传时间
	 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

//	public StuInfoEntity getStuInfo() {
//		return stuInfo;
//	}
//
//	public void setStuInfo(StuInfoEntity stuInfo) {
//		this.stuInfo = stuInfo;
//	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
