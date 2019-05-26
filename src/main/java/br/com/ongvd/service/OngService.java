package br.com.ongvd.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.ongvd.dto.EnderecoDTO;
import br.com.ongvd.dto.OngDTO;
import br.com.ongvd.model.Ong;

@Service
public interface OngService extends UserDetailsService {
	Ong findByEmail(String email);
	
	Ong findByCnpj(String cnpj);

	void save(OngDTO ongDTO, EnderecoDTO enderecoDTO);

	List<Ong> getAll();

	Ong findById(Long id);

	Ong findByRazaoSocial(String razaoSocial);

	void update(Long id, Ong ong);

	void delete(Long id);

	boolean exists(Ong ong);
}