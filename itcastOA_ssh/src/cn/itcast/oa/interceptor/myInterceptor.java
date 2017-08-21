package cn.itcast.oa.interceptor;



import org.apache.struts2.ServletActionContext;

import cn.itcast.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class myInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// System.out.println("...拦截之前");
		 //取得用户
		User user = (User) ActionContext.getContext().getSession().get("user");
		//取得url,并取消掉应用程序的前缀
		  String privilegeUrl = null;
		  //取得url应该是(nameSpace+privilegeUrl)
		String namespace =   invocation.getProxy().getNamespace();
		String actionName =   invocation.getProxy().getActionName();
		 
		if(namespace.endsWith("/")){
			privilegeUrl = namespace+actionName;
			
		}
		else{
			privilegeUrl = namespace+"/"+actionName;
		}
		
		//如果privilgeUrl是以"/"开头的，去掉那个"/"
		 if(privilegeUrl.startsWith("/")){
			 privilegeUrl = privilegeUrl.substring(1);
		 }
		 
		 System.out.println("---->interceptor:"+privilegeUrl);
		  //如果没有登录
		  if(user==null){
			  if(privilegeUrl.startsWith("userAction_login")){//如果是在登录的过程中(userAction_loginUI,userAction_login)
				   //放行
				 return  invocation.invoke();
			  }else{
				   //如果不是去登录的页面，就转到loginUI页面
				  return "loginUI";
			  }
			  
		  }else{
			    //如果登录了
			    if(user.hasPrivilegeByURL(privilegeUrl)){
			    	 //如果有权限，就放行
			    	return invocation.invoke();
			    }else{
			    	 //如果没有权限，就提示你无权访问
			    	return "noPrivilegeError";
			    }
			  
		  }
	
	
	}

	

}
