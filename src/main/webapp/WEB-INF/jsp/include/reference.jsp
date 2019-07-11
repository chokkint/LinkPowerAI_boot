<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	var operateList = '<c:forEach items="${PAGE_OPERATE_LIST}" var="operate" varStatus = "i"><c:if test="${!i.first}">,</c:if>#${operate.operatecode}</c:forEach>';
	$(".moduleTitle .titleButton").not(""+operateList).remove();
</script>