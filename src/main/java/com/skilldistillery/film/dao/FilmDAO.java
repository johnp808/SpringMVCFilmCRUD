package com.skilldistillery.film.dao;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {

	Film findById(int filmId);

	Film createFilm(Film film);

	boolean deleteFilm(Film film);

	Film updateFilm(Film ogFilm, Film film);
	
	Actor addActor(Actor actor);
	

}
