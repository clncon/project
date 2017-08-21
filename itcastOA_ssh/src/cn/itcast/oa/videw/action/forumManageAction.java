package cn.itcast.oa.videw.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.baseaction.ModelBaseAction;
import cn.itcast.oa.domain.Forum;



@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class forumManageAction extends ModelBaseAction<Forum>{

	/**
	 * 删除板块的功能
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		forumservice.delete(model.getId());
		return "toList";
	}
	
	/**
	 * 显示所有的板块
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		
		List<Forum> listForum = forumservice.findAll();
		ActionContext.getContext().put("ListForum", listForum);
		return "list";
	}
	
	 /**
	  * 进入添加页面
	  * @return
	  * @throws Exception
	  */
	public String addUI() throws Exception {
		
		return "saveUI";
	}
	/**
	 * 添加板块的功能
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
	
		forumservice.save(model);
		
		return "toList";
	}
	/**
	 * 进入修改页面
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {
		 Forum forum = forumservice.getById(model.getId());
		 ActionContext.getContext().getValueStack().push(forum);
		return "saveUI";
	}
	/**
	 * 修改板块的功能
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		 //1.通过id取得需要更新的对象
		 Forum forum =  forumservice.getById(model.getId());
		 //2.更新对象中的数据
		   forum.setForumName(model.getForumName());
		   forum.setDescription(model.getDescription());
		 //3.更新到数据库
		    forumservice.update(forum);
		return "toList";
	}
	 
	 /**
	  * 上移板块的功能
	  * @return
	  * @throws Exception
	  */
	public String moveUp() throws Exception{
		//取得当前的forum对象和它对应的上面的那个forum对象
		 Forum forum = forumservice.getById(model.getId());
		 Forum other = null;
		 //交换
		 forumservice.moveUp(model.getId());
		 return "toList";
	}
	/**
	 * 下移板块的功能
	 * @return
	 * @throws Exception
	 */
	public String moveDown() throws Exception{
		forumservice.moveDown(model.getId());
		return "toList";
	}
	 
}
