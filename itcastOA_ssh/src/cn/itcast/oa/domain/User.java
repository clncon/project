package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

/**
 * 让所有与User有关系的类实现Serializable接口，这样就能在服务器重启后保存session数据
 * @author Administrator
 *
 */
public class User implements Serializable{
	private Long id;
	private Department department;
	private Set<Role> roles = new HashSet<Role>();
	private String loginName;
	private String realName;
	private String password;
	private String gender;
	private String phone;
	private String email;
	private String description;
	

	public User(){}

	/**
	 * 判断用户中的角色是否有该权限(使用权限名)
	 * @param privilegeName
	 * @return
	 */
	public boolean hasPrivilegeByName(String privilegeName){
		boolean flag = false;
		
		  //如果是超极管理员
		   if(isAdmin()){
			   return true;
		   }
		//这是针对一般用户的
		 //取得所有的Role对象
        for(Role role : getRoles()){
        	//判断每个role的权限是否包含该权限
        	 for(Privilege privilege : role.getPrivileges()){
        		 if(privilegeName.equals(privilege.getPrivilegeName())){
        			 flag=true;
        		 }
        	 }
        }
		return flag;
	}
	 /**
	  * 判断用户中的角色是否有该权限(使用权限名)
	  * @param url
	  * @return
	  */
	public boolean hasPrivilegeByURL(String url){
		boolean flag = false;
		
		//如果是超极管理员
		if(isAdmin()){
			return true;
		}
		//如果URL是以UI结尾的话
        if(url.endsWith("UI")){
        	url = url.substring(0,url.length()-2);
        	//System.out.println(url);
        }
        
         @SuppressWarnings("unchecked")
		List<String> allPrivilegeUrls = (List<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls");
         if(!allPrivilegeUrls.contains(url)){
        	 //如果不需要控制的功能则直接放行
        	  return true;
         }
		//这是针对一般用户的
		//取得所有的Role对象
		for(Role role : getRoles()){
			//判断每个role的权限是否包含该权限
			for(Privilege privilege : role.getPrivileges()){
				if(url.equals(privilege.getUrl())){
					flag=true;
				}
			}
		}
		return flag;
	}
	 /**
	  * 判断该用户是不是超级管理员
	  * @return
	  */
	  
	private boolean isAdmin() {
		if("admin".equals(loginName)){
			return true;
		}
		return false;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	


}
