<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Bulletin</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<div class="placardView">
		<div class="placardMessage">
	 		<c:out value="${placard.message}" />
	<%--	${placard.message}	not secure! --%>
		</div>
		<div>
			<span class="placardTime">
				<c:out value="${placard.time}" />
			</span>
		</div>
	</div>
</body>
</html>