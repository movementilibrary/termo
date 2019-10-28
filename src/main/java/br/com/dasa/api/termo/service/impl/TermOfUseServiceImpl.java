package br.com.dasa.api.termo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import br.com.dasa.api.termo.service.TermOfUseService;

@SuppressWarnings("ALL")
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
        Optional<TermOfUser> byId = this.termOfUserRepository.findById(1l);
        termOfUser.setVersion("v1");
//        if (byId.isEmpty()) {
//            termOfUser.setVersion("v".concat("1"));
//        }
//        if (byId.isPresent()) {
//            ter
//
//        }


        return termOfUserRepository.save(termOfUser);
    }


}