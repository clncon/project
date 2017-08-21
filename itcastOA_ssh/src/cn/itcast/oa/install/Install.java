package cn.itcast.oa.install;



import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.domain.User;

/**
 * 安装程序(初始化数据)
 * @author Administrator
 *
 */
//TODO 这个安装程序应该做成 setup.bat的批处理文件
@Component("install")
@SuppressWarnings("unused")
public class Install {
  
	    @Resource
	  private SessionFactory sessionFactory;
	 
	    
	 @Transactional
	 public  void install(){
		 Session session = sessionFactory.getCurrentSession();
		 //创建超极管理员
		     User user = new User();
		      user.setLoginName("admin");
		      user.setRealName("超极管理员");
		      user.setPassword(DigestUtils.md5Hex("admin"));
		       //保存超极用户信息
		         session.save(user);
		 //初始化权限数据
		      
		   Privilege menu,menu1,menu2,menu3,menu4,menu5;
		    //==================================================系统管理================================================
		   menu = new Privilege("系统管理",null,"FUNC20082.gif",null);
		      //部门管理
		     menu1 = new Privilege("部门管理","departmentAction_list",null,menu);
		      //岗位管理
		     menu2 = new Privilege("岗位管理","roleAction_list",null,menu);
		     //用户管理
		    menu3 = new Privilege("用户管理","userAction_list",null,menu);		     
		     //=========保存权限=====
		       session.save(menu);
		       session.save(menu1);
		       session.save(menu2);
		       session.save(menu3);
		        //部门下属的权限
		     session.save(new Privilege("部门列表","departmentAction_list",null,menu1));
		     session.save(new Privilege("部门添加","departmentAction_addUI",null,menu1));
		     session.save(new Privilege("部门删除","departmentAction_delete",null,menu1));
		     session.save(new Privilege("部门修改","departmentAction_edit",null,menu1));
		     //岗位下属的权限
		     session.save(new Privilege("岗位列表","roleAction_list",null,menu2));
		     session.save(new Privilege("岗位添加","roleAction_addUI",null,menu2));
		     session.save(new Privilege("岗位删除","roleAction_delete",null,menu2));
		     session.save(new Privilege("岗位修改","roleAction_edit",null,menu2));
		     //用户下属的权限
		     session.save(new Privilege("用户列表","userAction_list",null,menu3));
		     session.save(new Privilege("用户添加","userAction_addUI",null,menu3));
		     session.save(new Privilege("用户删除","userAction_delete",null,menu3));
		     session.save(new Privilege("用户修改","userAction_edit",null,menu3));
		     session.save(new Privilege("用户初始化密码","userAction_initPassword",null,menu3));
		     
     //==============================论坛系统===================================================
		     
		     
		       menu = new Privilege("网上交流",null,"FUNC20064.gif",null);
		         //论坛
		       menu1 = new Privilege("论坛","forumAction_list",null,menu);
		        //论坛管理
		       menu2 = new Privilege("论坛管理","forumManageAction_list",null,menu);
		       
		       //===========保存权限========================
		          session.save(menu);
		          session.save(menu1);
		          session.save(menu2);
		          
		          
    //========================================审批管理======================================
		          menu = new Privilege("审批流转",null,"FUNC20057.gif",null);
		          menu1 = new Privilege("审批流程管理","processDefinitionAction_list",null,menu);
		          menu2 = new Privilege("表单模板管理","applicationTemplateAction_list",null,menu);
		          menu3 = new Privilege("起草审批","flowAction_applicationTemplateList",null,menu);
		          menu4 = new Privilege("待我审批","flowAction_myTaskList",null,menu);
		          menu5 = new Privilege("我的申请查询","flowAction_myApplicationList",null,menu);
		          
		          //======保存权限=========
		            session.save(menu);
		            session.save(menu1);
		            session.save(menu2);
		            session.save(menu3);
		            session.save(menu4);
		            session.save(menu5);
		       
	  } 
	public static void main(String[] args) {
		 System.out.println("------>正在初始化数据。。。");
		  //注意一定要通过容器取得对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		  Install install = (Install) ac.getBean("install");
		   System.out.println("--->正在安装。。。");
		   install.install();
		   System.out.println("------>初始化数据完成!");
		

	}

}
