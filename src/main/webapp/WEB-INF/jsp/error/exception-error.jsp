<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setStatus(HttpServletResponse.SC_OK);
	String accept = request.getHeader("Accept");
	if(accept.indexOf("application/json")!=-1||accept.indexOf("text/javascript")!=-1||accept.indexOf("text/plain")!=-1){
		%>
		<c:if  test="${not empty exception.errCode}">
			  <c:choose>
				  <c:when test="${exception.errCode=='sessionOut'}">
				  	<%=accept.indexOf("application/json")!=-1?"{\"error\":\"sessionOut\"}":"sessionOut" %>
				  </c:when>
				  <c:when test="${exception.errCode=='NoAccess'}">
				    <%=accept.indexOf("application/json")!=-1?"{\"error\":\"NoAccess\"}":"NoAccess" %>
				  </c:when>
			  </c:choose>
		  </c:if>
		<%
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
	    <div class="title">页面出现错误</div>
	    <div class="content">
	      <c:if  test="${not empty exception.errCode}">
			  <c:choose>
				  <c:when test="${exception.errCode=='sessionOut'}">
<!-- 				  	<script type="text/javascript"> -->
<!-- 						window.setTimeout(function(){ -->
<%-- 							window.top.location="${pageContext.request.contextPath}/jsp/login"; --%>
<!-- 						},2000); -->
<!-- 					</script> -->
				    ${exception.errMsg}
				  </c:when>
				  <c:when test="${exception.errCode=='NoAccess'}">
				    ${exception.errMsg}
				  </c:when>
			  </c:choose>
		  </c:if>
	    </div>
	</div>
</body>
</html>
<%} %>
