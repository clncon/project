package cn.itcast.oa.service;

import java.util.Collection;
import java.util.List;

import cn.itcast.oa.domain.ApplicationInfo;
import cn.itcast.oa.domain.ApproveInfo;
import cn.itcast.oa.domain.TaskView;
import cn.itcast.oa.domain.User;

public interface FlowService {

	
	 /**
	  * 调用业务方法，保存申请信息，启动流程流转
	  * @param applicationInfo
	  */
	void submit(ApplicationInfo applicationInfo);

	
	 /**
	  * 查询我的任务列表
	 * @param user 
	  * @return
	  */
	List<TaskView> findMyTaskViewList(User currentUser);


	 /**
	  * 通过applicationId取得ApplicationInfo对象
	  * @param applicationId
	  * @return
	  */
	ApplicationInfo getApplicationInfoById(Long applicationId);


	 /**
	  * 审批处理
	  *  1.保存审批信息
	  *  2.完成当前任务
	  *  3.维护申请的状态
	  * @param approveInfo
	  * @param taskId
	 * @param outcome 
	 *  //表示本活动要使用那条transition离开本活动，如果为null,就使用默认的transition离开本活动
	  */
	void approve(ApproveInfo approveInfo, String taskId, String outcome);

    
	/**
	 * 获得指定的taskId下的所有transition分支
	 * @param taskId
	 * @return
	 */
	Collection<String> getOutComesByTaskId(String taskId);

     /**
      * 根据指定的applicationId获取所有的ApproveInfo
      * @param applicationId
      * @return
      */
	List<ApproveInfo> findApproveInfoByApplicationId(Long applicationId);

}
