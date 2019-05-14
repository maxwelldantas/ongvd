package br.com.ongvd.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.ongvd.model.ServicoVoluntario;

@Service
public interface ServicoVoluntarioService {
	
	ServicoVoluntario novo(ServicoVoluntario servicoVoluntario, UserDetails currentUser);
	
	void save(ServicoVoluntario servico);

	List<ServicoVoluntario> getAll();

	ServicoVoluntario get(Long id);

	void delete(Long id);
	
	ServicoVoluntario findByNome(String nome);

	boolean exists(ServicoVoluntario servicoVoluntario);
}