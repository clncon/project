package cn.itcast.oa.service.impl;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.service.ProcessDefinitionService;

@Service
@Transactional
public class ProcessDefinitionServiceImpl implements
		ProcessDefinitionService {
	
	
      @Resource
	  private ProcessEngine processEngine;
      
      /**
       *查询所有流程定义的最新版本
       */
	@Override
	public List<ProcessDefinition> findAllLastVersions() {
		
		//1.查询
		  List<ProcessDefinition> list =processEngine.getRepositoryService()//
				       .createProcessDefinitionQuery()//
				       .orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION)//
				       .list();
		  
		 //使用map来过滤出同类中最新的版本的流程定义
		  
		  Map<String,ProcessDefinition> map = new HashMap<String,ProcessDefinition>();
		   for(ProcessDefinition pd : list){
			   map.put(pd.getKey(),pd);
		   }
		   
		   //返回map的值
		return new ArrayList<ProcessDefinition>(map.values());
	}

	
	 /**
	  * 删除指定的key下的所有的流程定义
	  */
	@Override
	public void deleteByKey(String key) {
		
		//1.查询所有的指定的key的流程定义
		
		  List<ProcessDefinition> list = processEngine//
				   .getRepositoryService()//
				   .createProcessDefinitionQuery()//
				   .processDefinitionKey(key)//
				   .list();
		  
		  //循环删除集合中的流程定义
		 for(ProcessDefinition pd:list){
			 processEngine.getRepositoryService().deleteDeploymentCascade(pd.getDeploymentId());
		 }
	}

    
	  /**
	   * 使用zip压缩文件的方式来部署文件
	   */
	@Override
	public void ProcessDefinition_zip(ZipInputStream zip) {
		
		processEngine.getRepositoryService()// 
		   .createDeployment()//
		   .addResourcesFromZipInputStream(zip)//
		   .deploy();
		 
		
	}

	
	
     /**
      * 获得指定的流程定义的流程图
      */
	@Override
	public InputStream getProcessImageResource(String processDefinitionId) {
		 //1.查询指定的流程id的流程对象
	    ProcessDefinition pd =  processEngine.getRepositoryService()//
	    		   .createProcessDefinitionQuery()//
	    		   .processDefinitionId(processDefinitionId)//
	    		   .uniqueResult();
		
	    //获得指定流程定义的图片资源的名字
	     String name = pd.getImageResourceName();
	     
	     //获得指定的流程定义的资源的流对象
	     InputStream in = processEngine.getRepositoryService()//
	    		   .getResourceAsStream(pd.getDeploymentId(), name);
		return in;
	}

	
}
