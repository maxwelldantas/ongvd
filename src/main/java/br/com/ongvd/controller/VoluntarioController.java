package br.com.ongvd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ongvd.dto.EmailDTO;
import br.com.ongvd.model.ServicoVoluntario;
import br.com.ongvd.service.EmailService;
import br.com.ongvd.service.ServicoVoluntarioService;

@Controller
@RequestMapping("/voluntario")
public class VoluntarioController {
	
	@Autowired
	private ServicoVoluntarioService service;
	
	@Autowired
	private EmailService emailService;
	
	@ModelAttribute("email")
	public EmailDTO emailDTO() {
		return new EmailDTO();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/conceito")
	public String voluntario(Model model) {
		return "voluntario/conceito";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/servicos-voluntarios")
	public String getAllServicosVoluntatios(Model model) {
		List<ServicoVoluntario> servicos = service.getAllByHabilitado(true);
		model.addAttribute("servicos", servicos);
		return "voluntario/listagem";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/servico-voluntario/{id}")
	public String listToUpdate(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("servico", service.get(id));
		return "voluntario/servico-voluntario";
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/servico-voluntario/{id}")
	public String enviarEmail(
			@PathVariable(name = "id") Long id, @PathVariable(name = "id") ServicoVoluntario servicoVoluntario,
			@ModelAttribute("email") @Valid EmailDTO emailDTO, BindingResult resultServico){

		if (resultServico.hasErrors()) {
			return "redirect:/voluntario/servico-voluntario/{id}?error";
		}
		emailDTO.setEmailPara(servicoVoluntario.getOng().getEmail());
		emailService.enviarTexto(emailDTO.getEmailDe(), emailDTO.getEmailPara(), emailDTO.getAssunto(), emailDTO.getCorpo());
		return "redirect:/voluntario/servico-voluntario/{id}?success";
	}
}
