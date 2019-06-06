package br.com.ongvd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ongvd.dto.EnderecoDTO;
import br.com.ongvd.dto.OngDTO;
import br.com.ongvd.dto.OngEdicaoDTO;
import br.com.ongvd.model.Ong;
import br.com.ongvd.service.OngService;

@Controller
public class OngController {

	@Autowired
	private OngService ongService;
	
	@ModelAttribute("ong")
	public OngDTO ongDTO() {
		return new OngDTO();
	}
	
	@ModelAttribute("ongEdicao")
	public OngEdicaoDTO ongEdicaoDTO() {
		return new OngEdicaoDTO();
	}

	@ModelAttribute("endereco")
	public EnderecoDTO enderecoDTO() {
		return new EnderecoDTO();
	}

	@GetMapping("/ong/conceito")
	public String ong(Model model) {
		return "ong/conceito";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/ong/registro")
	public String showRegistrationForm(Model model) {
		return "ong/registro";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ong/registro")
	public String registerOngAccount(@ModelAttribute("ong") @Valid OngDTO ongDTO, BindingResult resultOng,
			@ModelAttribute("endereco") @Valid EnderecoDTO enderecoDTO, BindingResult resultEndereco) {

		Ong existeEmail = ongService.findByEmail(ongDTO.getEmail());
		Ong existeCnpj = ongService.findByCnpj(ongDTO.getCnpj());
		if (existeEmail != null) {
			resultOng.rejectValue("email", null, "Este endereço de e-mail já está sendo usado");
		}
		if (existeCnpj != null) {
			resultOng.rejectValue("cnpj", null, "Este CNPJ já está sendo usado");
		}
		if (resultOng.hasErrors() || resultEndereco.hasErrors()) {
			return "ong/registro";
		}
		ongService.novo(ongDTO, enderecoDTO);
		return "redirect:/ong/registro?success";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/alterar-registro/{id}")
	public String paginaAlterarRegistro(@PathVariable(name = "id") Long id, Model model) {
		Ong ong = ongService.findById(id);
		model.addAttribute("ongEdicao", ong);
		return "painel/ong/alterar-registro";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/painel/ong/alterar-registro/{id}")
	public String alterarRegistro(@PathVariable(name = "id") Long id, @ModelAttribute("ongEdicao") @Valid OngEdicaoDTO ongEdicaoDTO,
			BindingResult result, @AuthenticationPrincipal UserDetails currentUser) {
		
		Ong ong = ongService.findById(id);
		Ong email = ongService.findByEmail(ongEdicaoDTO.getEmail());
		Ong cnpj = ongService.findByCnpj(ongEdicaoDTO.getCnpj());
		List<Ong> ongs = ongService.getAll();
		if (ongs.contains(ong) && !ong.equals(email) && email != null) {
			result.rejectValue("email", null, "Este endereço de e-mail já está sendo usado");
		}
		if (ongs.contains(ong) && !ong.equals(cnpj) && cnpj != null) {
			result.rejectValue("cnpj", null, "Este CNPJ já está cadastrado!");
		}
		if (result.hasErrors()) {
			ongEdicaoDTO.setId(id);
			return "painel/ong/alterar-registro";
		}
		ongService.edita(ong, ongEdicaoDTO);
		ongService.save(ong);
		return "redirect:/painel/ong/configuracoes?success";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/configuracoes")
	public String painelOng(Model model, @AuthenticationPrincipal UserDetails currentUser) {
		Ong ong = (Ong) ongService.findByEmail(currentUser.getUsername());
		model.addAttribute("ong", ong);
		return "painel/ong/configuracoes";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/ong/listagem")
	public String getAll(Model model) {
		model.addAttribute("ongs", ongService.getAll());
		return "ong/listagem";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/ong/{id}")
	public String findById(@PathVariable Long id) {
		ongService.findById(id);
		return "ong/listagem";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/desativar-registro/{id}")
	public String desativar(@PathVariable Long id) {
		Ong ong = ongService.findById(id);
		ong.setAtivo(false);
		ongService.save(ong);
		return "redirect:/painel/ong/configuracoes?desativada";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/ativar-registro/{id}")
	public String ativar(@PathVariable Long id) {
		Ong ong = ongService.findById(id);
		ong.setAtivo(true);
		ongService.save(ong);
		return "redirect:/painel/ong/configuracoes?ativada";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/deletar-registro/{id}")
	public String delete(@PathVariable("id") Long id) {
		ongService.delete(id);
		return "redirect:/logout?delete";
	}

}
