<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film</title>
</head>
<body>
<h1>Film By ID</h1>
	<br>
	<c:if test="${! empty film}">
		Title: ${film.title}, 
		${film.releaseYear},
		Rated ${film.rating},
		Description: ${film.description},
		${film.length } minutes
	</c:if>
	<c:if test="${empty film }">Could not find a film with that ID.
	</c:if>
	<br><a href="home.do">Home</a>
</body>
</html>