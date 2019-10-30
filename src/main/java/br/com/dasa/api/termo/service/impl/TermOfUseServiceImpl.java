package br.com.dasa.api.termo.service.impl;

import java.util.Date;
import java.util.Optional;

import br.com.dasa.api.termo.entity.SubVersion;
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

	@Autowired
	private SubVersionImpl subVersionImpl;


	@Override
	public TermOfUser save(TermOfUser termOfUser) {
		termOfUser.setCurrentDate(new Date());
		String newVersion = null;
		if(termOfUser.isFlag()){
			newVersion = generatedNewVersion();
		}else {
			newVersion = generatedNewSubVersion();
		}
		termOfUser.setVersion(newVersion);
		TermOfUser novoTermo = termOfUserRepository.save(termOfUser);
		return  novoTermo;
	}


    /**
     * Metodo responsável por vertificar se Flag is marcada, caso esteja marcada gera Versao Grande
     * caso não estaje marcada gera versão menor
     * @param
     * @return
     */
	public String generatedNewVersion(){
		Integer newVersion = null;
			VersionTerm currenteVersion = versionTermImpl.findById(1);
			if(currenteVersion==null){
				newVersion = versionTermImpl.saveNewVersion(new VersionTerm(1));
			}else{
				currenteVersion.setVersion(currenteVersion.getVersion()+1);
				newVersion = versionTermImpl.saveNewVersion(currenteVersion);

				SubVersion currentSubVersion = subVersionImpl.findById(1);
				subVersionImpl.updateSubVersion(currentSubVersion);
			}

		return "V".concat(newVersion.toString());

	}


	public String generatedNewSubVersion(){
		Integer newSubVersion = null;
		SubVersion currentSubVersion = subVersionImpl.findById(1);
		if(currentSubVersion==null){
			newSubVersion = subVersionImpl.saveNewSubVersion(new SubVersion(1));
		}else {
			currentSubVersion.setSubVersion(currentSubVersion.getId()+1);
			newSubVersion = subVersionImpl.saveNewSubVersion(currentSubVersion);

			VersionTerm currenteVersion = versionTermImpl.findById(1);
		}
		return "V".concat(currentSubVersion.toString()).concat(".").concat(newSubVersion.toString());
	}


    /**
     * Metodo Responsáve por retornar TermOfUser pelo Id
     * @param id
     * @return
     */
	@Override
	public Optional<TermOfUser> findById(long id) {
		return termOfUserRepository.findById(id);
	}

//TODO: inativar versao anterior
}
