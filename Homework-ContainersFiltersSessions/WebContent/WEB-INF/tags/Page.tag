<%@ tag import="java.util.Date"%>
<%@ taglib prefix="ct" uri="http://jwd.bg/tags" %>
<%@ attribute name="title" %>
<%@ attribute name="projectName" %>
<%@ attribute name="version" %>
	
</head>
<body>
	<ct:Header title="${title}"/>
	<jsp:doBody/>	
	<ct:Footer projectName="${projectName}" version="${version}" />			
</body>
</html>