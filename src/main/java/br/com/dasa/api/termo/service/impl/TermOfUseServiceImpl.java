package br.com.dasa.api.termo.service.impl;

import java.util.Date;
import java.util.Optional;

import br.com.dasa.api.termo.entity.VersionTerm;
import br.com.dasa.api.termo.enumeration.StatusTermUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import br.com.dasa.api.termo.service.TermOfUseService;

@Service
public class TermOfUseServiceImpl implements TermOfUseService {

	@Autowired
	private TermOfUserRepository termOfUserRepository;

	@Autowired
	private VersionTermImpl versionTermImpl;


	@Override
	public TermOfUser save(TermOfUser termOfUser) {
		termOfUser.setCurrentDate(new Date());
		TermOfUser novoTermo = termOfUserRepository.save(termOfUser);
		return  novoTermo;
	}


	public TermOfUser verificaFlagisMaked(TermOfUser termOfUser){
		Integer novaVersao = null;
		if(termOfUser.isFlag()){
			termOfUser.setVersion(versionTermImpl.criaVersao(new VersionTerm(termOfUser.getDescriptionTerm())).toString());
			atualizaStatus(termOfUser);
		}

		return termOfUser;

	}

	/**
	 * Metodo Responsável por atualizar status caso encontre versão
	 * @param termOfUser
	 * @return
	 */
	@Override
	public void atualizaStatus(TermOfUser termOfUser){
		Optional<TermOfUser> versaoAnterior = findById(termOfUser.getId() - 1);
		if(versaoAnterior.isPresent()) {
			versaoAnterior.get().setStatus(StatusTermUse.INACTIVE);
		    save(versaoAnterior.get());
		}
	}


	@Override
	public Optional<TermOfUser> findById(long id) {
		return termOfUserRepository.findById(id);
	}


	//TODO: zerar subVersao
}
