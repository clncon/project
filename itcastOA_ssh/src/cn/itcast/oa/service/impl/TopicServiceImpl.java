package cn.itcast.oa.service.impl;



import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.baseImpl.BaseDaoImpl;
import cn.itcast.oa.cfg.Configuration;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.TopicService;

@Service
@SuppressWarnings("unchecked")
public class TopicServiceImpl extends BaseDaoImpl<Topic> implements TopicService {

	
	@Deprecated
	public List<Topic> findTopicByForum(Forum forum) {
		//TODO 查找某个板块的所有的主题，但是要求将查询的所有主题中的置顶帖排在最前面，将其他的帖子放在置顶帖的后面
		 //但是最新置顶帖放在所有置顶帖的前面，而最新的一般帖放在一般帖的前面
		//("FROM Topic t WHERE t.forum=? ORDER BY t.type DESC,t.lastUpdateTime DESC")
		return  getSession().createQuery//
		 ("FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END)"
		 		+ "DESC,t.lastUpdateTime DESC")//
		 .setParameter(0, forum)
		 .list();
		
	}

	@Override
	public void save(Topic topic) {
		//1.设置topic的属性
//		    topic.setLastReply(null);//默认为null
//		    topic.setType(0);//默认为普通
		   // topic.setReplyCount(0);//默认0
		
		 
		    topic.setLastUpdateTime(topic.getPostTime());//取主题创建的时间
		getSession().save(topic);
		
		 //2.维护特殊变量
		
		  Forum forum = topic.getForum();
		  forum.setTopicCount(forum.getTopicCount()+1);//主题数
		  forum.setArticleCount(forum.getArticleCount()+1);//文章数
		  forum.setLastTopic(topic);//最新的主题
		  
		  //更新当前的forum的信息
		   getSession().update(forum);
		
		
		
	}

	 @Deprecated
	public PageBean getPageBean(int pageNum, Forum forum) {
		//取得pageSize
		 int pageSize = Configuration.getPageSize();
		//查询该版块下的所有的主题
		 List<Topic> listTopic =  getSession().createQuery//
				 ("FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END)"
		 		+ "DESC,t.lastUpdateTime DESC")//
		 		.setParameter(0, forum)
		 		.setFirstResult((pageNum-1)*pageSize)
		 		.setMaxResults(pageSize)
				 .list();
		 
		 //查询所有的在该版机主题的数量
		  Long count=  (Long) getSession().createQuery//
				  ("SELECT COUNT(*) FROM Topic t WHERE t.forum=?")//
				  .setParameter(0, forum)
				  .uniqueResult();
		  
		  
		return new PageBean<Topic>(pageNum,pageSize,listTopic,count.intValue());
	}
	
	

	
}
