package cn.itcast.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;





public class ApplicationInfo{
	 /**
	  * 状态常量："审批中"
	  */
	public  static final  String STATUS_RUNNING = "审批中";
	 /**
	  * 状态常量: "已通过"
	  */
	public  static final  String STATUS_APPROVED = "已通过";
	/**
	 * 状态常量:"未通过"
	 */
	public  static final  String STATUS_REJECTED= "未通过";
	
	
	
	
	private Long id;
	private String path;
	private ApplicationTemplate applicationTemplate;
	private Set<ApproveInfo> approveInfos = new HashSet<ApproveInfo>();
	private String title;
	private User applicant;
	private Date applyTime;
	private String status;
	
	
	
	public ApplicationInfo(){}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}



	


	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public User getApplicant() {
		return applicant;
	}



	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}



	public Date getApplyTime() {
		return applyTime;
	}



	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public Set<ApproveInfo> getApproveInfos() {
		return approveInfos;
	}



	public void setApproveInfos(Set<ApproveInfo> approveInfos) {
		this.approveInfos = approveInfos;
	}



	public ApplicationTemplate getApplicationTemplate() {
		return applicationTemplate;
	}



	public void setApplicationTemplate(ApplicationTemplate applicationTemplate) {
		this.applicationTemplate = applicationTemplate;
	}
	
	
}
