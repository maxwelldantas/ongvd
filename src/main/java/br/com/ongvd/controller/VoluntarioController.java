package br.com.ongvd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("voluntario")
public class VoluntarioController {

	@GetMapping("/conceito")
	public String voluntario(Model model) {
		return "voluntario/conceito";
	}

}
