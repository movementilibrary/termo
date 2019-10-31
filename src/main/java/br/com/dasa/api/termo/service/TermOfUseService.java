package br.com.dasa.api.termo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.repository.TermOfUserRepository;

@Service
public class TermOfUseService {

	@Autowired
	private TermOfUserRepository termOfUserRepository;


	public Optional<TermOfUser> findById(long id) {
		return termOfUserRepository.findById(id);
	}

	
	public TermOfUser save(TermOfUser termOfUser) {
		termOfUser.setVersion("v1");

		return termOfUserRepository.save(termOfUser);
	}
	
	public TermOfUser buscarUltimoTermo() {
		return termOfUserRepository.buscarUltimoTermoAtivo(); 
	}

}
