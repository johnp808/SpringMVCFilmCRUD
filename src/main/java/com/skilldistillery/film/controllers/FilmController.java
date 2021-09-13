package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;


@Controller
public class FilmController {
	@Autowired
	private FilmDAO filmDAO;

	@RequestMapping(path = { "/", "home.do" })
	public String home() {
		return "home";
	}

	@RequestMapping(path = "GetFilmsId.do", method = RequestMethod.POST)
	public ModelAndView findFilmId(int filmId) {
		Film film = filmDAO.findById(filmId);
		ModelAndView mv = new ModelAndView();
		if (film != null) {
			mv.addObject(film);
		}
		mv.setViewName("getfilmid");
		return mv;
	}
	
	@RequestMapping(path = "AddFilm.do" , method = RequestMethod.POST)
	public ModelAndView createFilm(Film film, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		Film checkFilm = filmDAO.createFilm(film);
		redir.addFlashAttribute("film", checkFilm);
		mv.setViewName("redirect:filmCreated.do");
		return mv;
	}
	@RequestMapping(path = "filmCreated.do", method = RequestMethod.GET)
	public ModelAndView created() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("filmadded");
		return mv;
	}
	
	@RequestMapping(path = "deleteFilm.do")
	public ModelAndView deleteFilm(int filmId) {
		ModelAndView mv = new ModelAndView();
		Film localFilm = null;
		localFilm = filmDAO.findById(filmId);
		if (localFilm != null) {
			filmDAO.deleteFilm(localFilm);
			mv.addObject(localFilm);
		}
		mv.setViewName("filmDeleted");
		return mv;
	}
}