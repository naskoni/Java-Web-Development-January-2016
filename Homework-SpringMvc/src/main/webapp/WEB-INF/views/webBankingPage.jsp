<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="http://jwd.bg/tags" %>
<%@ page errorPage="exception.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>

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
	<ct:Footer projectName="Homework - Spring MVC" version="1.01"></ct:Footer>
</div>
</body>
</html>