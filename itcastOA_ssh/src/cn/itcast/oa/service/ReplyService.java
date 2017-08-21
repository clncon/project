package cn.itcast.oa.service;


import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.baseImpl.BaseDaoImpl;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

public interface ReplyService extends BaseDao<Reply>{

	@Deprecated
	List<Reply> findReplyByTopic(Topic topic);

	PageBean getPageBean(int pageNum, Topic topic);

}
