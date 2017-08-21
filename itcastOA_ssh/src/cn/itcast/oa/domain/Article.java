package cn.itcast.oa.domain;

import java.util.Date;

public class Article {
	private Long id;
	private String title;
	private String content;
	private String faceIcon;
	private  User author;
	private String ipAddress;
	private Date postTime;
	
	public Article(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFaceIcon() {
		return faceIcon;
	}

	public void setFaceIcon(String faceIcon) {
		this.faceIcon = faceIcon;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	
	

}
