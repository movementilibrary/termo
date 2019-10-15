package br.com.dasa.api.termo.service;

import br.com.dasa.api.termo.entity.AceiteTermo;
import br.com.dasa.api.termo.entity.AceiteTermoJson;
import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.exceptions.enumsExceptions.AceiteTermoEnums;
import br.com.dasa.api.termo.repository.AceiteRepository;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

@Service
public class AceiteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AceiteService.class);

    @Autowired
    private AceiteRepository aceiteRepository;

    @Autowired
    private TermOfUserRepository termOfUserRepository;


    public boolean salvarAceite(AceiteTermoJson aceiteTermoJson) throws Exception {


        try {
            Optional<TermOfUser> termOfUser = this.termOfUserRepository.findById(aceiteTermoJson.getIdTermo());
            AceiteTermo termo = new AceiteTermo(aceiteTermoJson.getMdmId(),
                    aceiteTermoJson.isRespostaCliente(), termOfUser.get());

            if (termOfUser.isPresent()) {
                this.aceiteRepository.save(termo);
                return true;
            } else {
                throw new Exception(AceiteTermoEnums.ID_NAO_ENCONTRADO.getMsg());
            }


        } catch (HttpClientErrorException e) {
            throw new Exception(e);

        }
    }
}
