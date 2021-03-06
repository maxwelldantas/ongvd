package br.com.ongvd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ongvd.entity.Ong;
import br.com.ongvd.entity.PedidoDoacao;

@Repository
public interface PedidoDoacaoRepository extends JpaRepository<PedidoDoacao, Long> {

	PedidoDoacao findByNome(String nome);

	List<PedidoDoacao> findAllByOng(Ong ong);

	List<PedidoDoacao> findNomeByOng(Ong ong);
}
