<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>导航菜单</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<link type="text/css" rel="stylesheet" href="style/blue/menu.css" />
 <script type="text/javascript">
   function menuClick(menuElement){
      // alert("ff");
     $(".MenuLevel2").not(menuElement).next().hide();
    $(menuElement).next().toggle();
   }
 </script>
</head>
<body style="margin: 0">
<div id="Menu">
    <ul id="MenuUl">
         <%--第一级菜单 --%>
        <s:iterator value="#application.ListTopPrivilege">
        <s:if test="#session.user.hasPrivilegeByName(privilegeName)">
		        <li class="level1">
		            <div onClick="menuClick(this)" class="level1Style"><img src="style/images/MenuIcon/${icon}" class="Icon" />${privilegeName}</div>
		             <%--控制二级菜单的显示与隐藏:display: none; --%>
		            <ul style="display: none;" class="MenuLevel2">
		                <%--第二级菜单 --%>
		                  <s:iterator value="children">
		                     <s:if test="#session.user.hasPrivilegeByName(privilegeName)">
				                <li class="level2">
				                    <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /><a target="right" href="${pageContext.request.contextPath}/${url}.action">${privilegeName}</a></div>
				                </li>
		                     
		                     </s:if>
		                  </s:iterator>
		            </ul>
		        </li>
        </s:if>
        </s:iterator>
    </ul>
</div>
</body>
</html>
