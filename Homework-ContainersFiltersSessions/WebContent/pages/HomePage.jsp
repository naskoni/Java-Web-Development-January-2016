<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="http://jwd.bg/tags" %>  

<c:set var="username" value="<%= request.getSession().getAttribute(\"username\")%>" />
<ct:Page>
	<jsp:attribute name="title">
		HomePage
	</jsp:attribute>
	<jsp:attribute name="projectName">
		Homework-ContainersFiltersSessions
	</jsp:attribute>
	<jsp:attribute name="version">
		1.23
	</jsp:attribute>
	
	<jsp:body>
		<c:choose>
			<c:when test='${username == null}'>
				Current user: not logged
			</c:when>
			<c:otherwise>Current user: ${username}</c:otherwise>
		</c:choose>		
	</jsp:body>	
</ct:Page>
