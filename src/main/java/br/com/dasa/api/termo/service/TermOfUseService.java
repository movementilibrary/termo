package br.com.dasa.api.termo.service;

import br.com.dasa.api.termo.entity.TermOfUser;

import java.util.Optional;

public interface TermOfUseService {



    Optional<TermOfUser> findById(long id);


    TermOfUser save(TermOfUser termOfUser);


}
