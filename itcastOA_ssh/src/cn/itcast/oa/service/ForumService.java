package cn.itcast.oa.service;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Forum;

public interface ForumService extends BaseDao<Forum>{

	void moveUp(Long id);

	void moveDown(Long id);

}
