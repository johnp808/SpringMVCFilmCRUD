<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Results</title>
</head>
<body>
	<c:if test="${not empty films}">
	<h1>Films with matching keywords</h1>
	<h4>Please select a choice to display the films information.</h4>
	<br>We have ${films.size()} films that match your category.<br>
	<div>
		<form action="findId.do" method="POST">
			<c:forEach var="f" items="${films}">
				<div>
					<input type="radio" id="${f.id}" name="filmId" value="${f.id}"> ${film.title}
					<label>${f.title}</label>, ${f.releaseYear}, ${f.rating}<br>
				</div>
			</c:forEach>
			<input type="submit" value="More Info">
		</form>
	</div>
	</c:if>
	<c:if test="${empty films}">
	<h1>No Films Found</h1>
	No film in the database matches that keyword. Try a different keyword or add a new film that contains it!
	</c:if>
	<br><a href="home.do">Home</a>
</body>
</html>