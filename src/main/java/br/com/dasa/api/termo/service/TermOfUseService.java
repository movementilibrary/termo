package br.com.dasa.api.termo.service;

import java.util.Optional;

import br.com.dasa.api.termo.entity.TermOfUser;

public interface TermOfUseService {

    Optional<TermOfUser> findById(long id);

    TermOfUser save(TermOfUser termOfUser);

    void atualizaStatus(TermOfUser termOfUser);

    Integer verificaFlag (TermOfUser termOfUser);

}
