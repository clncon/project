<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
    <%@ include file="/WEB-INF/jsp/public/header.jspf"%>
    <META http-equiv=Content-Type CONTENT="text/html; charset=gbk" />
	<TITLE>Itcast OA</TITLE>
	<LINK HREF="${pageContext.request.contextPath}/style/blue/login.css" type=text/css rel=stylesheet />
	 <script type="text/javascript">
	    $(function(){
	    
	     document.forms[0].loginName.focus();
	 
	    });
	     //如果该页面被别的页面所嵌套时。刷新嵌套的页面
	       //window:当前的窗口
	       //  window:嵌套的窗口
	        //window.parent==window:表示有嵌套的窗口
	        //window.parent!=window:表示有没有嵌套的窗口
	      if(window.parent!=window){
	        window.parent.location.reload(true);
	      }
	 </script>
</HEAD>
   
<BODY LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 CLASS=PageBody >
<s:form action="userAction_login.action">
    <DIV ID="CenterAreaBg">
        <DIV ID="CenterArea">
            <DIV ID="LogoImg"><IMG BORDER="0" SRC="${pageContext.request.contextPath}/style/blue/images/logo.png" /></DIV>
            <DIV ID="LoginInfo">
                <TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 width=100%>
                   <TR>
                		<TD colspan="3"><!-- 显示错误 -->
							<font color="red"><s:fielderror/></font>
                		</TD>
                	</TR	>
                    <TR>
                        <TD width=45 CLASS="Subject"><IMG BORDER="0" SRC="${pageContext.request.contextPath}/style/blue/images/login/userId.gif" /></TD>
                        <TD>
                           <s:textfield name="loginName" cssClass="TextField" size="20"></s:textfield></TD>
                       <TD ROWSPAN="2" STYLE="padding-left:10px;"><INPUT TYPE="image" SRC="${pageContext.request.contextPath}/style/blue/images/login/userLogin_button.gif"/></TD>
                    </TR>
                    <TR>
                        <TD CLASS="Subject"><IMG BORDER="0" SRC="${pageContext.request.contextPath}/style/blue/images/login/password.gif" /></TD>
                        <TD><s:password name="password" cssClass="TextField" showPassword="false" size="20"></s:password></TD>
                    </TR>
                </TABLE>
            </DIV>
            <DIV ID="CopyRight"><A HREF="javascript:void(0)">&copy; 2010 版权所有 itcast</A></DIV>
        </DIV>
    </DIV>
</s:form>
</BODY>

</HTML>

