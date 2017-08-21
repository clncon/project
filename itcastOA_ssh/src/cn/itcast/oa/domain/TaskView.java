package cn.itcast.oa.domain;

import org.jbpm.api.task.Task;

public class TaskView {
	
	
	private Task task;
	private ApplicationInfo applicationInfo;
	
	public TaskView(){}

	public TaskView(Task task, ApplicationInfo applicationInfo) {
		super();
		this.task = task;
		this.applicationInfo = applicationInfo;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public ApplicationInfo getApplicationInfo() {
		return applicationInfo;
	}

	public void setApplicationInfo(ApplicationInfo applicationInfo) {
		this.applicationInfo = applicationInfo;
	}
	
	
	

}
