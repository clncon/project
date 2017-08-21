package com.itheima.elec.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.itheima.elec.dao.IElecUserDao;
import com.itheima.elec.domain.ElecUser;


@Repository(IElecUserDao.SERVICE_NAME)
public class ElecUserDaoImpl  extends CommonDaoImpl<ElecUser> implements IElecUserDao {

	/**使用sql语句的联合查询，查询用户表，关联数据字典表*/
	public List<ElecUser> findCollectionByConditionNoPageWithSql(String condition,
			final Object[] params, Map<String, String> orderby) {
		//sql语句
		String sql = "SELECT o.userID,o.logonName,o.userName,a.ddlName,o.contactTel,o.onDutyDate,b.ddlName " +
					 " FROM elec_user o " + 
					 " INNER JOIN elec_systemddl a ON o.sexID = a.ddlCode AND a.keyword = '性别' " +
					 " INNER JOIN elec_systemddl b ON o.postID= b.ddlCode AND b.keyword = '职位' " +
					 " WHERE 1=1";
		//将Map集合中存放的字段排序，组织成ORDER BY o.textDate ASC,o.textName DESC
		String orderbyCondition = this.orderbySql(orderby);
		//添加查询条件
		final String finalSql = sql + condition + orderbyCondition;

		List<Object[]> list = this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//当投影查询的字段用到相同名称的字段，为了区分字段，使用标量查询
				Query query = session.createSQLQuery(finalSql)
									.addScalar("o.userID")
									.addScalar("o.logonName")
									.addScalar("o.userName")
									.addScalar("a.ddlName")
									.addScalar("o.contactTel")
									.addScalar("o.onDutyDate")
									.addScalar("b.ddlName");
				if(params!=null && params.length>0){
					for(int i=0;i<params.length;i++){
						query.setParameter(i, params[i]);
					}
				}
				return query.list();
			}
			
		});
		
		//将List<Object[]>转换成List<ElecUser>
		List<ElecUser> userList = new ArrayList<ElecUser>();
		if(list!=null && list.size()>0){
			for(Object [] o:list){
				ElecUser elecUser = new ElecUser();
				//o.userID,o.logonName,o.userName,a.ddlName,o.contactTel,o.onDutyDate,b.ddlName
				elecUser.setUserID(o[0].toString());
				elecUser.setLogonName(o[1].toString());
				elecUser.setUserName(o[2].toString());
				elecUser.setSexID(o[3].toString());
				elecUser.setContactTel(o[4].toString());
				elecUser.setOnDutyDate((Date)o[5]);
				elecUser.setPostID(o[6].toString());
				userList.add(elecUser);
			}
		}
		return userList;
	}

	/**将Map集合中存放的字段排序，组织成
	 * ORDER BY o.textDate ASC,o.textName DESC*/
	private String orderbySql(Map<String, String> orderby) {
		StringBuffer buffer = new StringBuffer("");
		if(orderby!=null && orderby.size()>0){
			buffer.append(" ORDER BY ");
			for(Map.Entry<String, String> map:orderby.entrySet()){
				buffer.append(map.getKey()+" "+map.getValue()+",");
			}
			//在循环后，删除最后一个逗号
			buffer.deleteCharAt(buffer.length()-1);
		}
		return buffer.toString();
	}
	
	/**  
	* @Name: chartUser
	* @Description: 统计用户分配情况
	* @Author: 刘洋（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-10（创建日期）
	* @Parameters: String zName：传递的数据类型
	* 				 String eName：字段名称
	* @Return:List<Object[]>：数据集合
	*/
	public List<Object[]> chartUser(String zName, String eName) {
		final String sql = "SELECT b.keyword,b.ddlName,COUNT(b.ddlCode) FROM elec_user a " +
				 	 " INNER JOIN elec_systemddl b ON a."+eName+" = b.ddlCode AND b.keyword='"+zName+"' " +
				 	 " WHERE a.isDuty='1' " +
				 	 " GROUP BY b.keyword,b.ddlName " +
				 	 " ORDER BY b.ddlCode ASC ";
		List<Object[]> list = this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
			}
			
		});
		return list;
	}
}
