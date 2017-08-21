package cn.itcast.oa.test.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;


/**
 * 说明：不能使用多层循环的方式，因为需要能支持任意层。
 */
public class TreeViewPractice {

	/**
	 * 练习一：打印所有顶层部门及其子孙部门的信息（名称） 提示：假设有一个 打印部门树 的信息 的方法
	 * 
	 * 要求打印如下效果：
	 * 
	 * <pre>
	 * 市场部
	 * 宣传部
	 * 业务部
	 * 业务一部
	 * 业务二部
	 * 开发部
	 * 开发一部
	 * 开发二部
	 * </pre>
	 */
	          @Test
	        public void printAllDet_1(){
	        	   //取得所有的顶级部门
	        	List<Department> listDepartment = findTopLevelDepartmentList();
	        	
	        	//打印所有的顶级部门的子部门
	        	
	        	//方式一
//	          for(Department d : listDepartment){
//	        	  showTreeInfo(d);
//	          } 
	          //方式二
	           showTreeListInfo(listDepartment);
	        	
	        }

	          //方式一
	          //显示一棵树的信息
	          private void showTreeInfo(Department top) {
	        	   //打印顶级部门的信息
	        	   System.out.println(top.getName());
	        	   
	        	   //处理子部门
	        	    for(Department d : top.getChildren()){
	        	    	 showTreeInfo(d);
	        	    }
	        	   
	          }
	          
	           
	          //方式二
	          //显示多棵树的信息
	          public void showTreeListInfo(Collection<Department> listTop){
	        	    for(Department d : listTop){
	        	    	 //顶点部门
	        	    	System.out.println(d.getName());
	        	    	 //子部门
	        	    	 showTreeListInfo(d.getChildren());
	        	    }
	          }
	          
	         
	          
	/**
	 * 练习二：打印所有顶层部门及其子孙部门的信息（名称），用不同的缩进表示层次（使用全角空格）。<br>
	 * 子部门的名称前比上级部门多一个空格，最顶层部门的名字前没有空格。 提示：假设有一个打印部门集合中所有部门信息的方法
	 * 
	 * 要求打印如下效果：
	 * 
	 * <pre>
	 * ┣市场部
	 *    ┣宣传部
	 *    ┣业务部
	 *       ┣业务一部
	 *       ┣业务二部
	 * ┣开发部
	 *    ┣开发一部
	 *    ┣开发二部
	 * </pre>
	 */    @Test
	      public void printAllDet_2(){
	    	  List<Department> listDepartment = findTopLevelDepartmentList();
	    	  showTreeListInfo_2(listDepartment,"");
	      }
       
	      
	      public void showTreeListInfo_2(Collection<Department> listTop,String prefix){
      	    for(Department d : listTop){
      	    	 //顶点部门
      	    	System.out.println(prefix+"┣"+d.getName());
      	    	 //子部门
      	    	 showTreeListInfo_2(d.getChildren(),"    "+prefix);
      	    }
        }
        

	/**
	 * 结构如下：
	 * 
	 * <pre>
	 * ┣市场部
	 *    ┣宣传部
	 *    ┣业务部
	 *       ┣业务一部
	 *       ┣业务二部
	 * ┣开发部
	 *    ┣开发一部
	 *    ┣开发二部
	 * </pre>
	 * 
	 * @return 所有最顶层的部门的列表
	 */
	public static List<Department> findTopLevelDepartmentList() {
		Department dept_1_1 = new Department();
		dept_1_1.setId(new Long(11));
		dept_1_1.setName("宣传部");

		Department dept_1_2 = new Department();
		dept_1_2.setId(new Long(12));
		dept_1_2.setName("业务部");

		Department dept_1_2_1 = new Department();
		dept_1_2_1.setId(new Long(121));
		dept_1_2_1.setName("业务一部");

		Department dept_1_2_2 = new Department();
		dept_1_2_2.setId(new Long(122));
		dept_1_2_2.setName("业务二部");

		dept_1_2_1.setParent(dept_1_2);
		dept_1_2_2.setParent(dept_1_2);
		Set<Department> children_0 = new LinkedHashSet<Department>();
		children_0.add(dept_1_2_1);
		children_0.add(dept_1_2_2);
		dept_1_2.setChildren(children_0);

		// ================================

		Department dept_1 = new Department();
		dept_1.setId(new Long(1));
		dept_1.setName("市场部");

		dept_1_1.setParent(dept_1);
		dept_1_2.setParent(dept_1);
		Set<Department> children_1 = new LinkedHashSet<Department>();
		children_1.add(dept_1_1);
		children_1.add(dept_1_2);
		dept_1.setChildren(children_1);

		// ---

		Department dept_2_1 = new Department();
		dept_2_1.setId(new Long(21));
		dept_2_1.setName("开发一部");

		Department dept_2_2 = new Department();
		dept_2_2.setId((new Long(22)));
		dept_2_2.setName("开发二部");

		Department dept_2 = new Department();
		dept_2.setId(new Long(2));
		dept_2.setName("开发部");

		dept_2_1.setParent(dept_2);
		dept_2_2.setParent(dept_2);
		Set<Department> children_2 = new LinkedHashSet<Department>();
		children_2.add(dept_2_1);
		children_2.add(dept_2_2);
		dept_2.setChildren(children_2);

		// ---

		List<Department> depts = new ArrayList<Department>();
		depts.add(dept_1);
		depts.add(dept_2);
		return depts;
	}

}
