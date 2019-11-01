package br.com.dasa.api.termo.service;

import br.com.dasa.api.termo.entity.AceiteTermo;
import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.entity.json.AceiteTermoJson;
import br.com.dasa.api.termo.entity.json.BuscaAceiteTermoJson;
import br.com.dasa.api.termo.exceptions.AceiteExceptions;
import br.com.dasa.api.termo.exceptions.ValidaExceptions;
import br.com.dasa.api.termo.exceptions.enums.AceiteTermoEnums;
import br.com.dasa.api.termo.repository.AceiteRepository;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AceiteService {


    @Autowired
    private AceiteRepository aceiteRepository;

    @Autowired
    private TermOfUserRepository termOfUserRepository;

    public Optional buscaIdTermo(Long id) {

        Optional<TermOfUser> term = this.termOfUserRepository.findById(id);
        if (term.isPresent()) {
            return term;

        }
        throw new AceiteExceptions(AceiteTermoEnums.ID_NAO_ENCONTRADO);


    }

    public boolean salvarAceite(AceiteTermoJson aceiteTermoJson) {
        Optional<TermOfUser> termOfUser = buscaIdTermo(aceiteTermoJson.getIdTermo());
        ValidaExceptions.validaAceiteId(aceiteTermoJson.getIdTermo());
        ValidaExceptions.validaTermo(aceiteTermoJson);
        try {

            AceiteTermo termo = new AceiteTermo(aceiteTermoJson.getMdmId(),
                    aceiteTermoJson.isRespostaCliente(), termOfUser.get(), aceiteTermoJson.getCip());
            this.aceiteRepository.save(termo);
            return true;

        } catch (Exception e) {
            throw new AceiteExceptions(e.getMessage());

        }


    }


    public BuscaAceiteTermoJson buscarAceiteTermo(String mdmId, Integer cip) {

        Optional<TermOfUser> optionalTerm = termOfUserRepository.buscarUltimoTermoObrigatorio();

        ArrayList<Long> ids = new ArrayList<>();

        if (optionalTerm.isPresent()) {
            ids.add(optionalTerm.get().getId());
            List<TermOfUser> lista = termOfUserRepository.buscarTermosUltimosTermosNaoObrigatorios(optionalTerm.get().getId());
            ids.addAll(lista.stream().map(t -> t.getId()).collect(Collectors.toList()));

            return new BuscaAceiteTermoJson(aceiteRepository.usuarioRespondeuAoTermo(mdmId, cip, ids));
        }

        return new BuscaAceiteTermoJson(false);
    }


}
