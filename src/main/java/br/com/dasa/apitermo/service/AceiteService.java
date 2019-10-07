package br.com.dasa.apitermo.service;

import br.com.dasa.apitermo.exceptions.ApiExceptions;
import br.com.dasa.apitermo.model.AceiteTermo;
import br.com.dasa.apitermo.repository.AceiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AceiteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AceiteService.class);

    @Autowired
    private AceiteRepository aceiteRepository;


    public void salvarAceite(AceiteTermo aceiteTermo){
        try {
            this.aceiteRepository.save(aceiteTermo);

        }catch (ApiExceptions e){
            LOGGER.error(e.getMessage());
        }
    }
}
