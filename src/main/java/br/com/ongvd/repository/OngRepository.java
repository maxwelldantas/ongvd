package br.com.ongvd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ongvd.model.Ong;

@Repository
public interface OngRepository extends JpaRepository<Ong, Long> {
	Ong findByEmail(String email);
	Ong findByRazaoSocial(String razaoSocial);
	Ong findByCnpj(String cnpj);
}
