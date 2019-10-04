package br.com.dasa.apitermo.service;

import br.com.dasa.apitermo.exceptions.ApiExceptions;
import br.com.dasa.apitermo.repository.TermoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TermoService.class);

    @Autowired
    private TermoRepository termoRepository;


    public void salvarTermo(String descricao, String versao){
            try {

//                this.termoRepository.saveByTermo(descricao, versao);


            }catch (ApiExceptions e){
                    LOGGER.error(e.getMessage());
                    return;
            }

    }
}
