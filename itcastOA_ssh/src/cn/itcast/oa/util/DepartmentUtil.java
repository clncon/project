package cn.itcast.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.itcast.oa.domain.Department;

public class DepartmentUtil {
	
	 /**
	  *遍历部门数，得到所有的部门列表，修改名称并以分割层次的形式来显示
	  * @param topDepartmentList
	  * @return
	  */
	 public static List<Department> getAllDepartments(List<Department> topDepartmentList,Department removeDepartment){
		  //新建一个用于存放新结果的集合(副本)
		 List<Department> listDepartment = new ArrayList<Department>();
		  //遍历所有的部门并修改名称
		   walkDepartmentTrees(topDepartmentList," ┣ ",listDepartment,removeDepartment);
		return listDepartment ;
		 
	 }

	   /**
	    * 遍历部门数，修改所有的部门的名字让部门显示有层次感
	    * @param topDepartmentList
	    * @param prefix
	 * @param listDepartment 
	 * @param removeDepartment //需要被删除的部门当在遍历时
	 *  //该部门和他的子部门都不应该存在集合中
	    */
	private static void walkDepartmentTrees(Collection<Department> topDepartmentList,
			String prefix, List<Department> listDepartment, Department removeDepartment) {
		     
		      for(Department top : topDepartmentList){
		    	  
		    	
		    	   //处理本部门及其同级的部门
		    	  if(removeDepartment!=null &&( top.getId()==removeDepartment.getId())){
		    		  continue;
		    	  }
		    	  
		    	  //创建一个副本对象
		    	   Department copy = new Department();
		    	  
		    	  copy.setId(top.getId());
		    	  copy.setDepartmentName(prefix+top.getDepartmentName());
		    	   //将副本加入到集合中
		    	  listDepartment.add(copy);
		    	  //递归调用遍历子部门
		    	   walkDepartmentTrees(top.getChildren(),"　"+prefix,listDepartment, removeDepartment);
		      }
				
		
		
	}

}
