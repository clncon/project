package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public class Privilege implements Serializable{
	
	
	private Long id;
	private Set<Role> roles = new HashSet<Role>();
	private Privilege parent;
	private Set<Privilege> children;
	private String url;
	private String privilegeName;
	private String icon;//顶级菜单的图标的名字
	public Privilege(){}
	
	

	public Privilege(String privilegeName,String url,String icon,Privilege parent) {
		this.parent = parent;
		this.url = url;
		this.privilegeName = privilegeName;
		this.icon=icon;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Privilege getParent() {
		return parent;
	}

	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	public Set<Privilege> getChildren() {
		return children;
	}

	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}



	public String getIcon() {
		return icon;
	}



	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	

}
