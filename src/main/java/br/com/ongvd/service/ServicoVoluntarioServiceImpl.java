package br.com.ongvd.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ongvd.dto.ServicoVoluntarioDTO;
import br.com.ongvd.model.Ong;
import br.com.ongvd.model.ServicoVoluntario;
import br.com.ongvd.repository.OngRepository;
import br.com.ongvd.repository.ServicoVoluntarioRepository;

@Service
public class ServicoVoluntarioServiceImpl implements ServicoVoluntarioService {
	
    @Autowired
	private ServicoVoluntarioRepository servicoVoluntarioRepository;
    
    @Autowired
    private OngRepository ongRepository;

	public void save(ServicoVoluntarioDTO dto, @AuthenticationPrincipal UserDetails currentUser) {
		Ong ong = (Ong) ongRepository.findByEmail(currentUser.getUsername());
		ServicoVoluntario servicoVoluntario = new ServicoVoluntario();
		servicoVoluntario.setNome(dto.getNome());
		servicoVoluntario.setDescricao(dto.getDescricao());
		servicoVoluntario.setDataInclusao(new Timestamp(new Date().getTime()));
		servicoVoluntario.setHabilitado(dto.getHabilitado());
		servicoVoluntario.setOng(ong);
		servicoVoluntarioRepository.save(servicoVoluntario);
	}
	
    public List<ServicoVoluntario> getAll() {
        return servicoVoluntarioRepository.findAll();
    }

    public ServicoVoluntario findByNome(String nome) {
        return servicoVoluntarioRepository.findByNome(nome);
    }
    
    public Optional<ServicoVoluntario> update(Long id) {
    	return servicoVoluntarioRepository.findById(id);
    }

    public void delete(@PathVariable Long id) {
//        if (!servicoVoluntarioRepository.findById(id).isPresent()) {
//        }
        servicoVoluntarioRepository.deleteById(id);
    }

    public boolean exists(ServicoVoluntario servicoVoluntario) {
        return findByNome(servicoVoluntario.getNome()) != null;
    }

}
