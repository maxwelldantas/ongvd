//package br.com.ongvd.service;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import javax.validation.Valid;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import br.com.ongvd.dto.EnderecoDTO;
//import br.com.ongvd.dto.OngDTO;
//import br.com.ongvd.model.Endereco;
//import br.com.ongvd.model.Ong;
//import br.com.ongvd.model.Role;
//import br.com.ongvd.repository.OngRepository;
//
//@Service
//public class PedidoDoacaoServiceImpl implements OngService {
//	
//	private final Logger LOG = LoggerFactory.getLogger(PedidoDoacaoServiceImpl.class);
//
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		Ong ong = ongRepository.findByEmail(email);
//		if (ong == null) {
//			throw new UsernameNotFoundException("E-mail ou senha inválidos.");
//		}
//		return new org.springframework.security.core.userdetails.User(ong.getEmail(), ong.getSenha(),
//				mapRolesToAuthorities(ong.getRoles()));
//	}
//
//	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNome())).collect(Collectors.toList());
//	}
//
//	public Ong findByEmail(String email) {
//		return ongRepository.findByEmail(email);
//	}
//	
//	public void save(OngDTO ongDTO, EnderecoDTO enderecoDTO) {
//		Ong ong = new Ong();
//		ong.setRazaoSocial(ongDTO.getRazaoSocial());
//		ong.setNomeFantasia(ongDTO.getNomeFantasia());
//		ong.setCnpj(ongDTO.getCnpj());
//		ong.setRamoAtividade(ongDTO.getRamoAtividade());
//		ong.setWebsite(ongDTO.getWebsite());
//		ong.setDescricao(ongDTO.getDescricao());
//		ong.setNomeContato(ongDTO.getNomeContato());
//		ong.setTelefone(ongDTO.getTelefone());
//		ong.setEmail(ongDTO.getEmail());
//		ong.setSenha(passwordEncoder.encode(ongDTO.getSenha()));
//		ong.setAtivo(new Boolean(true));
//		ong.setEnderecos(Arrays.asList(new Endereco(enderecoDTO.getCep(), enderecoDTO.getLogradouro(),
//				enderecoDTO.getNumero(), enderecoDTO.getComplemento(), enderecoDTO.getBairro(), enderecoDTO.getCidade(),
//				enderecoDTO.getUf())));
//		ong.setRoles(Arrays.asList(new Role("ROLE_USER")));
//		ongRepository.save(ong);
//	}
//	
//    public ResponseEntity<List<Ong>> getAll() {
//        return ResponseEntity.ok(ongRepository.findAll());
//    }
//
//    public ResponseEntity<Ong> findByRazaoSocial(String razaoSocial) {
//        return ResponseEntity.ok(ongRepository.findByRazaoSocial(razaoSocial));
//    }
//    
//    public Optional<Ong> findById(@PathVariable Long id) {
//        Optional<Ong> ong = ongRepository.findById(id);
//        if (!ong.isPresent()) {
//            LOG.error("Id " + id + " is not existed");
//            ResponseEntity.badRequest().build();
//        }
//        return findById(id);
//    }
//
//    public void update(@PathVariable Long id, @Valid @RequestBody Ong ong) {
//        if (!ongRepository.findById(id).isPresent()) {
//            LOG.error("Id " + id + " is not existed");
//            ResponseEntity.badRequest().build();
//        }
//        ongRepository.save(ong);
//    }
//
//    public void delete(@PathVariable Long id) {
//        if (!ongRepository.findById(id).isPresent()) {
//            LOG.error("Id " + id + " is not existed");
//            ResponseEntity.badRequest().build();
//        }
//        ongRepository.deleteById(id);
//        ResponseEntity.ok().build();
//    }
//
//    public boolean exists(Ong ong) {
//        return findByRazaoSocial(ong.getRazaoSocial()) != null;
//    }
//
//}
