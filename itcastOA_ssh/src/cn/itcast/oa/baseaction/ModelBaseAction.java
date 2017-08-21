package cn.itcast.oa.baseaction;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.service.ForumService;
import cn.itcast.oa.service.PrivilegeService;
import cn.itcast.oa.service.ReplyService;
import cn.itcast.oa.service.RoleService;
import cn.itcast.oa.service.TopicService;
import cn.itcast.oa.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@SuppressWarnings({ "unchecked", "serial" })

public class ModelBaseAction<T>  extends BaseAction implements ModelDriven<T>{

    protected  T model;
	 
    
    
 		public ModelBaseAction(){
 			  //取得bean对象的字节码对象
 			     //取得父类的子类的泛型对象
 			ParameterizedType pt  = (ParameterizedType)//
 					this.getClass().getGenericSuperclass();
 			      //取得泛型对应的特化的对象
 			    @SuppressWarnings("rawtypes")
 				Class clazz = (Class) pt.getActualTypeArguments()[0];
 			  //创建字节码对象实例对象
 			    try {
 					model = (T) clazz.newInstance();
 				} catch (Exception e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				} 
 		  }
 		@Override
 		public T getModel() {
 			// TODO Auto-generated method stub
 			return (T) model;
 		}
 		
 	//--------------------------------------------------------
 		  
    	
}
