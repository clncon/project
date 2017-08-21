<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>配置权限</title>
    <%@ include file="/WEB-INF/jsp/public/header.jspf"%>
     <script language="javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/file.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css" />
</head>

  <script type="text/javascript">
     $(function(){
    	   //给所有的选项加上点击事件
    	     $("input[name='privilegeIds']").click(function(){
    	    	 //选中上级部门时同时选中所有的下级部门
    	    	  $(this).siblings("ul").find("input").attr("checked",this.checked);
    	    	   //alert("ff");
    	    	   
    	    	 //选中一个下级部门，将他的所有直系的上级部门选中
    	    	 if(this.checked){
    	    		 //自己选中或取消时，将自己的直系的上级也同时选中或删除
    	    	   $(this).parents("li").children("input").attr("checked",this.checked);
    	    		 
    	    	 }
    	    	 else {
    	    		 
    	    		 //当取消一个权限时，如果同级的没有权限被选中时，那么就把他的上级权限也给取消了
    	    		      //如果当前级别的权限全部没有选中，那么取消上级权限的选项
    	    		  if($(this).parent().siblings("li").children("input:checked").size()==0){
    	    			  $(this).parent().parent().siblings("input").attr("checked",false);
    	    			   var start = $(this).parent().parent();
    	    			    //如果每一级的权限都没被选中，那么取消最高级别的权限
    	    			  if(start.parent().siblings("li").children("input:checked").size()==0){
    	    				 start.parent().parent().siblings("input").attr("checked",false);
    	    			  }
    	    		  }
    	    	 }
    	    	 
    	     });
    	 
     });
   
  </script>  
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 配置权限
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="roleAction_setPrivilege?id=%{id}" method="post">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 正在为【${role.roleName}】配置权限 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<!--表头-->
					<thead>
						<tr align="LEFT" valign="MIDDLE" id="TableTitle">
							<td width="300px" style="padding-left: 7px;">
								<!-- 如果把全选元素的id指定为selectAll，并且有函数selectAll()，就会有错。因为有一种用法：可以直接用id引用元素 -->
								<input type="CHECKBOX" id="cbSelectAll" onClick="$('input[name=privilegeIds]').attr('checked',this.checked)"/>
								<label for="cbSelectAll">全选</label>
							</td>
						</tr>
					</thead>
                   
			   		<!--显示数据列表-->
					<tbody id="TableData">
						<tr class="TableDetail1">
							<!-- 显示权限树 -->
							<td>
							   <!--这种方式不好，因为freemarket不熟悉，而且这样改会导致所有的应用了该freemarket的标签的样式都发生变化-->
                                 <%-- <s:checkboxlist list="#listAllPrivilege" name="privilegeIds"
                                  listKey="id" listValue="privilegeName"
                                 ></s:checkboxlist> --%>
                                 
                                 
                         <!-- 手动写html代码来实现回显和换行 -->
                     <%--    <s:iterator value="#listAllPrivilege">
                           <input type="checkbox" name="privilegeIds" value="${id}" id="cb_${id}"
                              <s:property  value="%{id in privilegeIds? 'checked':''}"/>
                            >
                            <label for="cb_${id}">${PrivilegeName}</label><br/>
                        </s:iterator>--%> 
                        
                          <ul id="root">
                              <!--第一级-->
                              
                              <s:iterator value="#listTopPrivilege">
                                 <li>
                                  <input type="checkbox" name="privilegeIds" value="${id}" id="cb_${id}"
                                    <s:property value="%{id in privilegeIds? 'checked':''}"/>
                                  />
                                  <label for="cb_${id}"><span class="folder">${PrivilegeName}</span></label>
                                    <ul>
                                       <!--第二级-->
                                        <s:iterator value="children">
                                            <li>
                                            <input type="checkbox" name="privilegeIds" value="${id}" id="cb_${id}"
                                            <s:property value="%{id in privilegeIds? 'checked':''}"/>
                                               />
                                            <label for="cb_${id}"><span class="folder">${PrivilegeName}</span></label>
                                           <ul>
                                              <!-- 第三级 -->
                                              <s:iterator value="children">
                                                 <li>
                                                   <input type="checkbox" name="privilegeIds" value="${id}" id="cb_${id}"
                                            <s:property value="%{id in privilegeIds? 'checked':''}"/>
                                               />
                                            <label for="cb_${id}"><span class="folder">${PrivilegeName}</span></label>
                                                 </li>
                                              </s:iterator>
                                           </ul>
                                           </li>
                                        </s:iterator>
                                    </ul>
                                  </li>
                              </s:iterator>
                          </ul>     
                                 
                            </td>
						</tr>
					</tbody>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <s:a action="roleAction_list"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></s:a>
        </div>
    </s:form>
</div>

<div class="Description">
	说明：<br />
	1，选中一个权限时：<br />
	&nbsp;&nbsp;&nbsp;&nbsp; a，应该选中 他的所有直系上级。<br />
	&nbsp;&nbsp;&nbsp;&nbsp; b，应该选中他的所有直系下级。<br />
	2，取消选择一个权限时：<br />
	&nbsp;&nbsp;&nbsp;&nbsp; a，应该取消选择 他的所有直系下级。<br />
	&nbsp;&nbsp;&nbsp;&nbsp; b，如果同级的权限都是未选择状态，就应该取消选中他的直接上级，并递归向上做这个操作。<br />

	3，全选/取消全选。<br />
	4，默认选中当前岗位已有的权限。<br />
</div>
  <script type="text/javaScript">
       $("#root").treeview();
    
  </script>
</body>
</html>
