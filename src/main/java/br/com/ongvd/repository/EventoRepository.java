package br.com.ongvd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ongvd.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {}
