package br.com.dasa.api.termo.service.impl;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.entity.json.TermoOfUserJson;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import br.com.dasa.api.termo.service.TermOfUseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TermOfUseServiceImpl implements TermOfUseService {
    private static final Logger LOG = LoggerFactory.getLogger(TermOfUseServiceImpl.class);
    @Autowired
    private TermOfUserRepository termOfUserRepository;

    @Override
    public Optional<TermOfUser> findById(long id) {
        return termOfUserRepository.findById(id);
    }


    @Override
    public TermOfUser save(TermoOfUserJson termoOfUserJson) {

        try {
            TermOfUser termOfUser = new TermOfUser(termoOfUserJson.getLoginUser(), termoOfUserJson.getDescriptionTerm(),
                    termoOfUserJson.getSummaryTerm(), termoOfUserJson.getFlagAtualizacao());
            termOfUser.setVersion("v1");
            termOfUserRepository.save(termOfUser);
            return termOfUser;
        } catch (Exception e) {
            LOG.error(e.getMessage());

        }
        return null;

    }


}