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

import br.com.ongvd.dto.PedidoDoacaoDTO;
import br.com.ongvd.model.Ong;
import br.com.ongvd.model.PedidoDoacao;
import br.com.ongvd.repository.OngRepository;
import br.com.ongvd.repository.PedidoDoacaoRepository;

@Service
public class PedidoDoacaoServiceImpl implements PedidoDoacaoService {
	
	private final Logger LOG = LoggerFactory.getLogger(PedidoDoacaoServiceImpl.class);

	@Autowired
	private PedidoDoacaoRepository pedidoDoacaoRepository;

	@Autowired
	private OngRepository ongRepository;
	
	public PedidoDoacao novo(PedidoDoacao pedidoDoacao, @AuthenticationPrincipal UserDetails currentUser) {
		Ong ong = (Ong) ongRepository.findByEmail(currentUser.getUsername());
		pedidoDoacao.setDataInclusao(new Timestamp(new Date().getTime()));
		if (pedidoDoacao.getHabilitado() == false) {
			pedidoDoacao.setDataDesabilitado(new Timestamp(new Date().getTime()));
		} else {
			pedidoDoacao.setDataDesabilitado(null);
		}
		pedidoDoacao.setOng(ong);
		LOG.info("Pedido de Doação cadastrado com sucesso!");
		return pedidoDoacao;
	}
	
	public PedidoDoacao edita(PedidoDoacao servico, PedidoDoacaoDTO pedidoDoacaoDTO) {
		servico.setNome(pedidoDoacaoDTO.getNome());
		servico.setDescricao(pedidoDoacaoDTO.getDescricao());
		servico.setHabilitado(pedidoDoacaoDTO.getHabilitado());
		servico.setDataAtualizacao(new Timestamp(new Date().getTime()));
		if (servico.getHabilitado() == false) {
			servico.setDataDesabilitado(new Timestamp(new Date().getTime()));
		} else {
			servico.setDataDesabilitado(null);
		}
		return servico;
	}

	public void save(PedidoDoacao servico) {
		pedidoDoacaoRepository.save(servico);
	}
	
	public List<PedidoDoacao> getAllByOng(UserDetails currentUser) {
		Ong ong = (Ong) ongRepository.findByEmail(currentUser.getUsername());
		return pedidoDoacaoRepository.findAllByOng(ong);
	}

	public List<PedidoDoacao> getAll() {
		return pedidoDoacaoRepository.findAll();
	}

	public PedidoDoacao getByNome(String nome) {
		return pedidoDoacaoRepository.findByNome(nome);
	}
	
	public List<PedidoDoacao> getNomeByOng(UserDetails currentUser) {
		Ong ong = (Ong) ongRepository.findByEmail(currentUser.getUsername());
		return pedidoDoacaoRepository.findNomeByOng(ong);
	}
	
	public PedidoDoacao get(Long id) {
		return pedidoDoacaoRepository.findById(id).get();
	}
	
	public void delete(Long id) {
		pedidoDoacaoRepository.deleteById(id);
	}

	public boolean exists(PedidoDoacao pedidoDoacao) {
		return getByNome(pedidoDoacao.getNome()) != null;
	}

}
