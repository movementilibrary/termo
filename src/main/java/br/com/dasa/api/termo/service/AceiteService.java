package br.com.dasa.api.termo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dasa.api.termo.entity.AceiteTermo;
import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.entity.json.AceiteTermoJson;
import br.com.dasa.api.termo.entity.json.BuscaAceiteTermoJson;
import br.com.dasa.api.termo.exceptions.AceiteExceptions;
import br.com.dasa.api.termo.exceptions.ValidaExceptions;
import br.com.dasa.api.termo.exceptions.enums.AceiteTermoEnums;
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
        ValidaExceptions.validaTermo(aceiteTermoJson);

        try {
            if (termOfUser.isPresent()) {
                AceiteTermo termo = new AceiteTermo(aceiteTermoJson.getMdmId(),
                        aceiteTermoJson.isRespostaCliente(), termOfUser.get(), aceiteTermoJson.getCip());
                this.aceiteRepository.save(termo);
            } else {
                LOGGER.info(AceiteTermoEnums.ID_NAO_ENCONTRADO.getMsg());
                throw new AceiteExceptions(AceiteTermoEnums.ID_NAO_ENCONTRADO);
            }


        } catch (Exception e) {
            throw new AceiteExceptions(e.getMessage());

        }

    }


	public BuscaAceiteTermoJson buscarAceiteTermo(String mdmId, Integer cip) {
		
		Optional<TermOfUser> optionalTerm = termOfUserRepository.buscarUltimoTermoObrigatorio(); 
		
		ArrayList<Long> ids = new ArrayList<>(); 
		
		if(optionalTerm.isPresent()) {
			ids.add(optionalTerm.get().getId()); 
			List<TermOfUser> lista = termOfUserRepository.buscarTermosUltimosTermosNaoObrigatorios(optionalTerm.get().getId());
			ids.addAll(lista.stream().map(t -> t.getId()).collect(Collectors.toList())); 
			
			return new BuscaAceiteTermoJson(aceiteRepository.usuarioRespondeuAoTermo(mdmId, cip, ids)); 
		}
		
		return new BuscaAceiteTermoJson(false);
	}


}
