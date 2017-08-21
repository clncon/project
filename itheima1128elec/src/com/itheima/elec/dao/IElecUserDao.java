package com.itheima.elec.dao;

import java.util.List;
import java.util.Map;

import com.itheima.elec.domain.ElecUser;

public interface IElecUserDao extends ICommonDao<ElecUser> {
	
	public static final String SERVICE_NAME = "com.itheima.elec.dao.impl.ElecUserDaoImpl";

	List<ElecUser> findCollectionByConditionNoPageWithSql(String condition,
			Object[] params, Map<String, String> orderby);

	List<Object[]> chartUser(String zName, String eName);

	
	
}
