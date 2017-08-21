package cn.itcast.oa.videw.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.baseaction.BaseAction;
import cn.itcast.oa.domain.ApplicationInfo;
import cn.itcast.oa.domain.ApplicationTemplate;
import cn.itcast.oa.domain.ApproveInfo;
import cn.itcast.oa.domain.TaskView;
import cn.itcast.oa.util.HqlHelper;

import com.opensymphony.xwork2.ActionContext;

/**
 * 流转功能
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class FlowAction extends BaseAction{

	  private Long templateId;
	  private String taskId;
	  private Long applicationId;
	  private String status;
	  private File upload;//上传使用
	  private String comment;
	  private boolean approval;
	  private String outcome;
	  private InputStream inputStream;
	 /**
	  * 起草申请(申请模板列表)
	  */
	public String applicationTemplateList() throws Exception {
    
		//准备显示的数据
		List<ApplicationTemplate> listTemplate = applicationTemplateService.findAll();
	    ActionContext.getContext().put("listTemplate", listTemplate);
		return "templateList";
	}
	
	
	/**进入提交申请页面*/
	public String submitUI() throws Exception {
		
		return "submitUI";
	}
	
	
	
	/**提交申请*/
	public String submit() throws Exception {
		
       //封装数据
		 ApplicationInfo applicationInfo = new ApplicationInfo();
		 
		 //在action中可以直接获取的数据，先进行封装
		
		 applicationInfo.setPath(saveUploadFile(upload));
		 applicationInfo.setApplicant(getCurrentUser());
		 applicationInfo.setApplicationTemplate( applicationTemplateService.getById(templateId));
		 
		 
		 
		 //调用业务方法(保存申请信息，启动流程开始流转)
		 flowService.submit(applicationInfo);
		 
		
		return "toMyApplicationList";
	}
	
	
	/**我的申请查询*/
	public String myApplicationList() throws Exception {
		//构建查询条件
		 new HqlHelper(ApplicationInfo.class, "a")//
		    .addCondition("a.applicant=?", getCurrentUser())//
		    .addCondition(templateId!=null,"a.applicationTemplate.id=?", templateId)//
		    .addCondition(status!=null&&status.trim().length()>0,"a.status=?",status)//
		    .addOrder("a.applyTime", false)//
		    .buildPageBeanForStruts2(pageNum, userservice);
		 
  
		 List<ApplicationTemplate> applicationTemplateList = applicationTemplateService.findAll();
		 ActionContext.getContext().put("applicationTemplateList",
				applicationTemplateList);
		return "myApplicationList";
	}
	
	//===========================审批人的功能==================================
	
    /**查看流转记录*/
	public String approveHistory() throws Exception {
		
        List<ApproveInfo> listApproveInfo = flowService.findApproveInfoByApplicationId(applicationId);		
		ActionContext.getContext().put("listApproveInfo", listApproveInfo);
		
		
		return "approveHistory";
	}
	
	
	
	  /**带我审批*/
	public String myTaskList() throws Exception {
		
       List<TaskView> listTaskView = flowService.findMyTaskViewList(getCurrentUser());	
	  ActionContext.getContext().put("listTaskView", listTaskView);
		
		return "myTaskList";
	}
	
	
	
	  /**审批处理页面*/
	public String approveUI() throws Exception {
     
	    Collection<String> outcomes = flowService.getOutComesByTaskId(taskId);
		
	    ActionContext.getContext().put("outcomes", outcomes);
		return "approveUI";
	}
	
	/**审批处理*/
	public String approve() throws Exception {
		 //封装对象
		ApproveInfo approveInfo = new ApproveInfo();
		
		approveInfo.setApplicationInfo(flowService.getApplicationInfoById(applicationId));
		approveInfo.setApprovel(approval);
		approveInfo.setApprover(getCurrentUser());
		approveInfo.setApproveTime(new Date());
		approveInfo.setComment(comment);
		
		//调用业务方法，进行审批处理
		 flowService.approve(approveInfo,taskId,outcome);
		return "toMyTaskList";
	}

	  /**进入查看申请信息的页面*/
		public String applicationInfoUI() throws Exception {
	      ActionContext.getContext().getValueStack().push(applicationId);
			return "showForm";
		}
		/**进入查看申请信息的页面*/
		public String downloadAppInfo() throws Exception {
			 //取得ApplicationInfo
			ApplicationInfo applicationInfo = flowService.getApplicationInfoById(applicationId);
			
			//取得申请文件的路径
			 String path = applicationInfo.getPath();
			 //生成文件
			 File file = new File(path);
			 //下载文件
			 
			 if(file.exists()){
				 inputStream = new FileInputStream(file);
			 }
			 //准备文件名
			  String fileName = applicationInfo.getApplicationTemplate().getTemplateName();
			  
			  //处理中文乱码的问题
			   fileName = new String(fileName.getBytes("gbk"),"iso-8859-1");
			   
			   ActionContext.getContext().put("fileName", fileName);
			return "downloadAppInfo";
		}
	
	//========================================================================================
	
	
	public Long getTemplateId() {
		return templateId;
	}
	
	
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}


	public File getUpload() {
		return upload;
	}


	public void setUpload(File upload) {
		this.upload = upload;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getTaskId() {
		return taskId;
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	public Long getApplicationId() {
		return applicationId;
	}


	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}




	public String getOutcome() {
		return outcome;
	}


	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}


	public boolean isApproval() {
		return approval;
	}


	public void setApproval(boolean approval) {
		this.approval = approval;
	}


	public InputStream getInputStream() {
		return inputStream;
	}


	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	
	
}
