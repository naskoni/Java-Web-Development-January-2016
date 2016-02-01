<%@ tag import="java.util.Date"%>
<%@ taglib prefix="ct" uri="http://jwd.bg/tags" %>
<%@ attribute name="title" %>
<%@ attribute name="showDate" %>
<%@ attribute name="version" %>
	
</head>
<body>
	<ct:Header title="${title}"/>
	<jsp:doBody/>	
	<ct:Footer showDate="${showDate}" version="${version}"/>	
</body>
</html>