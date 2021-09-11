package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.film.dao.FilmDAO;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDao;
	
	  //       finish result.jsp, then controller findFIlmById route. 
	
	@RequestMapping(path= {"/", "home.do"} )
	public String home() {
		return "WEB-INF/home.jsp";
	}
	
	@RequestMapping(path="GetFilmId.do", params="id", method=RequestMethod.GET)
	public ModelAndView getFilmId
}
