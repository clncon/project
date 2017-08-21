<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>edit</title>
  </head>
  
  <body>
     <s:form action="roleAction_edit.action">
     <s:hidden name="id" value="%{id}"></s:hidden>
         名称：  <s:textfield name="roleName"></s:textfield><br/>
          说明<s:textarea name="description"></s:textarea><br/>
           <s:submit value="提交"></s:submit>
       </s:form>
  </body>
</html>
