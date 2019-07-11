<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.text.*"%>
<%@ page import="org.apache.commons.logging.Log" %>
<%@ page import="org.apache.commons.logging.LogFactory" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page  isErrorPage="true"%>
<%
	response.setStatus(HttpServletResponse.SC_OK);
	String accept = request.getHeader("Accept");
	if(accept.indexOf("application/json")!=-1||accept.indexOf("text/javascript")!=-1||accept.indexOf("text/plain")!=-1){
		%><%=accept.indexOf("application/json")!=-1?"{\"error\":\"error\"}":"error" %><%
	}else{
%>
<!DOCTYPE html>
<html>
<head>
<title>SMBC Smart Graphic Intelligence</title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--[if lt IE 9]><link href="css/index-less-ie9.css" media="screen" rel="stylesheet" type="text/css" /><![endif]-->
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css"/>
</head>

<body>
	<div class="box noMargin">
	    <div class="title">对不起，系统出现问题，请联系管理员或者重新刷新页面。</div>
	    <div class="content">
	      ${exception}
	      <%
			Log log = LogFactory.getLog(this.getClass());
	        log.error(exception.getMessage());
          %>
	    </div>
	</div>
</body>
</html>
<%} %>