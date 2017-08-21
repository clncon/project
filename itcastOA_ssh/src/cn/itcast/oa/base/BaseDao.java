package cn.itcast.oa.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.util.HqlHelper;



public interface BaseDao<T> {
	
	  /**
	   * 保存实体
	   * @param t
	   */
	 void save(T entity); 
	  /**
	   * 根据id删除实体
	   * @param id
	   */
	 void delete(Long id);
	  /**
	   * 更新实体
	   * @param entity
	   */
	 void update(T entity);
	  /**
	   * 根据id获得实体
	   * @param id
	   * @return
	   */
	  T getById(Long id);
	    /**
	     * 根据id数组获得多个实体
	     * @param ids
	     * @return
	     */
	   List<T> getByIds(Long[] ids);
	    /**
	     * 发现所有的实体
	     * @return
	     */
	   List<T>findAll();
	    

	    /**
	     * 公共的查询分页信息的方法
	     * @param pageNum
	     * @param queryListHQL
	     * @param parameters
	     * @return
	     */
	   @Deprecated
	   PageBean getPageBean(int pageNum,String queryListHQL,Object[] parameters);
	   /**
	    * 公共的查询分页信息的方法(使用工具)
	    * @param pageNum
	    * @param queryListHQL
	    * @param parameters
	    * @return
	    */
	   PageBean getPageBean(int pageNum,HqlHelper hqlHelper);
	   
	   
	  
		 
}
