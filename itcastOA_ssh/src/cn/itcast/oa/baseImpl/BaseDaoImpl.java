package cn.itcast.oa.baseImpl;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.cfg.Configuration;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.util.HqlHelper;
@SuppressWarnings("unchecked")

@Transactional
public class BaseDaoImpl<T> implements BaseDao<T>{

	
	  @Resource
	 private SessionFactory sessionFatory;
	  private Class clazz;
	  
	  public BaseDaoImpl(){
		 //通过反射得到T的真实对象
		   //从子类中获得父类泛型
		  ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		   //通过子类泛型获得父类泛型中的真实类型
		   this.clazz =  (Class) pt.getActualTypeArguments()[0];
		  System.out.println("=====>"+clazz.getName());
	  }
	@Override
	public void save(T entity) {
		getSession().save(entity);
		
	}

	@Override
	public void delete(Long id) {
		T entity = getById(id);
		getSession().delete(entity);
	}

	@Override
	public void update(T entity) {
		 getSession().update(entity);
		
	}

	
	@Override
	public T getById(Long id) {
		
		 if(id==null){
			 return null;
		 }
		return  (T) getSession().get(clazz,id);
		
	}

	@Override
	public List<T> getByIds(Long[] ids) {
		if(ids==null||ids.length==0){
			return Collections.EMPTY_LIST;
		}
		return (List<T>) getSession().createQuery("FROM "+
		clazz.getSimpleName()+" WHERE id IN(:ids)")//
		.setParameterList("ids", ids)//
		.list();
	}

	@Override
	public List<T> findAll() {
		 
		return (List<T>) getSession()//
				.createQuery("FROM "+clazz.getSimpleName()+"")//
				.list();
	}

	protected  Session getSession(){
		 return sessionFatory.getCurrentSession();
	}
	
	
	@Override
	@Deprecated
	public PageBean getPageBean(int pageNum, String queryListHQL,
			Object[] parameters) {
		
		 System.out.println("------->BaseDaoImpl.getPageBean()");
		//取得pageSize
		 int pageSize = Configuration.getPageSize();
		//查询该版块下的所有的主题
		   Query listQuery = getSession().createQuery(queryListHQL);
		if(parameters!=null&& parameters.length>0){ 
		    for(int i=0;i<parameters.length;i++){
		    	
		    	listQuery.setParameter(i,parameters[i]);
		    }
		}
		 		listQuery.setFirstResult((pageNum-1)*pageSize);
		 		listQuery.setMaxResults(pageSize);
				List recordList = listQuery.list();
		
		 //查询所有的在该版机主题的数量
		  Query queryCount= getSession().createQuery("SELECT COUNT(*) "+queryListHQL);
		  if(parameters!=null && parameters.length>0){
		   for(int i=0;i<parameters.length;i++){
			   
			   queryCount.setParameter(i,parameters[i]);
		   }
		  }
				 Long count =  (Long) queryCount.uniqueResult();
		  
		  
		return new PageBean<Topic>(pageNum,pageSize,recordList,count.intValue());
		
	}
	@Override
	public PageBean getPageBean(int pageNum, HqlHelper hqlHelper) {
		 System.out.println("------->BaseDaoImpl.getPageBean()");
		      List<Object> parameters = hqlHelper.getParameters();
			//取得pageSize
			 int pageSize = Configuration.getPageSize();
			//查询该版块下的所有的主题
			   Query listQuery = getSession().createQuery(hqlHelper.getQueryListHql());
			if(parameters!=null&& parameters.size()>0){ 
			    for(int i=0;i<parameters.size();i++){
			    	
			    	listQuery.setParameter(i,parameters.get(i));
			    }
			}
			 		listQuery.setFirstResult((pageNum-1)*pageSize);
			 		listQuery.setMaxResults(pageSize);
					List recordList = listQuery.list();
			
			 //查询所有的在该版机主题的数量
			  Query queryCount= getSession().createQuery(hqlHelper.getQueryCountHql());
			  if(parameters!=null && parameters.size()>0){
			   for(int i=0;i<parameters.size();i++){
				   
				   queryCount.setParameter(i,parameters.get(i));
			   }
			  }
					 Long count =  (Long) queryCount.uniqueResult();
			  
			  
			return new PageBean<Topic>(pageNum,pageSize,recordList,count.intValue());
			
	}
	
	
	

}
