package cn.itcast.oa.util;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.PageBean;

public class HqlHelper {
	
	
	private String FromClause;  //From子句，必须
	private String WhereClause =""; //Where子句，可选 
	private String orderByClause =""; //orderBy子句，可选
	
	
	//参数列表
	private List<Object> parameters = new ArrayList<Object>();
	
	
	 /**
	  * 生成From子句，默认的别名为o
	  * @param clazz
	  */
	public HqlHelper(Class clazz){
		FromClause = "FROM "+clazz.getSimpleName()+" o";
	}
	/**
	 * 生成From子句，带别名
	 * @param clazz
	 */
	public HqlHelper(Class clazz,String alias){
		FromClause = "FROM "+clazz.getSimpleName()+" "+alias;
	}
	
	
	 /**
	  * 生成Where子句
	  * @param condition
	  * @param parameters
	  * @return
	  */
	public HqlHelper addCondition(String condition,Object...params){
		
		//拼接
		 if( WhereClause.length()==0){
			 WhereClause =  " WHERE "+condition;
		 }else{
			 WhereClause += " AND "+condition;
		 }
		 //保存参数
		  if(params!=null&&params.length>0){
			 for(Object obj : params){
				 parameters.add(obj);
			 }
			  
		  }
		return this;
		
		
	}
	
	/**
	 * 如果第一个参数文true就执行，生成Where子句
	 * @param append
	 * @param condition
	 * @param parameters
	 * @return
	 */
	public HqlHelper addCondition(boolean append,String condition,Object...params){
		
		 if(append){
			 addCondition(condition, params);
		 }
		return this;
		
		
	}
	
	 /**
	  * 生成orderByClause子句
	  * @param propertyName
	  * @param isAsc
	  * @return
	  */
	public HqlHelper addOrder(String propertyName,boolean isAsc){
	   if(orderByClause.length()==0){
		   orderByClause = " ORDER BY "+propertyName+( isAsc ? " ASC" : " DESC");
	   }else{
		   
		   orderByClause += ", "+propertyName+( isAsc ? " ASC" : " DESC");
	   }
		
		return this;
		
	}
	/**
	 * 第一参数为true,生成orderByClause子句
	 * @param append
	 * @param propertyName
	 * @param isAsc
	 * @return
	 */
	public HqlHelper addOrder(boolean append,String propertyName,boolean isAsc){
		 if(append){
			 addOrder(propertyName, isAsc);
		 }
		
		return this;
		
	}
	
	 
	 /**
	  * 获取生成的查询的HQL语句
	  * @return
	  */
	public String  getQueryListHql(){
		
		return FromClause+WhereClause+orderByClause;
		
	}
	
	 /**
	  * 获取生成查询总记录的hql语句
	  * @return
	  */
	public String getQueryCountHql(){
		return "SELECT COUNT(*) "+FromClause+WhereClause;
	}
	
	 /**
	  * 获取参数列表，和HQL过滤条件的?一一对应
	  * @return
	  */
	public List<Object> getParameters(){
	           return  parameters;	
	}
	
	
	 /**
	  * 查询并准备分页(放到栈顶)
	  * @param pageNum
	  * @param service
	  * @return
	  */
	public  HqlHelper buildPageBeanForStruts2(int pageNum,BaseDao<?> service){
		System.out.println("--------->HqlHelper.buildPageBeanForStruts2()");
		
		PageBean pageBean  = service.getPageBean(pageNum,this);
		ActionContext.getContext().getValueStack().push(pageBean);
		return null;
		
	}
     	
}
