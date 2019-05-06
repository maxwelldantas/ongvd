package br.com.ongvd.service;

import org.springframework.stereotype.Service;

import br.com.ongvd.dto.EnderecoDTO;

@Service
public interface EnderecoService {
    void save(EnderecoDTO enderecoDTO);
}
