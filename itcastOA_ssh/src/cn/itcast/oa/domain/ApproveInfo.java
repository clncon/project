package cn.itcast.oa.domain; 

import java.util.Date;




public class ApproveInfo{
	private Long id;
	private ApplicationInfo applicationInfo;
	private User approver;
	private Date approveTime;
	private boolean approvel;
	private String comment;
	
	public ApproveInfo(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ApplicationInfo getApplicationInfo() {
		return applicationInfo;
	}

	public void setApplicationInfo(ApplicationInfo applicationInfo) {
		this.applicationInfo = applicationInfo;
	}

	public User getApprover() {
		return approver;
	}

	public void setApprover(User approver) {
		this.approver = approver;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public boolean isApprovel() {
		return approvel;
	}

	public void setApprovel(boolean approvel) {
		this.approvel = approvel;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
