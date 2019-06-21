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
import br.com.ongvd.dto.PedidoDoacaoDTO;
import br.com.ongvd.entity.PedidoDoacao;
import br.com.ongvd.service.EmailService;
import br.com.ongvd.service.PedidoDoacaoService;

@Controller
public class PedidoDoacaoController {
	
	@Autowired
	private PedidoDoacaoService service;
	
	@Autowired
	private EmailService emailService;

	@ModelAttribute("email")
	public EmailDTO emailDTO() {
		return new EmailDTO();
	}
	
	@ModelAttribute("pedido")
	public PedidoDoacaoDTO pedidoDoacaoDTO() {
		return new PedidoDoacaoDTO();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/pedido-doacao/cadastro")
	public String mostraFormularioCadastro(Model model) {
		model.addAttribute("pedido");
		return "painel/ong/pedido-doacao/cadastro";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/painel/ong/pedido-doacao/cadastro")
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
		return "redirect:/painel/ong/pedido-doacao/cadastro?success";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/pedido-doacao/listagem")
	public String getAll(Model model, @AuthenticationPrincipal UserDetails currentUser) {
		List<PedidoDoacao> pedidos = service.getAllByOng(currentUser);
		model.addAttribute("pedidos", pedidos);
		return "painel/ong/pedido-doacao/listagem";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/pedido-doacao/edita-cadastro/{id}")
	public String listToUpdate(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("pedido", service.get(id));
		return "painel/ong/pedido-doacao/edita-cadastro";
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/painel/ong/pedido-doacao/edita-cadastro/{id}")
	public String update(
			@PathVariable(name = "id") Long id,
			@ModelAttribute("pedido") @Valid PedidoDoacaoDTO pedidoDoacaoDTO,
			BindingResult result, @AuthenticationPrincipal UserDetails currentUser){

		PedidoDoacao pedido = service.get(id);
		PedidoDoacao nome = service.getByNome(pedidoDoacaoDTO.getNome());
		List<PedidoDoacao> ong = service.getNomeByOng(currentUser);
		if (ong.contains(nome) && !pedido.equals(nome)) {
			result.rejectValue("nome", null, "Este pedido de doação já está cadastrado!");
		}
		if (result.hasErrors()) {
			pedidoDoacaoDTO.setId(id);
			return "painel/ong/pedido-doacao/edita-cadastro";
		}
		service.edita(pedido, pedidoDoacaoDTO);
		return "redirect:/painel/ong/pedido-doacao/listagem?success";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/pedido-doacao/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/painel/ong/pedido-doacao/listagem?delete";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/doador/conceito")
	public String pedido(Model model) {
		return "doador/conceito";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/p/pedidos-doacoes")
	public String getAllPedidosDoacoes(Model model) {
		List<PedidoDoacao> pedidosOld = service.getAll();
		List<PedidoDoacao> pedidosNew = service.getAllHabilitadoTrueAndOngAtivoTrue(pedidosOld);
		model.addAttribute("pedidos", pedidosNew);
		return "doador/listagem";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/p/pedido-doacao/{id}")
	public String mostrarPedidoDoacao(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("pedido", service.get(id));
		return "doador/pedido";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/p/pedido-doacao/{id}")
	public String enviarEmail(@PathVariable(name = "id") Long id,
			@PathVariable(name = "id") PedidoDoacao pedidoDoacao,
			@ModelAttribute("email") @Valid EmailDTO emailDTO, BindingResult resultServico) {

		if (resultServico.hasErrors()) {
			return "redirect:/p/pedido-doacao/{id}?error";
		}
		emailDTO.setEmailPara(pedidoDoacao.getOng().getEmail());
		emailService.enviarTexto(emailDTO.getEmailDe(), emailDTO.getEmailPara(), emailDTO.getAssunto(),
				emailDTO.getCorpo());
		return "redirect:/p/pedido-doacao/{id}?success";
	}

}
