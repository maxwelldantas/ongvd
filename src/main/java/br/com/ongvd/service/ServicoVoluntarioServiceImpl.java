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

import br.com.ongvd.dto.ServicoVoluntarioDTO;
import br.com.ongvd.entity.Ong;
import br.com.ongvd.entity.ServicoVoluntario;
import br.com.ongvd.repository.OngRepository;
import br.com.ongvd.repository.ServicoVoluntarioRepository;

@Service
@Transactional
public class ServicoVoluntarioServiceImpl implements ServicoVoluntarioService {

	@Autowired
	private ServicoVoluntarioRepository servicoVoluntarioRepository;

	@Autowired
	private OngRepository ongRepository;
	
	public ServicoVoluntario novo(ServicoVoluntario servicoVoluntario, @AuthenticationPrincipal UserDetails currentUser) {
		Ong ong = (Ong) ongRepository.findByEmail(currentUser.getUsername());
		servicoVoluntario.setDataInclusao(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
		if (servicoVoluntario.getHabilitado() == false) {
			servicoVoluntario.setDataDesabilitado(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
		} else {
			servicoVoluntario.setDataDesabilitado(null);
		}
		servicoVoluntario.setOng(ong);
		return servicoVoluntario;
	}
	
	public ServicoVoluntario edita(ServicoVoluntario servico, ServicoVoluntarioDTO servicoVoluntarioDTO) {
		servico.setNome(servicoVoluntarioDTO.getNome());
		servico.setDescricao(servicoVoluntarioDTO.getDescricao());
		servico.setHabilitado(servicoVoluntarioDTO.getHabilitado());
		servico.setDataAtualizacao(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
		if (servico.getHabilitado() == false) {
			servico.setDataDesabilitado(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
		} else {
			servico.setDataDesabilitado(null);
		}
		return servico;
	}

	public void save(ServicoVoluntario servico) {
		servicoVoluntarioRepository.save(servico);
	}
	
	public List<ServicoVoluntario> getAllByOng(UserDetails currentUser) {
		Ong ong = (Ong) ongRepository.findByEmail(currentUser.getUsername());
		return servicoVoluntarioRepository.findAllByOng(ong);
	}

	public List<ServicoVoluntario> getAll() {
		return servicoVoluntarioRepository.findAll();
	}
	
	public List<ServicoVoluntario> getAllHabilitadoTrueAndOngAtivoTrue(List<ServicoVoluntario> servicosOld) {
		List<ServicoVoluntario> servicosNew = new ArrayList<>();
		for (ServicoVoluntario servico : servicosOld) {
			if (servico.getHabilitado().equals(true) && servico.getOng().getAtivo().equals(true)) {
				servicosNew.add(servico);
			}
		}
		return servicosNew;
	}

	public ServicoVoluntario getByNome(String nome) {
		return servicoVoluntarioRepository.findByNome(nome);
	}
	
	public List<ServicoVoluntario> getNomeByOng(UserDetails currentUser) {
		Ong ong = (Ong) ongRepository.findByEmail(currentUser.getUsername());
		return servicoVoluntarioRepository.findNomeByOng(ong);
	}
	
	public ServicoVoluntario get(Long id) {
		return servicoVoluntarioRepository.findById(id).get();
	}
	
	public void delete(Long id) {
		servicoVoluntarioRepository.deleteById(id);
	}

	public boolean exists(ServicoVoluntario servicoVoluntario) {
		return getByNome(servicoVoluntario.getNome()) != null;
	}

}
