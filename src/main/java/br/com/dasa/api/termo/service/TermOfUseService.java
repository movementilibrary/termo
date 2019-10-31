package br.com.dasa.api.termo.service;

import java.util.Optional;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.enumeration.StatusTermUse;

public interface TermOfUseService {

    Optional<TermOfUser> findById(long id);

    TermOfUser checkFlagIsMarked(TermOfUser termOfUser);

    TermOfUser save(TermOfUser termOfUser);



}
