package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	@Autowired
	private FilmDAO filmDAO;
	
	  //       finish result.jsp, then controller findFIlmById route. 
	
	@RequestMapping(path= {"/", "home.do"} )
	public String home() {
		return "home";
	}
	
	@RequestMapping(path="GetFilmId.do", method=RequestMethod.GET)
	public ModelAndView FindById(int filmId) {
		Film film = filmDAO.findById(filmId);
		ModelAndView mv = new ModelAndView();
		if(film != null)
		mv.addObject(film);
		mv.setViewName("result");
		return mv;
	}
}
