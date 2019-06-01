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
import br.com.ongvd.model.PedidoDoacao;
import br.com.ongvd.service.EmailService;
import br.com.ongvd.service.PedidoDoacaoService;

@Controller
@RequestMapping("/doador")
public class DoadorController {
	
	@Autowired
	private PedidoDoacaoService service;

	@Autowired
	private EmailService emailService;

	@ModelAttribute("email")
	public EmailDTO emailDTO() {
		return new EmailDTO();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/conceito")
	public String doador(Model model) {
		return "doador/conceito";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/pedidos-doacoes")
	public String getAllPedidosDoacoes(Model model) {
		List<PedidoDoacao> pedidosOld = service.getAll();
		List<PedidoDoacao> pedidosNew = service.getAllHabilitadoTrueAndOngAtivoTrue(pedidosOld);
		model.addAttribute("pedidos", pedidosNew);
		return "doador/listagem";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/pedido-doacao/{id}")
	public String mostrarPedidoDoacao(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("pedido", service.get(id));
		return "doador/pedido-doacao";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/pedido-doacao/{id}")
	public String enviarEmail(@PathVariable(name = "id") Long id,
			@PathVariable(name = "id") PedidoDoacao pedidoDoacao,
			@ModelAttribute("email") @Valid EmailDTO emailDTO, BindingResult resultServico) {

		if (resultServico.hasErrors()) {
			return "redirect:/doador/pedido-doacao/{id}?error";
		}
		emailDTO.setEmailPara(pedidoDoacao.getOng().getEmail());
		emailService.enviarTexto(emailDTO.getEmailDe(), emailDTO.getEmailPara(), emailDTO.getAssunto(),
				emailDTO.getCorpo());
		return "redirect:/doador/pedido-doacao/{id}?success";
	}

}
