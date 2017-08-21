package cn.itcast.oa.service;


import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.baseImpl.BaseDaoImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Topic;

public interface TopicService extends BaseDao<Topic>{

	
	 /**
	  * 查找某个板块的所有的主题，但是要求将查询的所有主题中的置顶帖排在最前面，将其他的帖子放在置顶帖的后面
	  * 但是最新置顶帖放在所有置顶帖的前面，而最新的一般帖放在一般帖的前面
	  * @param forum
	  * @return
	  */
	 @Deprecated
	List<Topic> findTopicByForum(Forum forum);

	 @Deprecated
	PageBean getPageBean(int pageNum, Forum forum);

}
