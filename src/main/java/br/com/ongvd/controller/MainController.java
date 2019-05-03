package br.com.ongvd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String init() {
		return "home";
	}

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping("/doador")
	public String doador(Model model) {
		return "doador";
	}

	@GetMapping("/voluntario")
	public String voluntario(Model model) {
		return "voluntario";
	}

	@GetMapping("/ong")
	public String ong(Model model) {
		return "ong";
	}
}
