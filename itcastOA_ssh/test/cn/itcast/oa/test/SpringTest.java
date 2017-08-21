package cn.itcast.oa.test;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.jbpm.api.ProcessEngine;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	
	 
	 private ApplicationContext  ac 
	                   = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	  private Log log = LogFactory.getLog(getClass());
	   @Test
	   public void testLog() throws Exception{
		   log.debug("这是debug");
		   log.info("这是info信息");
			log.warn("这是warn信息");
			log.error("这是error信息");
			log.fatal("这是fatal信息");
	   }
	  //测试sessionFactory
	   @Test
	   public void testsessionFactory(){
		    SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		     
		      System.out.println(sessionFactory.toString());
	   }
	   
	     //测试事务
	     @Test
	   public void testTransaction(){
	    	  testService service = (testService) ac.getBean("testService");
	    	   service.savetwoUser();
	    	 
	    }
	     //测试Spirng环境息的ProcessEngine
	     @Test
	     public void testProcessEngine(){
	    	 
	    	 ProcessEngine processEngine = (ProcessEngine) ac.getBean("processEngine");
	    	 
	    	 System.out.println("-------->"+processEngine);
	    	 
	     }
	     
	     
	     

}
