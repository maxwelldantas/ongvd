package br.com.ongvd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doador")
public class DoadorController {
	
	@GetMapping("/conceito")
	public String doador(Model model) {
		return "doador/conceito";
	}

}
