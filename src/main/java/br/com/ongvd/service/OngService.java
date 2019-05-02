package br.com.ongvd.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.ongvd.dto.OngDTO;
import br.com.ongvd.model.Ong;

@Service
public interface OngService extends UserDetailsService {
    Ong findByEmail(String email);
    Ong save(OngDTO ongDTO);
}