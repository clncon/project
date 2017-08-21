package cn.itcast.oa.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;


@Component("testAction")
public class testAction extends ActionSupport {

	  @Resource
	 private testService testservice;
	@Override
	public String execute() throws Exception {
		
		testservice.savetwoUser();
		System.out.println("---->testAction");
		return "success";
	}
	
	 

}
