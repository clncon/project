package cn.itcast.oa.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.domain.ApplicationInfo;
import cn.itcast.oa.domain.ApproveInfo;
import cn.itcast.oa.domain.TaskView;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.FlowService;


@Service
@Transactional
public class FlowServiceImpl implements FlowService {
	
	@Resource
	private SessionFactory sessionFactory;
    @Resource
    private ProcessEngine processEngine;
	
	
	 private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	 /**
	  * 对flowService中的submit方法进行实现
	  */
	@Override
	public void submit(ApplicationInfo applicationInfo) {
		 /**
		  * 1.设置属性并保存申请信息
		  */
		 applicationInfo.setApplyTime(new Date());//设置审批时间为当前时间
		 applicationInfo.setStatus(ApplicationInfo.STATUS_RUNNING);//默认"审批中"
		 applicationInfo.setTitle(applicationInfo.getApplicationTemplate().getTemplateName()+"_"+//
		 
	      applicationInfo.getApplicant().getRealName()+"_"+//
	      sdf.format(applicationInfo.getApplyTime()));//"{申请的模板名称}_{申请人}_{申请时间}"
		 
		 //保存到数据库
		 sessionFactory.getCurrentSession().save(applicationInfo);//保存
		 
		 
		 /**
		  * 2.启动流程，开始流转
		  * 
		  */
		    
		   //>设置流程变量
		   Map<String,Object> variables = new HashMap<String,Object>();
		    variables.put("applicationInfo", applicationInfo);
		    //>启动流程实例
		    ProcessInstance pi =  processEngine.getExecutionService().//
		        startProcessInstanceByKey(applicationInfo.getApplicationTemplate()//
				.getProcessDefinitionKey(), variables);
		  
		   //>办理完成第一个任务(提交申请)
		  
		     //取得当前流程实例的唯一任务
		    Task task = processEngine.getTaskService()//
		    		 .createTaskQuery().processInstanceId(pi.getId())//
		    		 .uniqueResult();
		     //办理这个任务
		    processEngine.getTaskService().completeTask(task.getId());
		
	}

	  /**
	   * 对flowService的findMyTaskViewList()方法进行实现
	   */
	@Override
	public List<TaskView> findMyTaskViewList(User currentUser) {
		List<TaskView> list = new ArrayList<TaskView>();
		 String userId=currentUser.getLoginName();
		//查询我的任务列表
		 List<Task> listTask  = processEngine//
				  .getTaskService()//
				  .findPersonalTasks(userId); 
		 
		 //循环每个任务取出其中的applicationInfo信息
		  for(Task task : listTask){
			  ApplicationInfo applicationInfo = (ApplicationInfo) //
					   processEngine.getTaskService()//
					   .getVariable(task.getId(),"applicationInfo");//
			  
			  
			  list.add(new TaskView(task,applicationInfo));
			  
		  }
		return list;
	}

	@Override
	public ApplicationInfo getApplicationInfoById(Long applicationId) {
		
		return (ApplicationInfo) sessionFactory.getCurrentSession().get(ApplicationInfo.class,applicationId);
	}

	@Override
	public void approve(ApproveInfo approveInfo, String taskId,String outcome) {
		//审批处理 
		
		//1.保存审批信息
		 sessionFactory.getCurrentSession().save(approveInfo);
		 //2.完成当前任务 
		 Task task = processEngine.getTaskService().getTask(taskId);//该方法一定要在完成任务之前执行，不然的话，任务就会从task表中删除，进入历史表中
		  //如果outcome不为空使用指定的transition离开本活动，否则使用默认的活动离开本活动
		  if(outcome!=null){
			  
			  processEngine.getTaskService().completeTask(taskId,outcome);//使用指定的transition离开本活动
		  }else{
			  processEngine.getTaskService().completeTask(taskId);//使用默认的transition离开本活动
			  
		  }
		
		 ProcessInstance pi = (ProcessInstance) processEngine.getExecutionService().findExecutionById(task.getExecutionId());
		//3.维护申请的状态
      
		  ApplicationInfo applicationInfo = approveInfo.getApplicationInfo();
		  if(!approveInfo.isApprovel()){
			   //如果本环节不同意的话，则流程直接 结束，并且申请的状态设置为'未通过'
			   //如果本环节不是最后一个流程的话，就需要手动的关闭这个流程了
			  
			  if(pi!=null){
				  processEngine.getExecutionService().endProcessInstance(pi.getId(),ProcessInstance.STATE_ENDED);
			  }
			  applicationInfo.setStatus(ApplicationInfo.STATUS_REJECTED);
			  
			   
		  }else{
			  
			  if(pi==null){
				  				  
				  //如果本环节同意的话，并且本环节是最后一个流程的话，就结束该流程，申请状态设置为'已通过'
				  applicationInfo.setStatus(ApplicationInfo.STATUS_APPROVED);
			  }
			  
			  
		  }
		  
		  sessionFactory.getCurrentSession().update(applicationInfo);

	
	}

	
	@Override
	public Collection<String> getOutComesByTaskId(String taskId) {
		// TODO Auto-generated method stub
		return processEngine.getTaskService().getOutcomes(taskId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApproveInfo> findApproveInfoByApplicationId(Long applicationId) {
		
		return sessionFactory.getCurrentSession().createQuery("FROM ApproveInfo a WHERE a.applicationInfo.id=? ORDER BY "
		 		+ "a.approveTime ASC")//
		 		.setParameter(0, applicationId)//
		 		.list();
		
	}

}
