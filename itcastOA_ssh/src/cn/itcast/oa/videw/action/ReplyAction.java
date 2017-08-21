package cn.itcast.oa.videw.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.baseaction.ModelBaseAction;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ReplyAction extends ModelBaseAction<Reply> {
	
	  private Long topicId;
	 
	/**
	 *进入添加回复的页面 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		//准备数据：topic
		 Topic topic = topicservice.getById(topicId);
		 ActionContext.getContext().put("topic", topic);
		return "addUI";
	}
	/**
	 *创建一个回复
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		 //表单数据，由model对象自动封装
//		model.setContent(content);
//		model.setFaceIcon(faceIcon);
//		model.setTitle(title);
		
		
		model.setTopic(topicservice.getById(topicId));
		
		//在当前服务器端处理的数据
		model.setAuthor(getCurrentUser());
		model.setIpAddress(ServletActionContext.getRequest().getRemoteAddr().toString());
		model.setPostTime(new Date());
		
		//保存数据
		 replyservice.save(model);
		return "toTopicShow";
	}

	 //----------------------------------------------------------------------------
	
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

}
