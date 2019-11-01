package br.com.dasa.api.termo.service;

import br.com.dasa.api.termo.entity.VersionTerm;

import br.com.dasa.api.termo.repository.VersionTermRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VersionTermService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionTermService.class);

    @Autowired
    private VersionTermRepository versionTermRepository;


    /**
     * Metodo responsável por retornar nova Versao
     * @param versionTerm
     * @return
     */

    public VersionTerm saveNewVersion(VersionTerm versionTerm) {
        VersionTerm currentVersion = null;
        try {
            LOGGER.info("Salvando nova versão");
             currentVersion = versionTermRepository.save(versionTerm);
        }catch (Exception e){
            LOGGER.error("Não foi possivel salvar nova versão ",  e.getMessage());
        }
      return currentVersion;
    }


    /**
     * Metodo responsável por buscar Verso do Termo pelo Id
     * @param id
     * @return
     */
    public VersionTerm findById(Integer id) {
        Optional<VersionTerm> currentVersionTerm = null;
        try {
            LOGGER.info("Buscando versão pelo id {}", id );
            currentVersionTerm = versionTermRepository.findById(id);

        }catch (Exception e){
            LOGGER.error("Não foi possivel criar nova versão ",  e.getMessage());
        }
        return  currentVersionTerm.orElse(null);
    }


}
