package br.com.ongvd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ongvd.model.ServicoVoluntario;

@Repository
public interface ServicoVoluntarioRepository extends JpaRepository<ServicoVoluntario, Long> {
	ServicoVoluntario findByNome(String nome);
}
