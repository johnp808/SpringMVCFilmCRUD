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
	
	public void setFilmDAO(FilmDAO filmDAO) {
		this.filmDAO = filmDAO;
	}
	
	  //       finish result.jsp, then controller findFIlmById route. 
	
	@RequestMapping(path= {"/", "home.do"} )
	public String home() {
		return "WEB-INF/home.jsp";
	}
	
	@RequestMapping(path="GetFilmId.do", params="id", method=RequestMethod.GET)
	public ModelAndView getFilmId(int id) {
		ModelAndView mv = new ModelAndView();
		Film f = filmDAO.findById(id);
		mv.addObject("film id", f);
		mv.setViewName("WEB-INF/result.jsp");
		return mv;
		
	}
}
