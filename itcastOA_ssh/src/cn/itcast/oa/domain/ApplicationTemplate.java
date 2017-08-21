package cn.itcast.oa.domain;

import java.util.HashSet;
import java.util.Set;

public class ApplicationTemplate {
	
	private Long id;
	private String templateName;
	private String processDefinitionKey;
	private String path;//文件上传到服务器存储的路径
	private Set<ApplicationInfo> applicationInfos = new HashSet<ApplicationInfo>();
	
	public ApplicationTemplate(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Set<ApplicationInfo> getApplicationInfos() {
		return applicationInfos;
	}

	public void setApplicationInfos(Set<ApplicationInfo> applicationInfos) {
		this.applicationInfos = applicationInfos;
	}
	
	
	

}
