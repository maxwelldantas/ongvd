package br.com.ongvd.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.ongvd.dto.EventoDTO;
import br.com.ongvd.model.Evento;

@Service
public interface EventoService {
	
	Evento novo(EventoDTO eventoDTO, UserDetails currentUser);

	Evento edita(Evento evento, EventoDTO eventoDTO);

	void save(Evento evento);

	List<Evento> getAll();

	List<Evento> getAllByOng(UserDetails currentUser);
	
	List<Evento> getAllByHabilitado(Boolean habilitado);

	Evento get(Long id);

	void delete(Long id);

	Evento getByNome(String nome);

	List<Evento> getNomeByOng(UserDetails currentUser);

	boolean exists(Evento evento);
}