package br.com.ongvd.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ongvd.dto.EventoDTO;
import br.com.ongvd.entity.Evento;
import br.com.ongvd.entity.Ong;
import br.com.ongvd.repository.EventoRepository;
import br.com.ongvd.repository.OngRepository;

@Service
@Transactional
public class EventoServiceImpl implements EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private OngRepository ongRepository;
	
	public Evento novo(Evento evento, @AuthenticationPrincipal UserDetails currentUser) {
		Ong ong = ongRepository.findByEmail(currentUser.getUsername());
		evento.setDataInclusao(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
		if (evento.getHabilitado() == false) {
			evento.setDataDesabilitado(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
		} else {
			evento.setDataDesabilitado(null);
		}
		evento.setOng(ong);
		save(evento);
		return evento;
	}
	
	public Evento edita(Evento evento, EventoDTO eventoDTO) {
		evento.setNome(eventoDTO.getNome());
		evento.setDescricao(eventoDTO.getDescricao());
		evento.setIngresso(eventoDTO.getIngresso());
		evento.setHorario(eventoDTO.getHorario());
		evento.setHabilitado(eventoDTO.getHabilitado());
		evento.setCep(eventoDTO.getCep());
		evento.setLogradouro(eventoDTO.getLogradouro());
		evento.setNumero(eventoDTO.getNumero());
		evento.setComplemento(eventoDTO.getComplemento());
		evento.setBairro(eventoDTO.getBairro());
		evento.setCidade(eventoDTO.getCidade());
		evento.setUf(eventoDTO.getUf());
		evento.setDataAtualizacao(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
		if (evento.getHabilitado() == false) {
			evento.setDataDesabilitado(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
		} else {
			evento.setDataDesabilitado(null);
		}
		save(evento);
		return evento;
	}

	public void save(Evento evento) {
		eventoRepository.save(evento);
	}
	
	public List<Evento> getAllByOng(UserDetails currentUser) {
		Ong ong = ongRepository.findByEmail(currentUser.getUsername());
		return eventoRepository.findAllByOng(ong);
	}

	public List<Evento> getAll() {
		return eventoRepository.findAll();
	}
	
	public List<Evento> getAllHabilitadoTrueAndOngAtivoTrue(List<Evento> eventosOld) {
		List<Evento> eventosNew = new ArrayList<>();
		for (Evento evento : eventosOld) {
			if (evento.getHabilitado().equals(true) && evento.getOng().getAtivo().equals(true)) {
				eventosNew.add(evento);
			}
		}
		return eventosNew;
	}

	public Evento getByNome(String nome) {
		return eventoRepository.findByNome(nome);
	}
	
	public List<Evento> getNomeByOng(UserDetails currentUser) {
		Ong ong = ongRepository.findByEmail(currentUser.getUsername());
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
