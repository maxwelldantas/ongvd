package br.com.ongvd.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ongvd.dto.EnderecoDTO;
import br.com.ongvd.dto.OngDTO;
import br.com.ongvd.dto.OngEdicaoDTO;
import br.com.ongvd.entity.Endereco;
import br.com.ongvd.entity.Ong;
import br.com.ongvd.entity.Role;
import br.com.ongvd.repository.OngRepository;

@Service
public class OngServiceImpl implements OngService {
	
	@Autowired
	private OngRepository ongRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Ong ong = ongRepository.findByEmail(email);
		if (ong == null) {
			throw new UsernameNotFoundException("E-mail ou senha inválidos.");
		}
		return new org.springframework.security.core.userdetails.User(ong.getEmail(), ong.getSenha(),
				mapRolesToAuthorities(ong.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNome())).collect(Collectors.toList());
	}

	public void novo(OngDTO ongDTO, EnderecoDTO enderecoDTO) {
		Ong ong = new Ong();
		ong.setRazaoSocial(ongDTO.getRazaoSocial());
		ong.setNomeFantasia(ongDTO.getNomeFantasia());
		ong.setCnpj(ongDTO.getCnpj());
		ong.setAreaDeAtuacao(ongDTO.getAreaDeAtuacao());
		ong.setWebsite(ongDTO.getWebsite());
		ong.setResponsavel(ongDTO.getResponsavel());
		ong.setFundacao(ongDTO.getFundacao());
		ong.setContato(ongDTO.getContato());
		ong.setWhatsapp(ongDTO.getWhatsapp());
		ong.setTelefone(ongDTO.getTelefone());
		ong.setEmail(ongDTO.getEmail());
		ong.setSenha(passwordEncoder.encode(ongDTO.getSenha()));
		ong.setAtivo(Boolean.TRUE);
		ong.setEnderecos(Arrays.asList((new Endereco(enderecoDTO.getCep(), enderecoDTO.getLogradouro(),
				enderecoDTO.getNumero(), enderecoDTO.getComplemento(), enderecoDTO.getBairro(), enderecoDTO.getCidade(),
				enderecoDTO.getUf()))));
		ong.setRoles(Arrays.asList(new Role("ROLE_USER")));
		ongRepository.save(ong);
	}
	
	public Ong edita(Ong ong, OngEdicaoDTO ongEdicaoDTO) {
		ong.setRazaoSocial(ongEdicaoDTO.getRazaoSocial());
		ong.setNomeFantasia(ongEdicaoDTO.getNomeFantasia());
		ong.setCnpj(ongEdicaoDTO.getCnpj());
		ong.setAreaDeAtuacao(ongEdicaoDTO.getAreaDeAtuacao());
		ong.setWebsite(ongEdicaoDTO.getWebsite());
		ong.setResponsavel(ongEdicaoDTO.getResponsavel());
		ong.setFundacao(ongEdicaoDTO.getFundacao());
		ong.setContato(ongEdicaoDTO.getContato());
		ong.setWhatsapp(ongEdicaoDTO.getWhatsapp());
		ong.setTelefone(ongEdicaoDTO.getTelefone());
		ong.setEmail(ongEdicaoDTO.getEmail());
		ong.setSenha(passwordEncoder.encode(ongEdicaoDTO.getSenha()));
		save(ong);
		return ong;
	}
	
	public Ong editaEndereco(Ong ong, EnderecoDTO enderecoDTO, Endereco endereco) {
		endereco.setCep(enderecoDTO.getCep());
		endereco.setLogradouro(enderecoDTO.getLogradouro());
		endereco.setNumero(enderecoDTO.getNumero());
		endereco.setComplemento(enderecoDTO.getComplemento());
		endereco.setBairro(enderecoDTO.getBairro());
		endereco.setCidade(enderecoDTO.getCidade());
		endereco.setUf(enderecoDTO.getUf());
		ong.setEnderecos(new ArrayList<>(Arrays.asList(endereco)));
		save(ong);
		return ong;
	}
	
    public List<Ong> getAll() {
        return ongRepository.findAll();
    }
    
    public void save(Ong ong) {
    	ongRepository.save(ong);
    }
    
    public Ong findByEmail(String email) {
		return ongRepository.findByEmail(email);
	}
    
    public Ong findByCnpj(String cnpj) {
    	return ongRepository.findByCnpj(cnpj);
    }

    public Ong findByRazaoSocial(String razaoSocial) {
        return ongRepository.findByRazaoSocial(razaoSocial);
    }
    
    public Ong findById(Long id) {
        return ongRepository.findById(id).get();
    }
    
    public void delete(Long id) {
        ongRepository.deleteById(id);
    }

    public boolean exists(Ong ong) {
        return findByRazaoSocial(ong.getRazaoSocial()) != null;
    }

}
