package br.com.ongvd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ongvd.model.PedidoDoacao;

@Repository
public interface PedidoDoacaoRepository extends JpaRepository<PedidoDoacao, Long> {}
