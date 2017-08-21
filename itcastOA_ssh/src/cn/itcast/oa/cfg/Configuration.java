package cn.itcast.oa.cfg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class Configuration {

	
	 private static int pageSize;
	 
	 
	 static{
		 
		 Properties props = new Properties();
		 
		  try {
			  
			/*String path =  Configuration.class.getClassLoader().getResource("configuration.properties").getPath();
			InputStream is = new FileInputStream(path);
			//System.out.println(is.toString());
			props.load(is);
			is.close();*/
			pageSize = 10;
			 
		} catch (Exception e) {
			e.printStackTrace();
			
		} 
		  
	 }
	 
	  //取得当前页数
	 public static int getPageSize(){
		return pageSize;
	 }
	 
	
}
