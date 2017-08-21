package cn.itcast.oa.domain;

import java.util.HashSet;
import java.util.Set;

public class Forum {
	
	private Long id;
	private String forumName;
	private String description; 
    private Integer position;
    private Set<Topic> topics = new HashSet<Topic>();//存储主题集合
    private int topicCount;//主题数
    private int articleCount;//文章数(主题数+回复数)
    private Topic lastTopic;//最新的主题
    public Forum(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	public int getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}

	

	public Topic getLastTopic() {
		return lastTopic;
	}

	public void setLastTopic(Topic lastTopic) {
		this.lastTopic = lastTopic;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
   
     
}
