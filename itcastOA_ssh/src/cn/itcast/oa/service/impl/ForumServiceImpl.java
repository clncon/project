package cn.itcast.oa.service.impl;



import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.baseImpl.BaseDaoImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.service.ForumService;

@Service
public class ForumServiceImpl extends BaseDaoImpl<Forum> implements ForumService {

	@Override
	public void moveUp(Long id) {
		//取得当前的forum对象和它对应的上面的那个forum对象
		 Forum forum = getById(id);
		 //取得当前forum对象上面的那个对象
		 Forum other = (Forum) getSession().createQuery//
				 ("From Forum f WHERE f.position<? ORDER BY f.position DESC")//
				 .setParameter(0, forum.getPosition())
				 .setFirstResult(0)//
				 .setMaxResults(1)//
				 .uniqueResult();
		
		 //如果other为空，就不做处理
		 if(other==null){
			 return;
		 }
		 //交换两个对象的position的值
		 int temp;
		 temp = forum.getPosition();
		 forum.setPosition(other.getPosition());
		 other.setPosition(temp);
		 
		 //保存到数据库
		  update(forum);
		  update(other);
		 

		
	}

	@Override
	public void moveDown(Long id) {
		//取得当前的forum对象和它对应的下面的那个forum对象
		 Forum forum = getById(id);
		 //取得当前forum对象下面的那个对象
		 Forum other = (Forum) getSession().createQuery//
				 ("From Forum f WHERE f.position>? ORDER BY f.position ASC")//
				 .setParameter(0, forum.getPosition())
				 .setFirstResult(0)//
				 .setMaxResults(1)//
				 .uniqueResult();
		
		 //如果other为空，就不做处理
		 if(other==null){
			 return;
		 }
		 //交换两个对象的position的值
		 int temp;
		 temp = forum.getPosition();
		 forum.setPosition(other.getPosition());
		 other.setPosition(temp);
		 
		 //保存到数据库
		  update(forum);
		  update(other);
		 

		
	}

	@Override
	public void save(Forum forum) {
		
		//设置position为最大值
		 super.save(forum);
		 int position = forum.getId().intValue();
		 //给forum设置position值
		 forum.setPosition(position);
		 
		 //因为session是持久化层的,所以会自动的更新数据库
		 
	}

	 //重写findAll()方法使其支持按position升序排序
	@SuppressWarnings("unchecked")
	@Override
	public List<Forum> findAll() {
		
		return getSession().createQuery//
				("FROM Forum f ORDER BY f.position ASC ")//
				.list();
	}
	
}
