<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% response.setStatus(HttpServletResponse.SC_OK);%>
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css"/>
</head>
<% int errorCode = (Integer)request.getAttribute("javax.servlet.error.status_code");%>
<body>
	<div class="box noMargin">
	    <div class="title">错误<%=request.getAttribute("javax.servlet.error.status_code")%></div>
	    <div class="content">
	    	<%if(404==errorCode||400==errorCode){%>
	    	<div>
	    		<p>抱歉，找不到您要的页面……</p>
	    		<p>仔细找过啦，没有发现你要找的页面。最可能的原因是： </p>
	    		<p>&nbsp;&nbsp;&nbsp;*在地址中可能存在键入错误。</p>
	    		<p>&nbsp;&nbsp;&nbsp;*当你点击某个链接时，它可能已过期。 </p>
	    		<%--<p>返回<a target="_top" href="${pageContext.request.contextPath}/home">首页 </a></p>
	    		<p>返回<a href="javascript:window.history.back();">上一页</a></p>
	    	--%></div>
	    	<%}else if(500==errorCode){%>
	    	<div>
	    		<p>抱歉，页面解析出现错误。</p>
	    		<%--<p>错误堆栈信息：</p>
					<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
					<p>${trace}</p>
					</c:forEach>--%>
	    		</div>
	    	<%}%>
	    </div>
	</div>
</body>
</html>
