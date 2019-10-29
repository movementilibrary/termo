package br.com.dasa.api.termo.service.impl;

import br.com.dasa.api.termo.entity.VersionTerm;

import br.com.dasa.api.termo.repository.VersionTermRepository;
import br.com.dasa.api.termo.service.VersionTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionTermImpl implements VersionTermService {

    @Autowired
    private VersionTermRepository versionTermRepository;


    /**
     * Metodo responsável por retornar nova Versao
     * @param versionTerm
     * @return
     */
    @Override
    public Integer criaVersao(VersionTerm versionTerm) {
        return versionTermRepository.save(versionTerm).getId();
    }



}
