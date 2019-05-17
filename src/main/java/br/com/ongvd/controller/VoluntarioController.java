package br.com.ongvd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/voluntario")
public class VoluntarioController {

	@RequestMapping(method = RequestMethod.GET, path = "/conceito")
	public String voluntario(Model model) {
		return "voluntario/conceito";
	}

}
