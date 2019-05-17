package br.com.ongvd.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.ongvd.dto.EventoDTO;
import br.com.ongvd.model.Evento;
import br.com.ongvd.model.Ong;
import br.com.ongvd.repository.EventoRepository;
import br.com.ongvd.repository.OngRepository;

@Service
public class EventoServiceImpl implements EventoService {
	
	private final Logger LOG = LoggerFactory.getLogger(EventoServiceImpl.class);

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private OngRepository ongRepository;
	
	public Evento novo(Evento evento, @AuthenticationPrincipal UserDetails currentUser) {
		Ong ong = (Ong) ongRepository.findByEmail(currentUser.getUsername());
		evento.setDataInclusao(new Timestamp(new Date().getTime()));
		if (evento.getHabilitado() == false) {
			evento.setDataDesabilitado(new Timestamp(new Date().getTime()));
		} else {
			evento.setDataDesabilitado(null);
		}
		evento.setOng(ong);
		LOG.info("Evento novo criado com sucesso!");
		return evento;
	}
	
	public Evento edita(Evento servico, EventoDTO eventoDTO) {
		servico.setNome(eventoDTO.getNome());
		servico.setDescricao(eventoDTO.getDescricao());
		servico.setHabilitado(eventoDTO.getHabilitado());
		servico.setDataAtualizacao(new Timestamp(new Date().getTime()));
		if (servico.getHabilitado() == false) {
			servico.setDataDesabilitado(new Timestamp(new Date().getTime()));
		} else {
			servico.setDataDesabilitado(null);
		}
		return servico;
	}

	public void save(Evento servico) {
		eventoRepository.save(servico);
	}
	
	public List<Evento> getAllByOng(UserDetails currentUser) {
		Ong ong = (Ong) ongRepository.findByEmail(currentUser.getUsername());
		return eventoRepository.findAllByOng(ong);
	}

	public List<Evento> getAll() {
		return eventoRepository.findAll();
	}

	public Evento getByNome(String nome) {
		return eventoRepository.findByNome(nome);
	}
	
	public List<Evento> getNomeByOng(UserDetails currentUser) {
		Ong ong = (Ong) ongRepository.findByEmail(currentUser.getUsername());
		return eventoRepository.findNomeByOng(ong);
	}
	
	public Evento get(Long id) {
		return eventoRepository.findById(id).get();
	}
	
	public void delete(Long id) {
		eventoRepository.deleteById(id);
	}

	public boolean exists(Evento evento) {
		return getByNome(evento.getNome()) != null;
	}

}
