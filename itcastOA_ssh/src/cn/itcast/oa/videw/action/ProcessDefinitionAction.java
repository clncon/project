package cn.itcast.oa.videw.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.baseaction.BaseAction;
import cn.itcast.oa.service.ProcessDefinitionService;

import com.opensymphony.xwork2.ActionContext;


@SuppressWarnings("serial")
@Component
@Scope("prototype")
public class ProcessDefinitionAction extends BaseAction{

	
	  private String key ;
	  private String id;
	  
	  private File upload;//上传的文件
	  private InputStream inputStream;//下载用的流对象
	  
	  
	 /**
	  * 显示所有最新版本的流程定义
	  */
	public String list() throws Exception {
	List<ProcessDefinition> processDefinitionList = processDefinitionService.findAllLastVersions();
	
      ActionContext.getContext().put("processDefinitionList",processDefinitionList);		
		return "list";
	}
	/**
	 * 删除指定的流程定义Id的所有同类的流程定义
	 */
	public String delete() throws Exception {
		
		 //自己再做一次解码
		 key=URLDecoder.decode(key, "UTF-8");
		processDefinitionService.deleteByKey(key);
		return "toList";
	}
	/**
	 * 进入到部署新的流程定义的页面
	 */
	public String addUI() throws Exception {
		return "addUI";
	}
	/**
	 * 部署新的流程定义
	 */
	public String add() throws Exception {
		
		 
		if(upload==null){
			return "toList";
		}
		 InputStream in = new FileInputStream(upload);
		 ZipInputStream zip = new ZipInputStream(in);
		processDefinitionService.ProcessDefinition_zip(zip);
		return "toList";
	}
	/**
	 * 下载并显示流程图
	 */
	public String downloadProcessImage() throws Exception {
		
		 //自己再做一次解码
		 id = URLDecoder.decode(id,"UTF-8");
	    inputStream = processDefinitionService.getProcessImageResource(id);
	  
		return "downloadProcessImage";
		
		
	}
	
	//=============================================================================================
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	

}
