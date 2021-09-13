package com.skilldistillery.film.dao;

import java.util.List;

import com.skilldistillery.film.entities.Film;

public interface FilmDAO {

	Film findById(int filmId);

	Film createFilm(Film film);

	Film deleteFilm(Film film);

	Film updateFilm(Film film);

	List<Film> findFilmByKeyword(String keyword);
	
}
