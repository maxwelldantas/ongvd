package br.com.ongvd.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ongvd.dto.EnderecoDTO;
import br.com.ongvd.dto.OngDTO;
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

	@RequestMapping(method = RequestMethod.GET, path = "/painel/ong/main")
	public String painelOng() {
		return "painel/ong/main";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ong/registro")
	public String registerOngAccount(@ModelAttribute("ong") @Valid OngDTO ongDTO, BindingResult resultOng,
			@ModelAttribute("endereco") @Valid EnderecoDTO enderecoDTO, BindingResult resultEndereco) {

		Ong existing = ongService.findByEmail(ongDTO.getEmail());
		if (existing != null) {
			resultOng.rejectValue("email", null, "Este endereço de e-mail já está sendo usado");
		}
		if (resultOng.hasErrors() || resultEndereco.hasErrors()) {
			return "ong/registro";
		}
		ongService.save(ongDTO, enderecoDTO);
		return "redirect:/ong/registro?success";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/ong/listagem")
	public String getAll(Model model) {
		model.addAttribute("ongs", ongService.getAll());
		return "ong/listagem";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/ong/{id}")
	public String findById(@PathVariable Long id) {
		Optional<Ong> ong = ongService.findById(id);
		if (!ong.isPresent()) {
		}
		return "ong/listagem";
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/ong/registro/{id}")
	public String update(@PathVariable Long id, @Valid @RequestBody OngDTO ongDTO,
			@Valid @RequestBody EnderecoDTO enderecoDTO) {
		if (!ongService.findById(id).isPresent()) {
		}
		ongService.save(ongDTO, enderecoDTO);
		return "ong/registro";
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/ong/registro/{id}/delete")
	public String delete(@PathVariable Long id) {
		if (!ongService.findById(id).isPresent()) {
		}
		ongService.delete(id);
		return "redirect:/home";
	}
}
