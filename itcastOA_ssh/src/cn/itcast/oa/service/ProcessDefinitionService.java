package cn.itcast.oa.service;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jbpm.api.ProcessDefinition;

import cn.itcast.oa.base.BaseDao;

public interface ProcessDefinitionService{

	
	 /**
	  * 查询所有流程定义的最新版本
	  * @return
	  */
	List<ProcessDefinition> findAllLastVersions();
	
	
	  
	 /**
	  * 删除指定的key的流程定义
	  * @param key
	  */
	 void deleteByKey(String key);
	 
	 
	 /**
	  * 使用zip压缩文件来部署项目
	  */
	 void ProcessDefinition_zip(ZipInputStream zip);
	 
	  /**
	   * 获取指定的流程定义的流程图
	   * @return
	   */
	 InputStream getProcessImageResource(String processDefinitionId);
	 
	 
	 
	 

}
