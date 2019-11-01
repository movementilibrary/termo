package br.com.dasa.api.termo.service;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.entity.json.TermoOfUserJson;

import java.util.Optional;

public interface TermOfUseService {



    Optional<TermOfUser> findById(long id);


    TermOfUser save(TermoOfUserJson termoOfUserJson);



}
