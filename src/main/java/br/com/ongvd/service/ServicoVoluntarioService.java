package br.com.ongvd.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.ongvd.dto.ServicoVoluntarioDTO;
import br.com.ongvd.model.ServicoVoluntario;

@Service
public interface ServicoVoluntarioService {
	
	ServicoVoluntario novo(ServicoVoluntario servicoVoluntario, UserDetails currentUser);
	
	ServicoVoluntario edita(ServicoVoluntario servico, ServicoVoluntarioDTO servicoVoluntarioDTO);
	
	void save(ServicoVoluntario servico);

	List<ServicoVoluntario> getAll();
	
	List<ServicoVoluntario> getAllByOngByEmail(UserDetails currentUser);

	ServicoVoluntario get(Long id);

	void delete(Long id);
	
	ServicoVoluntario getByNome(String nome);
	
	boolean exists(ServicoVoluntario servicoVoluntario);
}