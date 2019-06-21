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

import br.com.ongvd.dto.EmailDTO;
import br.com.ongvd.dto.EventoDTO;
import br.com.ongvd.entity.Evento;
import br.com.ongvd.service.EmailService;
import br.com.ongvd.service.EventoService;

@Controller
public class EventoController {

	@Autowired
	private EventoService service;
	
	@Autowired
	private EmailService emailService;
	
	@ModelAttribute("email")
	public EmailDTO emailDTO() {
		return new EmailDTO();
	}

	@ModelAttribute("eventoDTO")
	public EventoDTO eventoDTO() {
		return new EventoDTO();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/evento/cadastro")
	public String mostraFormularioCadastro(Model model) {
		model.addAttribute("eventoDTO");
		return "painel/ong/evento/cadastro";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/painel/ong/evento/cadastro")
	public String cadastrarEvento(
			@ModelAttribute("eventoDTO") @Valid EventoDTO eventoDTO, BindingResult result,
			Evento evento, @AuthenticationPrincipal UserDetails currentUser) {

		Evento nome = service.getByNome(eventoDTO.getNome());
		List<Evento> ong = service.getNomeByOng(currentUser);
		if (ong.contains(nome)) {
			result.rejectValue("nome", null, "Este evento já está cadastrado!");
		}
		if (result.hasErrors()) {
			return "painel/ong/evento/cadastro";
		}
		service.novo(evento,currentUser);
		return "redirect:/painel/ong/evento/cadastro?success";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/evento/listagem")
	public String getAll(Model model, @AuthenticationPrincipal UserDetails currentUser) {
		List<Evento> eventos = service.getAllByOng(currentUser);
		model.addAttribute("eventos", eventos);
		return "painel/ong/evento/listagem";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/evento/edita-cadastro/{id}")
	public String mostrarFormularioParaEditar(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("eventoDTO", service.get(id));
		return "painel/ong/evento/edita-cadastro";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/painel/ong/evento/atualizar-cadastro/{id}")
	public String atualizar(@PathVariable(name = "id") Long id,
			@ModelAttribute("eventoDTO") @Valid EventoDTO eventoDTO, BindingResult result,
			@AuthenticationPrincipal UserDetails currentUser) {

		Evento evento = service.get(id);
		Evento nome = service.getByNome(eventoDTO.getNome());
		List<Evento> ong = service.getNomeByOng(currentUser);
		if (ong.contains(nome) && !evento.equals(nome)) {
			result.rejectValue("nome", null, "Este evento já está cadastrado!");
		}
		if (result.hasErrors()) {
			eventoDTO.setId(id);
			return "painel/ong/evento/edita-cadastro";
		}
		service.edita(evento, eventoDTO);
		return "redirect:/painel/ong/evento/listagem?success";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/evento/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/painel/ong/evento/listagem?delete";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/evento/conceito")
	public String evento(Model model) {
		return "evento/conceito";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/e/eventos")
	public String getAllEventos(Model model) {
		List<Evento> eventosOld = service.getAll();
		List<Evento> eventosNew = service.getAllHabilitadoTrueAndOngAtivoTrue(eventosOld);
		model.addAttribute("eventos", eventosNew);
		return "evento/listagem";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/e/evento/{id}")
	public String detalhesEvento(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("evento", service.get(id));
		return "evento/evento";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/e/evento/{id}")
	public String enviarEmail(@PathVariable(name = "id") Long id,
			@PathVariable(name = "id") Evento evento,
			@ModelAttribute("email") @Valid EmailDTO emailDTO, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:/e/evento/{id}?error";
		}
		emailDTO.setEmailPara(evento.getOng().getEmail());
		emailService.enviarTexto(emailDTO.getEmailDe(), emailDTO.getEmailPara(), emailDTO.getAssunto(),
				emailDTO.getCorpo());
		return "redirect:/e/evento/{id}?success";
	}

}
