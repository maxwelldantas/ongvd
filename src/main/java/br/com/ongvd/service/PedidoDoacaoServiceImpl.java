package br.com.ongvd.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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
		pedidoDoacao.setDataInclusao(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
		if (pedidoDoacao.getHabilitado() == false) {
			pedidoDoacao.setDataDesabilitado(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
		} else {
			pedidoDoacao.setDataDesabilitado(null);
		}
		pedidoDoacao.setOng(ong);
		LOG.info("Pedido de Doação cadastrado com sucesso!");
		return pedidoDoacao;
	}
	
	public PedidoDoacao edita(PedidoDoacao pedidoDoacao, PedidoDoacaoDTO pedidoDoacaoDTO) {
		pedidoDoacao.setNome(pedidoDoacaoDTO.getNome());
		pedidoDoacao.setDescricao(pedidoDoacaoDTO.getDescricao());
		pedidoDoacao.setHabilitado(pedidoDoacaoDTO.getHabilitado());
		pedidoDoacao.setItemPedido(pedidoDoacaoDTO.getItemPedido());
		pedidoDoacao.setValorPedido(pedidoDoacaoDTO.getValorPedido());
		pedidoDoacao.setDataAtualizacao(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
		if (pedidoDoacao.getHabilitado() == false) {
			pedidoDoacao.setDataDesabilitado(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
		} else {
			pedidoDoacao.setDataDesabilitado(null);
		}
		LOG.info("Pedido de Doação atualizado com sucesso!");
		return pedidoDoacao;
	}

	public void save(PedidoDoacao pedido) {
		pedidoDoacaoRepository.save(pedido);
	}
	
	public List<PedidoDoacao> getAllByOng(UserDetails currentUser) {
		Ong ong = (Ong) ongRepository.findByEmail(currentUser.getUsername());
		return pedidoDoacaoRepository.findAllByOng(ong);
	}

	public List<PedidoDoacao> getAll() {
		return pedidoDoacaoRepository.findAll();
	}
	
	public List<PedidoDoacao> getAllHabilitadoTrueAndOngAtivoTrue(List<PedidoDoacao> pedidosOld) {
		List<PedidoDoacao> pedidosNew = new ArrayList<>();
		for (PedidoDoacao pedido: pedidosOld) {
			if (pedido.getHabilitado().equals(true) && pedido.getOng().getAtivo().equals(true)) {
				pedidosNew.add(pedido);
			}
		}
		return pedidosNew;
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
