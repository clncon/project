package cn.itcast.oa.service;


import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Department;


public interface DepartmentService extends BaseDao<Department>{
     /**
      * 查找所有没有上级部门的部门
      * @return
      */
	List<Department> findTopList();

	  /**
	   * 查找部门id为parentId的所有子部门
	   * @param parentId
	   * @return
	   */
	List<Department> findChildren(Long parentId);

	
}
