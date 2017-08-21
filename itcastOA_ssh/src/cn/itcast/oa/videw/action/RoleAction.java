package cn.itcast.oa.videw.action;


import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.baseaction.ModelBaseAction;
import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.domain.Role;

import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class RoleAction extends ModelBaseAction<Role>{

      
	private Long[] privilegeIds;

	  /**
	   * 查询所有的role
	   * @return
	   */
	 public String list(){
		 
		List<Role> listRole =roleservice.findAll();
		 ActionContext.getContext().put("ListRole", listRole);
		 return "list";
	 }
	  /**
	   * 删除一个role
	   * @return
	   */
	 public String delete(){
		   //删除数据库中一条记录
		 roleservice.delete(model.getId());
		//System.out.println("fff");
		 return "toList";
	 }
	 
	  /**
	   * 添加一个role
	   * @return
	   */
	 public String add(){
//		 
//		 Role role = new Role();
//		 role.setRoleName(roleName);
//		 role.setDescription(description);
		  //保存到数据库
		 roleservice.save(model);
		 return "toList";
	 }
	 
	  /**
	   * 转发到添加页面
	   * @return
	   */
	  public String addUI(){
		  
		  return "saveUI";
	  }
	   /**
	    * 转发到编辑页面
	    * @return
	    */
	  public String editUI(){
		   //取得id对应的对象
		   Role role = roleservice.getById(model.getId());
		    //保存到对象栈
		   ActionContext.getContext().getValueStack().push(role);
		  return "saveUI";
	  }
	   /**
	    * 编辑一个role的信息
	    * @return
	    */
	 public String edit(){
		   //取得id对应的对象
		 Role role = roleservice.getById(model.getId());
		   //更细role的信息
           role.setRoleName(model.getRoleName());
		 role.setDescription(model.getDescription());
		   //更新到数据库
		  roleservice.update(model);
		 return "toList";
	 }
	 /**
	  * 转发到配置权限的页面
	  * @return
	  */
	 public String setPrivilegeUI(){
	   //准备Privilege数据
		 List<Privilege> listTopPrivilege = privilegeservice.findTopList();
		 ActionContext.getContext().put("listTopPrivilege", listTopPrivilege);
	   //准备role数据
		  Role role = roleservice.getById(model.getId());
		ActionContext.getContext().put("role", role);
		 
	   //处理权限的数据
		  
		   if(role.getPrivileges()!=null){
		    int index=0;
		    privilegeIds = new Long[role.getPrivileges().size()];
		    for(Privilege p : role.getPrivileges()){
		    	privilegeIds[index++] = p.getId();
		    }
		   }
		 return "setPrivilegeUI";
	 }
	 /**
	  * 配置权限
	  * @return
	  */
	 public String setPrivilege(){
		 System.out.println(privilegeIds.length);
		   //根据privilegeIds取得所有选中的权限
		  List<Privilege> ListPrivilege = privilegeservice.getByIds(privilegeIds);
		  // System.out.println(ListPrivilege.toString());
		   //取得被设置权限的对象role
		  Role role = roleservice.getById(model.getId());
		   //让role和ListPrivilge单向关联(多对多只要单向关联)
		 role.setPrivileges(new HashSet<Privilege>(ListPrivilege));
		  
		  //保存到数据库
		   roleservice.update(role);
		 return "toList";
	 }
	
     //====================================================================================
	 
	 public Long[] getPrivilegeIds() {
			return privilegeIds;
		}
		public void setPrivilegeIds(Long[] privilegeIds) {
			this.privilegeIds = privilegeIds;
		}
}
