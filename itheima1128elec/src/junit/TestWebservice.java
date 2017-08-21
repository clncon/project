package junit;

import org.junit.Test;

import com.itheima.elec.domain.xsd.ElecSystemDDL;
import com.itheima.elec.webservice.FindSystemByKeyword;
import com.itheima.elec.webservice.FindSystemByKeywordResponse;
import com.itheima.elec.webservice.IWebSystemDDLServiceSkeleton;

public class TestWebservice {

	/**测试接口的实现类*/
	@Test
	public void testWebService(){
		IWebSystemDDLServiceSkeleton ddlServiceSkeleton = new IWebSystemDDLServiceSkeleton();
		FindSystemByKeyword findSystemByKeyword = new FindSystemByKeyword();
		findSystemByKeyword.setArgs0("性别");
		FindSystemByKeywordResponse byKeywordResponse = ddlServiceSkeleton.findSystemByKeyword(findSystemByKeyword);
		ElecSystemDDL [] ddl = byKeywordResponse.get_return();
		if(ddl!=null && ddl.length>0){
			for(ElecSystemDDL systemDDL:ddl){
				System.out.println(systemDDL.getKeyword()+"   "+systemDDL.getDdlCode()+"   "+systemDDL.getDdlName());
			}
		}
		
	}
	
}
