package br.com.ongvd.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
	
	public Evento novo(EventoDTO dto, @AuthenticationPrincipal UserDetails currentUser) {
		Ong ong = (Ong) ongRepository.findByEmail(currentUser.getUsername());
		Evento evento = new Evento();
		evento.setNome(dto.getNome());
		evento.setDescricao(dto.getDescricao());
		evento.setContribuicaoParaEvento(dto.getContribuicaoParaEvento());
		evento.setDuracaoEvento(dto.getDuracaoEvento());
		evento.setOrcamento(dto.getOrcamento());
		evento.setHabilitado(dto.getHabilitado());
		evento.setDataInclusao(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
		if (evento.getHabilitado() == false) {
			evento.setDataDesabilitado(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
		} else {
			evento.setDataDesabilitado(null);
		}
		evento.setOng(ong);
		LOG.info("Evento novo criado com sucesso!");
		return evento;
	}
	
	public Evento edita(Evento evento, EventoDTO eventoDTO) {
		evento.setNome(eventoDTO.getNome());
		evento.setDescricao(eventoDTO.getDescricao());
		evento.setHabilitado(eventoDTO.getHabilitado());
		evento.setDataAtualizacao(new Timestamp(new Date().getTime()));
		if (evento.getHabilitado() == false) {
			evento.setDataDesabilitado(new Timestamp(new Date().getTime()));
		} else {
			evento.setDataDesabilitado(null);
		}
		return evento;
	}

	public void save(Evento evento) {
		eventoRepository.save(evento);
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
