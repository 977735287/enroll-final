package com.enroll.modules.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告
 * 
 * @author hsc
 *
 * Sep 20, 2017
 */
public class ArticleEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 公告ID
	 */
	private Long id;
	
	/**
	 * 公告标题
	 */
	private String artTitle;
	
	/**
	 * 公告作者
	 */
	private SysUserEntity artAuthor;
	
	/**
	 * 公告类型
	 * 0:招生政策   1:公告
	 */
	private int artType;
	
	/**
	 * 公告状态
	 * 0:未发布   1:已发布   3:已下线
	 */
	private int artStatus;
	
	/**
	 * 公告发布时间
	 */
	private Date artPublishTime;
	
	/**
	 * 公告有效时间
	 */
	private Date artVaildTime;
	
	/**
	 * 公告内容
	 */
	private String artContent;
	
	/**
	 * 公告来源
	 */
	private String artSource;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArtTitle() {
		return artTitle;
	}

	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}

	public SysUserEntity getArtAuthor() {
		return artAuthor;
	}

	public void setArtAuthor(SysUserEntity artAuthor) {
		this.artAuthor = artAuthor;
	}	

	public int getArtType() {
		return artType;
	}

	public void setArtType(int artType) {
		this.artType = artType;
	}

	public int getArtStatus() {
		return artStatus;
	}

	public void setArtStatus(int artStatus) {
		this.artStatus = artStatus;
	}

	public Date getArtPublishTime() {
		return artPublishTime;
	}

	public void setArtPublishTime(Date artPublishTime) {
		this.artPublishTime = artPublishTime;
	}

	public Date getArtVaildTime() {
		return artVaildTime;
	}

	public void setArtVaildTime(Date artVaildTime) {
		this.artVaildTime = artVaildTime;
	}

	public String getArtContent() {
		return artContent;
	}

	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}

	public String getArtSource() {
		return artSource;
	}

	public void setArtSource(String artSource) {
		this.artSource = artSource;
	}
		
}
