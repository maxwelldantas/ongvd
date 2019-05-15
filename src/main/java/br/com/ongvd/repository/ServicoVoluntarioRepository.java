package br.com.ongvd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ongvd.model.Ong;
import br.com.ongvd.model.ServicoVoluntario;

@Repository
public interface ServicoVoluntarioRepository extends JpaRepository<ServicoVoluntario, Long> {
	ServicoVoluntario findByNome(String nome);
	List<ServicoVoluntario> findAllByOng(Ong ong);
}
