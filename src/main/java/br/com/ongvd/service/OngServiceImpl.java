package br.com.ongvd.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ongvd.dto.EnderecoDTO;
import br.com.ongvd.dto.OngDTO;
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

	public Ong findByEmail(String email) {
		return ongRepository.findByEmail(email);
	}
	
	public void save(OngDTO ongDTO, EnderecoDTO enderecoDTO) {
		Ong ong = new Ong();
		ong.setRazaoSocial(ongDTO.getRazaoSocial());
		ong.setNomeFantasia(ongDTO.getNomeFantasia());
		ong.setCnpj(ongDTO.getCnpj());
		ong.setRamoAtividade(ongDTO.getRamoAtividade());
		ong.setWebsite(ongDTO.getWebsite());
		ong.setDescricao(ongDTO.getDescricao());
		ong.setNomeContato(ongDTO.getNomeContato());
		ong.setTelefone(ongDTO.getTelefone());
		ong.setEmail(ongDTO.getEmail());
		ong.setSenha(passwordEncoder.encode(ongDTO.getSenha()));
		ong.setAtivo(new Boolean(true));
		ong.setRoles(Arrays.asList(new Role("ROLE_USER")));
		ongRepository.save(ong);
	}

}
