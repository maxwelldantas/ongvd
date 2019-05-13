package br.com.ongvd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.ongvd.dto.ServicoVoluntarioDTO;
import br.com.ongvd.model.ServicoVoluntario;

@Service
public interface ServicoVoluntarioService {
	void save(ServicoVoluntarioDTO dto, UserDetails currentUser);

	List<ServicoVoluntario> getAll();

	Optional<ServicoVoluntario> update(Long id);

	void delete(Long id);
	
	ServicoVoluntario findByNome(String nome);

	boolean exists(ServicoVoluntario servicoVoluntario);
}