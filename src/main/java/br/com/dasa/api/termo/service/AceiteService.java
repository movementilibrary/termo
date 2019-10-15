package br.com.dasa.api.termo.service;

import br.com.dasa.api.termo.entity.AceiteTermo;
import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.entity.json.AceiteTermoJson;
import br.com.dasa.api.termo.exceptions.ApiException;
import br.com.dasa.api.termo.exceptions.enumsExceptions.AceiteTermoEnums;
import br.com.dasa.api.termo.repository.AceiteRepository;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AceiteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AceiteService.class);

    @Autowired
    private AceiteRepository aceiteRepository;

    @Autowired
    private TermOfUserRepository termOfUserRepository;


    public void salvarAceite(AceiteTermoJson aceiteTermoJson) {
        Optional<TermOfUser> termOfUser = this.termOfUserRepository.findById(aceiteTermoJson.getIdTermo());
        try {
            if (termOfUser.isPresent()) {
                AceiteTermo termo = new AceiteTermo(aceiteTermoJson.getMdmId(),
                        aceiteTermoJson.isRespostaCliente(), termOfUser.get());
                this.aceiteRepository.save(termo);
            } else {
                LOGGER.info(AceiteTermoEnums.ID_NAO_ENCONTRADO.getMsg());
                return;
            }

        } catch (ApiException e) {
            LOGGER.error(e.getMessage(), e);
            new Exception(e.getMessage().concat(e.getErro()));

        }

    }
}
