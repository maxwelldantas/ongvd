package br.com.ongvd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

import br.com.ongvd.dto.ServicoVoluntarioDTO;
import br.com.ongvd.model.ServicoVoluntario;
import br.com.ongvd.service.ServicoVoluntarioServiceImpl;

@Controller
public class VoluntarioController {

	@Autowired
	private ServicoVoluntarioServiceImpl servicoImpl;

	@ModelAttribute("servico")
	public ServicoVoluntarioDTO servicoVoluntarioDTO() {
		return new ServicoVoluntarioDTO();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/voluntario/conceito")
	public String voluntario(Model model) {
		return "voluntario/conceito";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/servico-voluntario/novo-cadastro")
	public String mostraFormularioCadastro(Model model) {
//		ServicoVoluntario servico = new ServicoVoluntario();
		model.addAttribute("servico");
		return "painel/ong/servico-voluntario/cadastro";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/painel/ong/servico-voluntario/cadastro")
	public String registrarServicoVoluntario(
			@ModelAttribute("servico") @Valid ServicoVoluntarioDTO servicoVoluntarioDTO, BindingResult resultServico,
			ServicoVoluntario servico, @AuthenticationPrincipal UserDetails currentUser) {
		
		
		ServicoVoluntario existing = servicoImpl.findByNome(servicoVoluntarioDTO.getNome());
		if (existing != null) {
			resultServico.rejectValue("nome", null, "Este serviço voluntário já está cadastrado!");
		}
		if (resultServico.hasErrors()) {
			return "painel/ong/servico-voluntario/cadastro";
		}
		servicoImpl.novo(servico, currentUser);
		servicoImpl.save(servico);
		return "redirect:/painel/ong/servico-voluntario/novo-cadastro?success";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/servico-voluntario/listagem")
	public String getAll(Model model) {
		List<ServicoVoluntario> servicos = servicoImpl.getAll();
		model.addAttribute("servicos", servicos);
		return "painel/ong/servico-voluntario/listagem";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/servico-voluntario/edita-cadastro/{id}")
	public ModelAndView update(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("painel/ong/servico-voluntario/edita-cadastro");
		ServicoVoluntario servico = servicoImpl.get(id);
		mav.addObject("servico", servico);
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/servico-voluntario/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		servicoImpl.delete(id);
		return "redirect:/painel/ong/servico-voluntario/listagem";
	}

}
