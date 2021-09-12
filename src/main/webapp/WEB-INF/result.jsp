<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Film</title>
</head>
<body>
    <c:if test="${! empty film}">
        Id: ${film.id},
        Title: ${film.title},
        Released: ${film.releaseYear},
        Film Rating: ${film.rating},
        Description: ${film.description},
        ${film.length } Minutes
    </c:if>
    <c:if test="${empty film}">
      <p>No film found</p>
    </c:if>
  <br><a href="home.do">Go Back</a>
</body>
</html>