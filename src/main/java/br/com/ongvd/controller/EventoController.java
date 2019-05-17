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

import br.com.ongvd.dto.EventoDTO;
import br.com.ongvd.model.Evento;
import br.com.ongvd.service.EventoService;

@Controller
@RequestMapping("/painel/ong/evento")
public class EventoController {

	@Autowired
	private EventoService service;

	@ModelAttribute("evento")
	public EventoDTO eventoDTO() {
		return new EventoDTO();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/cadastro")
	public String mostraFormularioCadastro(Model model) {
		model.addAttribute("evento");
		return "painel/ong/evento/cadastro";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/cadastro")
	public String cadastrarEvento(
			@ModelAttribute("evento") @Valid EventoDTO eventoDTO, BindingResult result,
			Evento evento, @AuthenticationPrincipal UserDetails currentUser) {

		Evento nome = service.getByNome(eventoDTO.getNome());
		List<Evento> ong = service.getNomeByOng(currentUser);
		if (ong.contains(nome)) {
			result.rejectValue("nome", null, "Este evento já está cadastrado!");
		}
		if (result.hasErrors()) {
			return "painel/ong/evento/cadastro";
		}
		service.novo(evento, currentUser);
		service.save(evento);
		return "redirect:/painel/ong/evento/cadastro?success";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listagem")
	public String getAll(Model model, @AuthenticationPrincipal UserDetails currentUser) {
		List<Evento> eventos = service.getAllByOng(currentUser);
		model.addAttribute("eventos", eventos);
		return "painel/ong/evento/listagem";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/view-update/{id}")
	public String listToUpdate(@PathVariable(name = "id") Long id, Model model) {
		Evento evento = service.get(id);
		model.addAttribute("evento", evento);
		return "painel/ong/evento/edita-cadastro";
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/edita-cadastro/{id}")
	public String update(
			@PathVariable(name = "id") Long id,
			@ModelAttribute("evento") @Valid EventoDTO eventoDTO,
			BindingResult result, @AuthenticationPrincipal UserDetails currentUser){

		Evento evento = service.get(id);
		Evento nome = service.getByNome(eventoDTO.getNome());
		List<Evento> ong = service.getNomeByOng(currentUser);
		if (ong.contains(nome)) {
			result.rejectValue("nome", null, "Este evento já está cadastrado!");
		}
		if (result.hasErrors()) {
			return "redirect:/painel/ong/evento/view-update/{id}";
		}
		service.edita(evento, eventoDTO);
		service.save(evento);
		return "redirect:/painel/ong/evento/listagem?success";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/painel/ong/evento/listagem";
	}

}
