package cn.itcast.oa.videw.action;

import java.util.HashSet;
import java.util.List;

import javax.ws.rs.PUT;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.baseaction.ModelBaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.util.DepartmentUtil;
import cn.itcast.oa.util.HqlHelper;

import com.opensymphony.xwork2.ActionContext;



@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserAction extends ModelBaseAction<User>{

	    private Long departmentId;
	    private Long[] roleIds;
	     
	      /**
		   * 查询所有的role
		   * @return
		   */
		 public String list(){
		    List<User> listUser = userservice.findAll();
		ActionContext.getContext().put("ListUser",listUser);
			 
			 new HqlHelper(User.class).buildPageBeanForStruts2(pageNum, userservice);
			 return "list";
		 }
		  /**
		   * 删除一个role
		   * @return
		   */
		 public String delete(){
			// System.out.println(model.getId());
			 userservice.delete(model.getId());
			 return "toList";
		 }
		 
		  /**
		   * 添加一个role
		   * @return
		   */
		 public String add(){

			 System.out.println(departmentId);
			 //取得选择的部门
			  Department department = departmentservice.getById(departmentId);
			 //取得RoleIds
			  List<Role>  listRole = roleservice.getByIds(roleIds);
			  //关联关系
			  model.setDepartment(department);
			  model.setRoles(new HashSet<Role>(listRole));
			  //保存到数据库中
			   userservice.save(model);
			 return "toList";
		 }
		 
		  /**
		   * 转发到添加页面
		   * @return
		   */
		  public String addUI(){
			  
//			  //准备Department数据
//			  List<Department> listDepartment = departmentservice.findAll();
//			  ActionContext.getContext().put("ListDepartment",listDepartment);
			  
			   //准备Department数据(树状结构)
			   List<Department> listTopDepartment = departmentservice.findTopList();
			    List<Department> listAllDepartment = DepartmentUtil.getAllDepartments(listTopDepartment, null);
			    ActionContext.getContext().put("ListDepartment", listAllDepartment);
			  //准备Role数据
			   List<Role> listRole = roleservice.findAll();
			   ActionContext.getContext().put("ListRole", listRole);
			   //ActionContext.getContext().put("listRole", listRole);
			  return "saveUI";
		  }
		   /**
		    * 转发到编辑页面
		    * @return
		    */
		  public String editUI(){
			  //准备回显的数据
			  User user = userservice.getById(model.getId());
			  //放入到值栈中
			  ActionContext.getContext().getValueStack().push(user);
			  //>>处理岗位(回显岗位数据)
			    roleIds = new Long[user.getRoles().size()];
			     int index=0;
			    for(Role role:user.getRoles()){
			    	roleIds[index++]=role.getId();
			    }
			   //>>处理部门(回显部门的数据)
			    if(user.getDepartment()!=null){
			    	departmentId = user.getDepartment().getId();
			    	
			    }
			    //准备Department数据(树状结构)
				   List<Department> listTopDepartment = departmentservice.findTopList();
				    List<Department> listAllDepartment = DepartmentUtil.getAllDepartments(listTopDepartment,user.getDepartment());
				    ActionContext.getContext().put("ListDepartment", listAllDepartment);
			    //准备岗位的数据
			        List<Role> listRole = roleservice.findAll();
			        ActionContext.getContext().put("ListRole",listRole);
			  return "saveUI";
		  }
		   /**
		    * 编辑一个role的信息
		    * @return
		    */
		 public String edit(){
			 System.out.println(model.getId());
			 User user = userservice.getById(model.getId());
			   System.out.println(user);
			   user.setEmail(model.getEmail());
			   user.setGender(model.getGender());
			   user.setLoginName(model.getLoginName());
			   user.setRealName(model.getRealName());
			   user.setPhone(model.getPhone());
			   user.setDescription(model.getDescription());
			 //取得选择的部门
			  Department department = departmentservice.getById(departmentId);
			 //取得RoleIds
			  List<Role>  listRole = roleservice.getByIds(roleIds);
			  //关联关系
			  user.setDepartment(department);
			  user.setRoles(new HashSet<Role>(listRole));
			  //保存到数据库中
			   userservice.update(user);
			 return "toList";
		 }
		 
		  /**
		   * 初始化密码为1234
		   * @return
		   */
		 public String initPassword(){
			  //取得用户对象
		     User user = userservice.getById(model.getId());
		      //初始化用户密码为1234
		     user.setPassword(DigestUtils.md5Hex("1234"));
		      //更新到数据库
		       userservice.update(user);
			 return "toList";
		 }
		 /**
		  * 转到登录页面
		  * @return
		  */
		 public String loginUI(){
			 
			 return "loginUI";
		 }
		 /**
		  * 登录用户
		  * @return
		  */
		 public String login(){
			 User user = userservice.findloginNameAndPassword(model.getLoginName(),model.getPassword());
			 
			 if(user==null){
				 //如果用户不存在
				  addFieldError("login", "用户名或者密码不正确");
				  return "loginUI";
			 }else{
				 //如果用户存在
				  ActionContext.getContext().getSession().put("user",user);
				 return "toIndex";
			 }
		 }
		 /**
		  * 登出用户
		  * @return
		  */
		 public String logout(){
			 ActionContext.getContext().getSession().remove("user");
			 return "logout";
		 }
		
		 
		 
//====================================================================
		 public Long getDepartmentId() {
			 return departmentId;
		 }
		 public void setDepartmentId(Long departmentId) {
			 this.departmentId = departmentId;
		 }
		 public Long[] getRoleIds() {
			 return roleIds;
		 }
		 public void setRoleIds(Long[] roleIds) {
			 this.roleIds = roleIds;
		 } 
}
