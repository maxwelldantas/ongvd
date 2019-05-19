package br.com.ongvd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ongvd.model.ServicoVoluntario;
import br.com.ongvd.service.ServicoVoluntarioService;

@Controller
@RequestMapping("/voluntario")
public class VoluntarioController {
	
	@Autowired
	private ServicoVoluntarioService service;

	@RequestMapping(method = RequestMethod.GET, path = "/conceito")
	public String voluntario(Model model) {
		return "voluntario/conceito";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/servicos-voluntarios")
	public String getAllServicosVoluntatios(Model model) {
		List<ServicoVoluntario> servicos = service.getAll();
		model.addAttribute("servicos", servicos);
		return "voluntario/listagem";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/servico-voluntario/{id}")
	public String listToUpdate(@PathVariable(name = "id") Long id, ModelMap model) {
		model.put("servico", service.get(id));
		return "voluntario/servico-voluntario";
	}
}
