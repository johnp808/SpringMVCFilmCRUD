<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Film Entry</title>
</head>
<body>
<h1>Enter Any Changes Necessary</h1>
<form action="updateFilmForm.do?filmId=${film.id }" method="POST">
Title <input type="text" name="title" value="${film.title }"><br>
Description <input type="text" name="description" value="${film.description }"><br>
Release Year <input type="text" name="release Year" value="${film.releaseYear }"><br>
Language Id <input type="text" name="language Id" value="${film.languageId }"><br>
Rental Duration <input type="text" name="rental Duration" value="${film.rentalDuration }"><br>
Rental Rate <input type="text" name="rental Rate" value="${film.rentalRate }"><br>
Length <input type="text" name="length" value="${film.length }"><br>
Replacement Cost <input type="text" name="replacement Cost" value="${film.replacementCost }"><br>
Rating <input type="text" name="rating" value="${film.rating }"><br>
Special Features <input type="text" name="special Features" value="${film.specialFeatures }"><br>
<input type="submit" value="Update">
</form>
</body>
</html>