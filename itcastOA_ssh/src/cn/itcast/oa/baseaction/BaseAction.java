package cn.itcast.oa.baseaction;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.ApplicationTemplateService;
import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.service.FlowService;
import cn.itcast.oa.service.ForumService;
import cn.itcast.oa.service.PrivilegeService;
import cn.itcast.oa.service.ProcessDefinitionService;
import cn.itcast.oa.service.ReplyService;
import cn.itcast.oa.service.RoleService;
import cn.itcast.oa.service.TopicService;
import cn.itcast.oa.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
    
	     @Resource
	     protected DepartmentService departmentservice;
	     @Resource
	     protected RoleService roleservice;
	     @Resource
	     protected UserService userservice;
	     @Resource
	     protected PrivilegeService privilegeservice;
	     @Resource
	     protected ForumService forumservice;
	     @Resource
	     protected TopicService topicservice;
	     @Resource
	     protected ReplyService replyservice;
	     @Resource
	     protected ProcessDefinitionService processDefinitionService;
	     @Resource
	     protected ApplicationTemplateService applicationTemplateService;
	     @Resource
	     protected FlowService flowService;
	     
	     
	     
	     /**
		    * 取得当前的用户对象
		    * @return
		    */
	    	protected User getCurrentUser(){
			  return (User) ActionContext.getContext().getSession().get("user");
			}
	    	

			//--------------------------------------------------------------
	    	//取得分页的当前页的页数，默认为1
	    	protected int pageNum=1;
	    	
	    	public int getPageNum() {
	    		return pageNum;
	    	}
	    	public void setPageNum(int pageNum) {
	    		this.pageNum = pageNum;
	    	}
	    	
	    	
	    	 /**
	    	  * 保存文件到服务器端
	    	  * @param upload
	    	  * @return
	    	  */
	    	 protected String saveUploadFile(File upload) {
	 			//使得日期按照/yyyy/mm/dd方式生成
	 			SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");
	 			
	 			 //取得上传文件在服务器的存储路径
	 			String basePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload_files");
	 			System.out.println(basePath);
	 			 //取得以当前日期生成的子路径
	 			String subPath = sdf.format(new Date());
	 			System.out.println(sdf);
	 			
	 		    File dir = new File(basePath+subPath);
	 		     //判断目录是否存在，如果不存在那么就新建
	 		    if(!dir.exists()){
	 		    	dir.mkdirs();
	 		    }
	 		    //拼接路径
	 			String path = basePath+subPath+"/"+UUID.randomUUID().toString();
	 			 
	 			
	 			//移动文件到服务器上的目录中
	 		    upload.renameTo(new File(path));
	 			return path;
	 		}
	  
}
