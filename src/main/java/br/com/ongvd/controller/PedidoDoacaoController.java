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

import br.com.ongvd.dto.PedidoDoacaoDTO;
import br.com.ongvd.model.PedidoDoacao;
import br.com.ongvd.service.PedidoDoacaoService;

@Controller
@RequestMapping("/painel/ong/pedido-doacao")
public class PedidoDoacaoController {
	
	@Autowired
	private PedidoDoacaoService service;

	@ModelAttribute("pedido")
	public PedidoDoacaoDTO pedidoDoacaoDTO() {
		return new PedidoDoacaoDTO();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/cadastro")
	public String mostraFormularioCadastro(Model model) {
		model.addAttribute("pedido");
		return "painel/ong/pedido-doacao/cadastro";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/cadastro")
	public String registrarPedidoDoacao(
			@ModelAttribute("pedido") @Valid PedidoDoacaoDTO pedidoDoacaoDTO, BindingResult result,
			PedidoDoacao pedido, @AuthenticationPrincipal UserDetails currentUser) {

		PedidoDoacao nome = service.getByNome(pedidoDoacaoDTO.getNome());
		List<PedidoDoacao> ong = service.getNomeByOng(currentUser);
		if (ong.contains(nome)) {
			result.rejectValue("nome", null, "Este pedido de doação já está cadastrado!");
		}
		if (result.hasErrors()) {
			return "painel/ong/pedido-doacao/cadastro";
		}
		service.novo(pedido, currentUser);
		service.save(pedido);
		return "redirect:/painel/ong/pedido-doacao/cadastro?success";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listagem")
	public String getAll(Model model, @AuthenticationPrincipal UserDetails currentUser) {
		List<PedidoDoacao> pedidos = service.getAllByOng(currentUser);
		model.addAttribute("pedidos", pedidos);
		return "painel/ong/pedido-doacao/listagem";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/edita-cadastro/{id}")
	public String listToUpdate(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("pedido", service.get(id));
		return "painel/ong/pedido-doacao/edita-cadastro";
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/edita-cadastro/{id}")
	public String update(
			@PathVariable(name = "id") Long id,
			@ModelAttribute("pedido") @Valid PedidoDoacaoDTO pedidoDoacaoDTO,
			BindingResult result, @AuthenticationPrincipal UserDetails currentUser){

		PedidoDoacao pedido = service.get(id);
		PedidoDoacao nome = service.getByNome(pedidoDoacaoDTO.getNome());
		List<PedidoDoacao> ong = service.getNomeByOng(currentUser);
		if (ong.contains(nome)) {
			result.rejectValue("nome", null, "Este pedido de doação já está cadastrado!");
		}
		if (result.hasErrors() && !nome.equals(pedido)) {
			return "redirect:/painel/ong/pedido-doacao/edita-cadastro/{id}";
		}
		service.edita(pedido, pedidoDoacaoDTO);
		service.save(pedido);
		return "redirect:/painel/ong/pedido-doacao/listagem?success";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/painel/ong/pedido-doacao/listagem?delete";
	}

}
