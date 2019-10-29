package br.com.dasa.api.termo.service.impl;

import java.util.Date;
import java.util.Optional;

import br.com.dasa.api.termo.entity.VersionTerm;
import br.com.dasa.api.termo.enumeration.StatusTermUse;
import br.com.dasa.api.termo.service.VersionTermService;
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
		verificaFlag(termOfUser);

		TermOfUser novoTermo = termOfUserRepository.save(termOfUser);
		atualizaStatus(novoTermo);

		return  novoTermo;
	}


	public Integer verificaFlag(TermOfUser termOfUser){
		Integer novaVersao = null;
		if(termOfUser.isFlag()){
			novaVersao = versionTermImpl.criaVersao(new VersionTerm(termOfUser.getDescriptionTerm()));
			termOfUser.setVersion("V".concat(novaVersao.toString()));
		}

		return novaVersao;
	}

	/**
	 * Metodo Responsável por atualizar status caso encontre versão
	 * @param termOfUser
	 * @return
	 */
	@Override
	public void atualizaStatus(TermOfUser termOfUser){
		Optional<TermOfUser> versaoAnterior = findById(termOfUser.getId() - 1);
		if(versaoAnterior.isPresent())
			versaoAnterior.get().setStatus(StatusTermUse.INACTIVE);
	}


	@Override
	public Optional<TermOfUser> findById(long id) {
		return termOfUserRepository.findById(id);
	}


	
}
