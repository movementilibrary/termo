package br.com.dasa.api.termo.service;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.entity.json.TermoOfUserJson;
import br.com.dasa.api.termo.enumeration.StatusTermUse;

import java.util.List;
import java.util.Optional;

public interface TermOfUseService {

    Optional<TermOfUser> findById(long id);

    TermOfUser checkFlagIsMarked(TermoOfUserJson termOfUserJson);

    TermOfUser save(TermOfUser termOfUser);

    List<TermOfUser> findByStatus(StatusTermUse status);

}
