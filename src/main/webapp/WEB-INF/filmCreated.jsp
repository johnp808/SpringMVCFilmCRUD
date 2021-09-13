<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Added To Database</title>

</head>
<body>
		<c:if test="${! empty film}">
			<div class="alert alert-success" role="alert">Film added</div>
			${film.title}
		</c:if>
		<c:if test="${empty film}">
			<div class="alert alert-danger" role="alert">Error adding Film</div>
		</c:if>
	<br>
	<br>
	<br><a href="home.do">Home</a>
	
	
</body>
</html>