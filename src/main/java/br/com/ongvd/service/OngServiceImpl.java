package br.com.ongvd.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.ongvd.dto.EnderecoDTO;
import br.com.ongvd.dto.OngDTO;
import br.com.ongvd.model.Endereco;
import br.com.ongvd.model.Ong;
import br.com.ongvd.model.Role;
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

	public void save(OngDTO ongDTO, EnderecoDTO enderecoDTO) {
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
		ong.setAtivo(new Boolean(true));
		ong.setEnderecos(Arrays.asList(new Endereco(enderecoDTO.getCep(), enderecoDTO.getLogradouro(),
				enderecoDTO.getNumero(), enderecoDTO.getComplemento(), enderecoDTO.getBairro(), enderecoDTO.getCidade(),
				enderecoDTO.getUf())));
		ong.setRoles(Arrays.asList(new Role("ROLE_USER")));
		ongRepository.save(ong);
	}
	
    public List<Ong> getAll() {
        return ongRepository.findAll();
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
    
    public Optional<Ong> findById(@PathVariable Long id) {
        Optional<Ong> ong = ongRepository.findById(id);
        if (!ong.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        ResponseEntity.ok().build();
        return findById(id);
    }

    public void update(@PathVariable Long id, @Valid @RequestBody Ong ong) {
        if (!ongRepository.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        ongRepository.save(ong);
        ResponseEntity.ok().build();
    }

    public void delete(@PathVariable Long id) {
        if (!ongRepository.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        ongRepository.deleteById(id);
        ResponseEntity.ok().build();
    }

    public boolean exists(Ong ong) {
        return findByRazaoSocial(ong.getRazaoSocial()) != null;
    }

}
