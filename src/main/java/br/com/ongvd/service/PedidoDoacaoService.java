package br.com.ongvd.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.ongvd.dto.PedidoDoacaoDTO;
import br.com.ongvd.entity.PedidoDoacao;

@Service
public interface PedidoDoacaoService {
	
	PedidoDoacao novo(PedidoDoacao pedidoDoacao, UserDetails currentUser);

	PedidoDoacao edita(PedidoDoacao pedido, PedidoDoacaoDTO pedidoDoacaoDTO);

	void save(PedidoDoacao pedido);

	List<PedidoDoacao> getAll();

	List<PedidoDoacao> getAllByOng(UserDetails currentUser);
	
	List<PedidoDoacao> getAllHabilitadoTrueAndOngAtivoTrue(List<PedidoDoacao> pedidosOld);
	
	PedidoDoacao get(Long id);

	void delete(Long id);

	PedidoDoacao getByNome(String nome);

	List<PedidoDoacao> getNomeByOng(UserDetails currentUser);

	boolean exists(PedidoDoacao pedidoDoacao);
}