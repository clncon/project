<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ItcastOA</title>
   <%@ include file="/WEB-INF/jsp/public/header.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<frameset rows="100,*,25" framespacing="0" border="0" frameborder="0">
    <frame src="${pageContext.request.contextPath}/homeAction_top.action" name="TopMenu"  scrolling="no" noresize />
    <frameset cols="180,*" id="resize">
        <frame noresize name="menu" src="${pageContext.request.contextPath}/homeAction_left.action" scrolling="yes" />
        <frame noresize name="right" src="${pageContext.request.contextPath}/homeAction_right.action" scrolling="yes" />
    </frameset>
    <frame noresize name="status_bar" scrolling="no" src="${pageContext.request.contextPath}/homeAction_bottom.action" />
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>
