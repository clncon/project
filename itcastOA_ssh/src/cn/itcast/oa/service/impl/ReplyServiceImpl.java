package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.baseImpl.BaseDaoImpl;
import cn.itcast.oa.cfg.Configuration;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.ReplyService;

@SuppressWarnings("unchecked")
@Service
public class ReplyServiceImpl extends BaseDaoImpl<Reply> implements ReplyService{

	@Deprecated//注释表示该方法已经被遗弃
	public List<Reply> findReplyByTopic(Topic topic) {
		return  getSession().createQuery//
		("FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
		.setParameter(0, topic)
		.list();
		
		
	}

	@Override
	public void save(Reply reply) {
		//1.保存回复信息
		
		getSession().save(reply);
		
		//2.维护特殊属性的关系
		
		//取得所属的主题
		Topic topic = reply.getTopic();
		//取得所属的板块
		Forum forum = topic.getForum();
		
		//维护关系
		forum.setArticleCount(forum.getArticleCount()+1);
		topic.setReplyCount(topic.getReplyCount()+1);
		topic.setLastReply(reply);
		topic.setLastUpdateTime(reply.getPostTime());
		
		//更新关系
		getSession().update(topic);
		getSession().update(forum);
	}

	@Override
	public PageBean getPageBean(int pageNum, Topic topic) {
		  //取得每页的记录数(从配置文件中获取)
		  int pageSize = Configuration.getPageSize();
		  System.out.println(pageSize);
		  //查询每页显示的记录数
		List<Reply> listReply = getSession().createQuery//
		("FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
		.setParameter(0, topic)
		.setFirstResult((pageNum-1)*pageSize)
		.setMaxResults(pageSize)
		.list();
		 //查询回复的总记录数
		 Long count = (Long) getSession().createQuery//
				  ("SELECT COUNT(*) FROM Reply r WHERE r.topic=?")//
				  .setParameter(0, topic)
				  .uniqueResult();
		return new PageBean<Reply>(pageNum,pageSize,listReply,count.intValue());
	}
	
	

}
