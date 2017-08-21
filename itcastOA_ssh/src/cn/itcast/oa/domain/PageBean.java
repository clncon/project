package cn.itcast.oa.domain;

import java.util.List;


public class PageBean<T> {
	 //传递的参数或者是配置的参数
	 private int  currentPage;    //当前页
	 private int  pageSize  ;     //每页的记录数
	 
	 //从数据库中查询可得
	 private List<T>  recordList;     //页的数据列表
	 private int  recordCount;    //总记录数
	 
	  //由计算可得到的结果
	 private int  pageCount ;     //总页数
	 private int  beginPageIndex; //页码开始的索引处(包含)
	 private int  endPageIndex; //  页码结束的索引处(包含)

	  public PageBean(){}

	  
	   
	public PageBean(int currentPage, int pageSize, List<T> recordList,
			int recordCount) {
		
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordList = recordList;
		this.recordCount = recordCount;
		
		//计算总页数的简单方法
		this.pageCount = (recordCount+pageSize-1)/pageSize;
		//计算开始页面的索引
		  
		 if(pageCount<10){
			 //如果总页数小于10,则全部显示
			  this.beginPageIndex=1;
			  this.endPageIndex = pageCount;
		 }else{
			 //按照前面：currentPage-4+currentPage+ 后面:currentPage+5;来显示
			  this.beginPageIndex = currentPage-4;
			  this.endPageIndex = currentPage+5;//比如：当前页为 7，则beginPageIndex=3,endPageIndex=12
			                                 //即3~12之间
			   
			   
			   if(beginPageIndex<1){
				   //如果当前页前面已经不足以显示4页的话，就显示1~10页
				   this.beginPageIndex=1;
				   this.endPageIndex=10;
			   }else if(endPageIndex>pageCount){
				   //如果当前页面的后面5页已经超过总页数的话，就显示最后一页到前10页
				   this.endPageIndex=pageCount;
				   this.beginPageIndex=pageCount-9;
				   
				   
			   }
			   
		 }
	}



	public List<T> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
	  
	  
	  
}
