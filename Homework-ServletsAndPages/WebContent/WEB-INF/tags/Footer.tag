<%@ tag import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="version" %>
<%@ attribute name="showDate" %>

<br /> 
<div style="color:blue">  
    <c:if test="${showDate}">
        <%=new Date() %>
    </c:if>    
</div>
<h4 style="color:red">
	Version: ${version} 
</h4>