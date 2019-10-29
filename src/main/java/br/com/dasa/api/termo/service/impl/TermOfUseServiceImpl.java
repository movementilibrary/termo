package br.com.dasa.api.termo.service.impl;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.exceptions.ValidaExceptions;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import br.com.dasa.api.termo.service.TermOfUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TermOfUseServiceImpl implements TermOfUseService {

    @Autowired
    private TermOfUserRepository termOfUserRepository;

    @Override
    public Optional<TermOfUser> findById(long id) {
        return termOfUserRepository.findById(id);
    }


    @Override
    public TermOfUser save(TermOfUser termOfUser) {
        termOfUser.setVersion("v1");
        ValidaExceptions.validaTermoOfUser(termOfUser);
        return termOfUserRepository.save(termOfUser);
    }


}