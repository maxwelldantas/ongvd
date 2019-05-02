package br.com.ongvd.service;

import org.springframework.stereotype.Service;

import br.com.ongvd.dto.EnderecoDTO;
import br.com.ongvd.model.Endereco;

@Service
public interface EnderecoService {
    Endereco save(EnderecoDTO enderecoDTO);    
}
