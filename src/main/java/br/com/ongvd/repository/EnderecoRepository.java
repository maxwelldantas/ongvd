package br.com.ongvd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ongvd.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {}
