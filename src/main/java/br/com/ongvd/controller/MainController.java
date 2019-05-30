package br.com.ongvd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	@GetMapping
	public String init() {
		return "home";
	}

	@GetMapping("home")
	public String home() {
		return "home";
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = {"logout"})
	 public String clear(HttpServletRequest request,HttpServletResponse response){
	 HttpSession session= request.getSession(false);
	     SecurityContextHolder.clearContext();
	          session= request.getSession(false);
	         if(session != null) {
	             session.invalidate();
	         }
	         for(javax.servlet.http.Cookie cookie : request.getCookies()) {
	             cookie.setMaxAge(0);
	         }

	     return "login";
	 }
}
