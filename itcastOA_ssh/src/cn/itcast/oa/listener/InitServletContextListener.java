package cn.itcast.oa.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.service.PrivilegeService;

public class InitServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
           ServletContext application = sce.getServletContext();
		
		//取得Spring容器(当无法使用resource来从容器对象中取得对象时，可以使用该方法取得容器对象)
		 ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
		 
		//取得privilegeService
		 PrivilegeService privilegeservice = (PrivilegeService) ac.getBean("privilegeServiceImpl");
		//取得所有的权限信息
		List<Privilege> listTopPrivilege = (List<Privilege>) privilegeservice.findTopList();
		
		//将权限信息放入到application对象中
		application.setAttribute("ListTopPrivilege",listTopPrivilege);

		 System.out.println("=====顶级权限数据已经准备完毕=======");
		 List<Privilege> allPrivilegeUrls  = (List<Privilege>) privilegeservice.findAllPrivilegeName();
		 
		 //将权限信息放入到application对象中
		 application.setAttribute("allPrivilegeUrls",allPrivilegeUrls);
		 
		 System.out.println("=====所有的权限的URL数据已经准备完毕=======");

	}

}
