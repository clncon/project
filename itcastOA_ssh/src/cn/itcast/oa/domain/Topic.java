package cn.itcast.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Topic extends Article{
	 /*普通帖*/
	public static final int TYPE_NORMAL = 0;
	/*精华帖*/
	public static final int TYPE_BEST = 1;
	/*置顶帖*/
	public static final int TYPE_TOP = 2;
   private Forum forum;//关联板块
   private Set<Reply> replies =new HashSet<Reply>();//关联回复
   private int type;//主题类型(普通，精华，置顶)
   private int replyCount;//回复数
   private Reply lastReply;//最新的回复
   private Date lastUpdateTime;//主题最后一次更新时间(如果没有回复就是新主题建立的时间，不然就是最新的回复时间)
   
   public Topic(){}

public Forum getForum() {
	return forum;
}

public void setForum(Forum forum) {
	this.forum = forum;
}

public Set<Reply> getReplies() {
	return replies;
}

public void setReplies(Set<Reply> replies) {
	this.replies = replies;
}

public int getReplyCount() {
	return replyCount;
}

public void setReplyCount(int replyCount) {
	this.replyCount = replyCount;
}

public Reply getLastReply() {
	return lastReply;
}

public void setLastReply(Reply lastReply) {
	this.lastReply = lastReply;
}

public Date getLastUpdateTime() {
	return lastUpdateTime;
}

public void setLastUpdateTime(Date lastUpdateTime) {
	this.lastUpdateTime = lastUpdateTime;
}

public int getType() {
	return type;
}

public void setType(int type) {
	this.type = type;
}
   
   
}
