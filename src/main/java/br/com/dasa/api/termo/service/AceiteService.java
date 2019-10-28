package br.com.dasa.api.termo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dasa.api.termo.entity.AceiteTermo;
import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.entity.json.AceiteTermoJson;
import br.com.dasa.api.termo.entity.json.BuscaAceiteTermoJson;
import br.com.dasa.api.termo.exceptions.AceiteExceptions;
import br.com.dasa.api.termo.exceptions.ApiException;
import br.com.dasa.api.termo.exceptions.ValidaExceptions;
import br.com.dasa.api.termo.exceptions.enumsExceptions.AceiteTermoEnums;
import br.com.dasa.api.termo.repository.AceiteRepository;
import br.com.dasa.api.termo.repository.TermOfUserRepository;

@Service
public class AceiteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AceiteService.class);

    @Autowired
    private AceiteRepository aceiteRepository;

    @Autowired
    private TermOfUserRepository termOfUserRepository;


    public void salvarAceite(AceiteTermoJson aceiteTermoJson) {

        Optional<TermOfUser> termOfUser = this.termOfUserRepository.findById(aceiteTermoJson.getIdTermo());
        ValidaExceptions.validaAceiteId(aceiteTermoJson.getIdTermo());
        try {
            if (termOfUser.isPresent()) {
                AceiteTermo termo = new AceiteTermo(aceiteTermoJson.getMdmId(),
                        aceiteTermoJson.isRespostaCliente(), termOfUser.get(), aceiteTermoJson.getCip());
                this.aceiteRepository.save(termo);
            } else {
                LOGGER.info(AceiteTermoEnums.ID_NAO_ENCONTRADO.getMsg());
              throw new AceiteExceptions("Id do termo não encontrado");
            }


        } catch (ApiException e) {
            LOGGER.error(e.getMessage(), e);
           throw  new RuntimeException(e.getMessage().concat(e.getErro()));

        }

    }


	public BuscaAceiteTermoJson buscarAceiteTermo(String mdmId, String cip) {
		
		Optional<TermOfUser> optionalTerm = termOfUserRepository.buscarUltimoTermoObrigatorio(); 
		
		if(optionalTerm.isPresent()) {
			return new BuscaAceiteTermoJson(aceiteRepository.usuarioRespondeuAoTermo(mdmId, cip, optionalTerm.get().getId())); 
		}
		
		return new BuscaAceiteTermoJson(false);
	}


}
