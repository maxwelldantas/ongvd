package br.com.ongvd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.ongvd.dto.EnderecoDTO;
import br.com.ongvd.dto.OngDTO;
import br.com.ongvd.model.Ong;

@Service
public interface PedidoDoacaoService extends UserDetailsService {
	Ong findByEmail(String email);

	void save(OngDTO ongDTO, EnderecoDTO enderecoDTO);

	ResponseEntity<List<Ong>> getAll();

	Optional<Ong> findById(Long id);

	ResponseEntity<Ong> findByRazaoSocial(String razaoSocial);

	void update(Long id, Ong ong);

	void delete(Long id);

	boolean exists(Ong ong);
}