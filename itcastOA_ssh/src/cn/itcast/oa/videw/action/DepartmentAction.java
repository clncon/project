package cn.itcast.oa.videw.action;


import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import cn.itcast.oa.baseaction.ModelBaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.util.DepartmentUtil;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class DepartmentAction extends ModelBaseAction<Department>{
	
	  private Long parentId;
	   
	  
	  /**
	   * 查询所有的department
	   * @return
	   */
	  //列表：列表页面只显示一层的（同级的）部门数据，默认显示最顶级的部门列表
	 public String list(){
		 List<Department> listDepartment=null;
	   if(parentId==null){
		   listDepartment = departmentservice.findTopList();
	   }else{
		  listDepartment = departmentservice.findChildren(parentId);
		  Department parent = departmentservice.getById(parentId);
		  ActionContext.getContext().put("Parent",parent);
	   }
	   ActionContext.getContext().put("ListDepartment", listDepartment);
		 return "list";
	 }
	  /**
	   * 删除一个department
	   * @return
	   */
	 public String delete(){
		   //删除数据库中一条记录
		 departmentservice.delete(model.getId());
		//System.out.println("fff");
		 return "toList";
	 }
	 
	  /**
	   * 添加一个department
	   * @return
	   */
	 public String add(){
//		 
//		 department department = new department();
//		 department.setdepartmentName(departmentName);
//		 department.setDescription(description);
		 if(parentId!=null){
			 Department department= departmentservice.getById(parentId);
			 model.setParent( department);
		 }
		  //保存到数据库
		 departmentservice.save(model);
		 return "toList";
	 }
	 
	  /**
	   * 转发到添加页面
	   * @return
	   */
	  public String addUI(){
//		  //准备数据
//		  List<Department> listDepartment = departmentservice.findAll();
//		   ActionContext.getContext().put("ListDepartment",listDepartment);
		  
		  //准备数据(树状结构)
		   List<Department> listTopDepartment = departmentservice.findTopList();
		  List<Department> listDepartment = DepartmentUtil.getAllDepartments(listTopDepartment,null);
		    System.out.println(listDepartment.toString());
		   ActionContext.getContext().put("ListDepartment",listDepartment);
		  return "saveUI";
	  }
	   /**
	    * 转发到编辑页面
	    * @return
	    */
	  public String editUI(){
		   //取得id对应的对象
		   Department department = departmentservice.getById(model.getId());
		   //>>处理上级部门、
		   if(department.getParent()!=null){
		   parentId=department.getParent().getId();
		   }
		    //保存到对象栈
		   ActionContext.getContext().getValueStack().push(department);
//		   //>>准备部门数据
//		   
//		     List<Department> listDepartment = departmentservice.findAll();
//		     ActionContext.getContext().put("ListDepartment",listDepartment);
		   
		   
		   //>>准备部门数据(树状图) 
		   List<Department> listTopDepartment = departmentservice.findTopList();
			  List<Department> listDepartment = DepartmentUtil.getAllDepartments(listTopDepartment,department);
			    System.out.println(listDepartment.toString());
			   ActionContext.getContext().put("ListDepartment",listDepartment);
		    
		  return "saveUI";
	  }
	   /**
	    * 编辑一个department的信息
	    * @return
	    */
	 public String edit(){
		   //取得id对应的对象
		 Department department = departmentservice.getById(model.getId());
		   //更细department的信息
           department.setDepartmentName(model.getDepartmentName());
		 department.setDescription(model.getDescription());
		  //>>处理上级部门的数据
		   
		      department.setParent(departmentservice.getById(parentId));
		   
		   //更新到数据库
		  departmentservice.update(department);
		 return "toList";
	 }
//==================================================================================================
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	 

}
