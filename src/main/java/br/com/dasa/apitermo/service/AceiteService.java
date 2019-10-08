package br.com.dasa.apitermo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dasa.apitermo.exceptions.TermoPersistenciaExceptions;
import br.com.dasa.apitermo.model.AceiteTermo;
import br.com.dasa.apitermo.repository.AceiteRepository;

@Service
public class AceiteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AceiteService.class);

    @Autowired
    private AceiteRepository aceiteRepository;


    public boolean salvarAceite(AceiteTermo aceiteTermo) {
        try {
        
            this.aceiteRepository.save(aceiteTermo);
            return true;
        } catch (Exception e) {
            LOGGER.error(String.valueOf(new TermoPersistenciaExceptions(e.getMessage())));


        }
        return false;
    }
}
