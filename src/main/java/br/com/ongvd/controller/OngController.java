package br.com.ongvd.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ongvd.dto.EnderecoDTO;
import br.com.ongvd.dto.OngDTO;
import br.com.ongvd.model.Ong;
import br.com.ongvd.service.EnderecoService;
import br.com.ongvd.service.OngService;

@Controller
public class OngController {

	@Autowired
	private OngService ongService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@ModelAttribute("ong")
	public OngDTO ongDTO() {
		return new OngDTO();
	}
	
	@ModelAttribute("endereco")
	public EnderecoDTO enderecoDTO() {
		return new EnderecoDTO();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/entidade/registration")
	public String showRegistrationForm(Model model) {
		return "entidade/registration";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/entidade/registration")
	public String registerOngAccount(
		@ModelAttribute("ong") @Valid OngDTO ongDTO, BindingResult resultOng,
		@ModelAttribute("endereco") @Valid EnderecoDTO enderecoDTO, BindingResult resultEndereco) {

		Ong existing = ongService.findByEmail(ongDTO.getEmail());
		if (existing != null) {
			resultOng.rejectValue(
					"email", null, "Já existe uma entidade registrada com este e-mail");
		}

		if (resultOng.hasErrors() || resultEndereco.hasErrors()) {
			return "entidade/registration";
		}
		ongService.save(ongDTO);
		enderecoService.save(enderecoDTO);
		return "redirect:/entidade/registration?success";
	}

}
