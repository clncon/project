package cn.itcast.oa.service.impl;

import java.io.File;

import org.springframework.stereotype.Service;

import cn.itcast.oa.baseImpl.BaseDaoImpl;
import cn.itcast.oa.domain.ApplicationTemplate;
import cn.itcast.oa.service.ApplicationTemplateService;

@Service
public class ApplicationTemplateServiceImpl extends BaseDaoImpl<ApplicationTemplate>
implements ApplicationTemplateService{

	@Override
	public void delete(Long id) {
		 //取得对应id的applicationTemplate
		
		  ApplicationTemplate applicationTemplate = getById(id);
		  
		  //取得该文件在服务器上的真实路径
		  
		   String path = applicationTemplate.getPath();
		   
		   //删除该文件
		    File file = new File(path);
		    if(file.exists()){
		    file.delete();
		    }
		    
		    
		    //删除数据库中的记录
		    getSession().delete(applicationTemplate);
	}

	
	 
	
	
}
