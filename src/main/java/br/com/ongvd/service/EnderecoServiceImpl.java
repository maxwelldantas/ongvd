package br.com.ongvd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ongvd.dto.EnderecoDTO;
import br.com.ongvd.model.Endereco;
import br.com.ongvd.repository.EnderecoRepository;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Endereco save(EnderecoDTO enderecoDTO) {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoDTO.getCep());
		endereco.setLogradouro(enderecoDTO.getLogradouro());
		endereco.setNumero(enderecoDTO.getNumero());
		endereco.setComplemento(enderecoDTO.getComplemento());
		endereco.setBairro(enderecoDTO.getBairro());
		endereco.setCidade(enderecoDTO.getCidade());
		endereco.setUf(enderecoDTO.getUf());
		return enderecoRepository.save(endereco);
	}

}
