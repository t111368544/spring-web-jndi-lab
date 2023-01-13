<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>Bulletin</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<div class="placardForm">
		<h1>Leave messages here...</h1>
		<form method="POST" name="placardForm">
			<textarea name="message" cols="80" rows="5"></textarea>
			<br /> 
			<input type="submit" value="Add" />
		</form>
	</div>
	<div class="listTitle">
		<h1>Recent Messages</h1>
		<ul class="placardList">
			<c:forEach items="${placardList}" var="placard">
				<li>
					<div class="placardMessage">
						<!--		
						<a href="<c:url value="/placards/show?placard_id=${placard.id}" />">
						<a href="<c:url value="/placards/${placard.id}" />">	
						-->
						<a href="<c:url value="/placards/${placard.id}" />">	
							<c:out value="${placard.message}" />
						</a>						
					</div>
					<div>
						<span class="placardTime"> 
							<c:out value="${placard.time}" />
						</span>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>