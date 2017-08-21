package cn.itcast.oa.videw.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.baseaction.ModelBaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.util.HqlHelper;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TopicAction extends ModelBaseAction<Topic> {

	private Long forumId;
	private Integer typeId;
	
	/**
	 *设置帖子的类型
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public String setTopicType() throws Exception {
		//通过topicId来设置帖子的类型
		if(typeId!=null){
			//取得要设置类型的主题
			Topic topic = topicservice.getById(model.getId());
			switch (typeId) {
			case 0:
				 topic.setType(topic.TYPE_NORMAL);
				break;
			case 1:
				topic.setType(topic.TYPE_BEST);
				break;
			case 2:
				topic.setType(topic.TYPE_TOP);
				break;

			default:
				break;
			}
			
			//更新到数据库
			topicservice.update(topic);
		}
		return "toShow";
	}
	/**
	 *显示单个主题+回帖列表
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		 //准备数据:topic
		  Topic topic = topicservice.getById(model.getId());
		  ActionContext.getContext().put("topic", topic);
		 //准备回复数据:ListReply
//		  List<Reply> listReply = replyservice.findReplyByTopic(topic);
//		  ActionContext.getContext().put("ListReply", listReply);
		  
		  //准备回复的数据:ListReply(带分页的)
//		   PageBean<Reply> pageBean =  replyservice.getPageBean(pageNum,topic);
//		  ActionContext.getContext().getValueStack().push(pageBean);
		  
		  new HqlHelper(Reply.class).buildPageBeanForStruts2(pageNum, replyservice);
		  
		return "show";
	}
	
	/**
	 * 转到一个添加主题的页面
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		 //准备数据 forum
		 Forum forum = forumservice.getById(forumId);
		 ActionContext.getContext().getValueStack().push(forum);
		return "addUI";
	}
	/**
	 *添加一个主题
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//表单提交的数据,已经封装成功
//		model.setContent(content);
//		model.setTitle(title);
//		model.setFaceIcon(faceIcon);
		
		model.setForum(forumservice.getById(forumId));
		
		
		//当前在服务器段就可以处理的
		
	    model.setAuthor(getCurrentUser());//作者
	    model.setIpAddress(ServletActionContext.getRequest().getRemoteAddr().toString());//ip地址
	    model.setPostTime(new Date());//创建主题时间
	    
	    //放到一个业务方法中去处理
//	    model.setLastReply(lastReply);
//	    model.setReplyCount(replyCount);
//	    model.setType(type);
//	    model.setLastUpdateTime(lastUpdateTime);
	    
	    //保存model
	    topicservice.save(model);
		return "toShow";
	}

	
	
     /**
      * 进入移动板块的页面
      * @return
      * @throws Exception
      */
	public String moveUI() throws Exception {
		//准备数据：topic
		  Topic topic = topicservice.getById(model.getId());
		 ActionContext.getContext().put("topic", topic);
		//准备板块列表
		 List<Forum> listForum = forumservice.findAll();
		 ActionContext.getContext().put("ListForum", listForum);
		return "moveUI";
	}
	
	   /**
	    * 移动到其他的板块
	    * @return
	    * @throws Exception
	    */
	public String moveToForum() throws Exception {
		//获得需要修改的板块
		Forum forum = forumservice.getById(forumId);
		//根据当期的主题的id取得Topic
		Topic topic = topicservice.getById(model.getId());
		
		//修改当前主题所属的板块
		  //因为只有topic会维护和forum的关系，所以将topic的所属板块改掉就可以了
		    topic.setForum(forum);
		    
		 //更新到数据库
		  topicservice.update(topic);
		 
		return "toShow";
	}
	 
	
	//--------------------------------------------------
	
	
	

	public Long getForumId() {
		return forumId;
	}
	
	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	

}
