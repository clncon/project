package cn.itcast.oa.videw.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.baseaction.ModelBaseAction;
import cn.itcast.oa.domain.ApplicationTemplate;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ApplicationTemplateAction extends ModelBaseAction<ApplicationTemplate> {

	
 	 private File upload;//上传文件专用 
	 private InputStream inputStream;//下载专用的输入流
	
	/**
	 * 显示所有的模板
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
	List<ApplicationTemplate> applicationTemplateList = applicationTemplateService.findAll();
	 ActionContext.getContext().put("applicationTemplateList",//
			applicationTemplateList);
		return "list";
	}
	 /**
	  * 删除指定id的模板对象,并删除在服务器的该文件
	  * @return
	  * @throws Exception
	  */
	public String delete() throws Exception {
		applicationTemplateService.delete(model.getId());
		return "toList";
	}
	 /**
	  * 转到添加新模板的页面
	  * @return
	  * @throws Exception
	  */
	public String addUI() throws Exception {
		
		//准备数据(流程定义的key)
		
		List<ProcessDefinition> processDefinitionList = //
				processDefinitionService.findAllLastVersions();
		    ActionContext.getContext().put("processDefinitionList",//
					processDefinitionList);
		return "saveUI";
	}
	/**
	 * 添加一个新的模板
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		 //保存文件到服务器
	     String path = saveUploadFile(upload);
	      //给模型对象设置路径
			model.setPath(path);
	    //保存到数据库中
	    applicationTemplateService.save(model);
	    
	    
		return "toList";
	}
	
	
	/**
	 * 进入修改当前模板的页面
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {
		
		
       //准备数据(ProcessDefinition)
		List<ProcessDefinition> processDefinitionList = //
				processDefinitionService.findAllLastVersions();
		    ActionContext.getContext().put("processDefinitionList",//
					processDefinitionList);
		    
		    
		 //准备数据(applicationTemplate)回显
		ApplicationTemplate applicationTemplate = applicationTemplateService.getById(model.getId());
	      ActionContext.getContext().getValueStack().push(applicationTemplate);
	      
	      
		return "saveUI";
	}
	 /**
	  * 修改当前的模板
	  * @return
	  * @throws Exception
	  */
	public String edit() throws Exception {
		//根据id取出数据库中对应的applicationTemplate记录
		  ApplicationTemplate applicationTemplate =//
				  applicationTemplateService.getById(model.getId());
		  
		 //设置要更新的值
		  applicationTemplate.setTemplateName(model.getTemplateName());
		  applicationTemplate.setProcessDefinitionKey(model.getProcessDefinitionKey());
		  if(upload!=null)//如果上传了新的文件的话
		  {
			  //删除旧的文件
			   String path = applicationTemplate.getPath();
			   File file = new File(path);
			   if(file.exists()){
				   file.delete();
			   }
			   
			   //上传新的文件并且将目录重新指定为当前这个文件
			     path = saveUploadFile(upload);
			     applicationTemplate.setPath(path);
			     
			     //更新到数据库中
			      applicationTemplateService.update(applicationTemplate);
		  }
		return "toList";
	}
	
	/**
	 * 下载模板文件
	 * @return
	 * @throws Exception
	 */
	public String download() throws Exception {
		
		//准备数据(applicationTemplate)
		 ApplicationTemplate applicationTemplate = applicationTemplateService.getById(model.getId());
		  //取得模板文件的真实路径
		 String path = applicationTemplate.getPath();
		 //下载文件
		inputStream = new FileInputStream(new File(path));
		
		
		 //准备文件名
		String fileName = applicationTemplate.getTemplateName();
		 //应对中文乱码的问题
		 //方式一:
		 fileName = URLEncoder.encode(fileName, "UTF-8");
		 //方式二：
		 //fileName = new String(fileName.getBytes("gbk"),"iso-8859-1");
		ActionContext.getContext().put("fileName", fileName);
		return "download";
	}
	
	
	//====================================================================
	
	
	

	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	
}
