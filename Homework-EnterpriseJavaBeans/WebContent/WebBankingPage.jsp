<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="http://jwd.bg/tags" %>
<%@ page errorPage="Exception.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="username" value="<%= request.getSession().getAttribute(\"username\")%>" />
<c:set var="balance" value="<%= request.getSession().getAttribute(\"balance\")%>" />
<c:set var="currency" value="<%= request.getSession().getAttribute(\"currency\")%>" />

<!DOCTYPE html>
<html>
<ct:HeaderMeta title="First Private Web Bank"></ct:HeaderMeta>
<body>
<div class="container">
	<ct:Header title="First Private Web Bank"></ct:Header>
	<ct:Form>
	<jsp:body>
		<c:choose>
			<c:when test='${username == null}'>
				<center><strong>Current customer: not logged. </strong></center>
			</c:when>
			<c:otherwise><center><strong>Customer: ${username}, balance: ${balance} ${currency}</strong></center></c:otherwise>
		</c:choose>		
	</jsp:body>
	</ct:Form>		
	<ct:Footer projectName="Homework-EnterpriseJavaBeans" version="1.23"></ct:Footer>
</div>
</body>
</html>