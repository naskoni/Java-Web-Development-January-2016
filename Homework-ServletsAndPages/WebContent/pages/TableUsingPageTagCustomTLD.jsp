<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="http://jwd.bg/tags" %>
  
<c:set var="date" value="<%= new Date() %>" />
<ct:Page>
	<jsp:attribute name="title">TableUsingPageTagCustomTLD</jsp:attribute>
	<jsp:attribute name="showDate">true</jsp:attribute>
	<jsp:attribute name="version">1.02</jsp:attribute>
	<jsp:body>
		<ct:VerticalHeadingTable>
			<jsp:attribute name="row1-title">
					Course
				</jsp:attribute>
				<jsp:attribute name="row1-value">
					Web Development Basics
				</jsp:attribute>
				<jsp:attribute name="row2-title">
					Date
				</jsp:attribute>
				<jsp:attribute name="row2-value">
					${date}
				</jsp:attribute>
		</ct:VerticalHeadingTable>
	</jsp:body>	
</ct:Page>
