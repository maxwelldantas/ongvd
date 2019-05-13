package br.com.ongvd.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ongvd.dto.ServicoVoluntarioDTO;
import br.com.ongvd.repository.ServicoVoluntarioRepository;
import br.com.ongvd.service.ServicoVoluntarioService;

@Controller
public class VoluntarioController {

	@Autowired
	private ServicoVoluntarioService servicoVoluntarioService;

	@Autowired
	private ServicoVoluntarioRepository servicoVoluntarioRepository;

	@ModelAttribute("servico")
	public ServicoVoluntarioDTO servicoVoluntarioDTO() {
		return new ServicoVoluntarioDTO();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/voluntario/conceito")
	public String voluntario(Model model) {
		return "voluntario/conceito";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/servico-voluntario/cadastro")
	public String showRegistrationForm(Model model) {
		return "painel/ong/servico-voluntario/cadastro";
	}

	@RequestMapping(method = RequestMethod.POST, value = { "/painel/ong/servico-voluntario/cadastro",
			"/painel/ong/servico-voluntario/cadastro/{id}" })
	public String registrarServicoVoluntario(
			@ModelAttribute("servico") @Valid ServicoVoluntarioDTO servicoVoluntarioDTO, BindingResult resultServico,
			@AuthenticationPrincipal UserDetails currentUser) {

//		ServicoVoluntario existing = servicoVoluntarioService.findByNome(servicoVoluntarioDTO.getNome());
//		if (existing != null) {
//			resultServico.rejectValue("nome", null, "Este serviço voluntário já está cadastrado");
//		}
		if (resultServico.hasErrors()) {
			return "painel/ong/servico-voluntario/cadastro";
		}
		servicoVoluntarioService.save(servicoVoluntarioDTO, currentUser);
		return "redirect:/painel/ong/servico-voluntario/cadastro?success";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/servico-voluntario/listagem")
	public String getAll(Model model) {
		model.addAttribute("servicos", servicoVoluntarioService.getAll());
		return "painel/ong/servico-voluntario/listagem";
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/painel/ong/servico-voluntario/atualiza-cadastro/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("servico", servicoVoluntarioRepository.findById(id));
		return "painel/ong/servico-voluntario/cadastro";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/servico-voluntario/{id}/delete")
	public String delete(@PathVariable Long id) {
		servicoVoluntarioService.delete(id);
		return "redirect:/painel/ong/servico-voluntario/listagem";
	}

}
