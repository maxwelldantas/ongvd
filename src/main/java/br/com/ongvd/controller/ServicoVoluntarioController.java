package br.com.ongvd.controller;

import java.util.List;

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
import br.com.ongvd.model.ServicoVoluntario;
import br.com.ongvd.service.ServicoVoluntarioService;

@Controller
@RequestMapping("/painel/ong/servico-voluntario")
public class ServicoVoluntarioController {

	@Autowired
	private ServicoVoluntarioService service;

	@ModelAttribute("servico")
	public ServicoVoluntarioDTO servicoVoluntarioDTO() {
		return new ServicoVoluntarioDTO();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/cadastro")
	public String mostraFormularioCadastro(Model model) {
		model.addAttribute("servico");
		return "painel/ong/servico-voluntario/cadastro";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/cadastro")
	public String registrarServicoVoluntario(
			@ModelAttribute("servico") @Valid ServicoVoluntarioDTO servicoVoluntarioDTO, BindingResult resultServico,
			ServicoVoluntario servico, @AuthenticationPrincipal UserDetails currentUser) {

		ServicoVoluntario nome = service.getByNome(servicoVoluntarioDTO.getNome());
		List<ServicoVoluntario> ong = service.getNomeByOng(currentUser);
		if (ong.contains(nome)) {
			resultServico.rejectValue("nome", null, "Este serviço voluntário já está cadastrado!");
		}
		if (resultServico.hasErrors()) {
			return "painel/ong/servico-voluntario/cadastro";
		}
		service.novo(servico, currentUser);
		service.save(servico);
		return "redirect:/painel/ong/servico-voluntario/cadastro?success";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listagem")
	public String getAll(Model model, @AuthenticationPrincipal UserDetails currentUser) {
		List<ServicoVoluntario> servicos = service.getAllByOng(currentUser);
		model.addAttribute("servicos", servicos);
		return "painel/ong/servico-voluntario/listagem";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/view-update/{id}")
	public String listToUpdate(@PathVariable(name = "id") Long id, Model model) {
		ServicoVoluntario servico = service.get(id);
		model.addAttribute("servico", servico);
		return "painel/ong/servico-voluntario/edita-cadastro";
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/edita-cadastro/{id}")
	public String update(
			@PathVariable(name = "id") Long id,
			@ModelAttribute("servico") @Valid ServicoVoluntarioDTO servicoVoluntarioDTO,
			BindingResult resultServico, @AuthenticationPrincipal UserDetails currentUser){

		ServicoVoluntario servico = service.get(id);
		ServicoVoluntario nome = service.getByNome(servicoVoluntarioDTO.getNome());
		List<ServicoVoluntario> ong = service.getNomeByOng(currentUser);
		if (ong.contains(nome)) {
			resultServico.rejectValue("nome", null, "Este serviço voluntário já está cadastrado!");
		}
		if (resultServico.hasErrors()) {
			resultServico.getAllErrors();
			return "redirect:/painel/ong/servico-voluntario/view-update/{id}";
		}
		service.edita(servico, servicoVoluntarioDTO);
		service.save(servico);
		return "redirect:/painel/ong/servico-voluntario/listagem?success";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/painel/ong/servico-voluntario/listagem";
	}

}
