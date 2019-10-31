package br.com.dasa.api.termo.service.impl;

import br.com.dasa.api.termo.entity.VersionTerm;

import br.com.dasa.api.termo.exceptions.ResourceNotFoundException;
import br.com.dasa.api.termo.repository.VersionTermRepository;
import br.com.dasa.api.termo.service.VersionTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public VersionTerm saveNewVersion(VersionTerm versionTerm) {
        VersionTerm currentVersion = null;
        try {
             currentVersion = versionTermRepository.save(versionTerm);
        }catch (Exception e){
            e.getMessage();
        }
      return currentVersion;
    }

    @Override
    public VersionTerm findById(Integer id) {
        Optional<VersionTerm> currentVersionTerm = null;
        try {
            currentVersionTerm = versionTermRepository.findById(id);

        }catch (Exception e){
            e.getMessage();
        }
        return  currentVersionTerm.orElse(null);
    }


}
