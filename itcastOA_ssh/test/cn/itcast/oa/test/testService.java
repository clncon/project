package cn.itcast.oa.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.domain.User;




@Service("testService")
public class testService {
	
	
	     @Resource
	    private SessionFactory sessionFactory;
	  @Transactional
	 public void savetwoUser(){
		 Session session = sessionFactory.getCurrentSession();
		   session.save(new User());
		    //int i=10/0;//这里会抛出异常
		    session.save(new User());
		 
	 }
	  @Test
	  public void testSaveUser_23(){
		  Session session = sessionFactory.getCurrentSession();
		  for(int i=1;i<=23;i++){
			  User user = new User();
			  user.setRealName("测试-"+i);
			  session.save(user);
		  }
	  }

}
