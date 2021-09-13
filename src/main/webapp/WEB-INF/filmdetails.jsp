<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Details</title>
</head>
<body>
	<br>
	<c:if test="${not empty film }">
	<h1>Film Details</h1>
		Title: ${film.title }, ${film.releaseYear }, Rated ${film.rating }<br>
		Description: ${film.description }<br>
		${film.length } minutes<br>
		<h2>Film Category</h2>
		<ul>
			<c:forEach var="c" items="${categories}">
				<li>${c.name }</li>
			</c:forEach>
		</ul>
		<h2>Film Cast</h2>
		<table>
			<c:forEach var="a" items="${actors}">
				<tr>
					<td>${a.firstName} ${a.lastName}</td>
					<td>${a.id}</td>
				</tr>
			</c:forEach>
		</table>
		<a href="home.do">Home</a>
		<br>
		<br>
		<br>
		<a href="filmGettingUpdate.do?filmId=${film.id}">Update this film
			information</a>
		<br>		
		<br>
		<br>
			<h1>Danger Cannot Be Undone...</h1>
		<a href="deleteFilm.do?filmId=${film.id}">Delete this film</a>
	</c:if>
	<c:if test="${empty film}">
	<h1>Error...</h1>
	Sorry, but that film ID doesn't exist.  Please Try Again!
	<br>
		<a href="home.do">Home</a>
	</c:if>
	
	
</body>
</html>