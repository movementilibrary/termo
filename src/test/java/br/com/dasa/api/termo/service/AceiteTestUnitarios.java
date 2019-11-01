package br.com.dasa.api.termo.service;

import br.com.dasa.api.termo.controller.AceiteController;
import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.entity.json.AceiteTermoJson;
import br.com.dasa.api.termo.entity.json.TermoOfUserJson;
import br.com.dasa.api.termo.enumeration.StatusTermUse;
import br.com.dasa.api.termo.exceptions.AceiteExceptions;
import br.com.dasa.api.termo.exceptions.ApiException;
import br.com.dasa.api.termo.repository.AceiteRepository;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import br.com.dasa.api.termo.service.impl.TermOfUseServiceImpl;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = {"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AceiteTestUnitarios {

    @Value("${local.server.port}")
    protected int porta;

    @Autowired
    private TermOfUserRepository termOfUserRepository;
    @Autowired
    public AceiteService aceiteService;
    @Autowired
    private AceiteController aceiteController;

    @Autowired
    private TermOfUseServiceImpl termOfUseService;


    private TermOfUser term;

    private TermoOfUserJson termoOfUserJson;

    private AceiteTermoJson aceiteTermoJson;


    @Before
    public void setUp() {
        RestAssured.port = porta;
        term = new TermOfUser();
        aceiteTermoJson = new AceiteTermoJson();
        termoOfUserJson = new TermoOfUserJson();
    }

    @Test
    public void testSalvaAceite() {
        Integer cip = 9999;
        String mdmId = "GLIESE-DEV-01";

        term = criarTermoVersao(true, StatusTermUse.ACTIVE);
        AceiteTermoJson termoJson = criaAceiteTermo(term.getId(), cip, mdmId, true);

        boolean ok = aceiteService.salvarAceite(termoJson);

        Assert.assertTrue(ok);


    }

    @Test
    public void testaAceiteComStatusInativo() throws AceiteExceptions {
        Integer cip = 9999;
        String mdmId = "GLIESE-DEV-01";

        term = criarTermoVersao(true, StatusTermUse.INACTIVE);
        AceiteTermoJson termoJson = criaAceiteTermo(term.getId(), cip, mdmId, true);

        Assert.assertEquals(StatusTermUse.INACTIVE, term.getStatus());

    }

    private AceiteTermoJson criaAceiteTermo(Long id, Integer cip, String mdmId, boolean resposta) {

        AceiteTermoJson termoJson = new AceiteTermoJson();
        termoJson.setIdTermo(id);
        termoJson.setMdmId(mdmId);
        termoJson.setCip(cip);
        termoJson.setRespostaCliente(resposta);


        return termoJson;
    }

    private TermoOfUserJson TermoJson(boolean flag, String user) {
        TermoOfUserJson termoOfUserJson = new TermoOfUserJson();
        termoOfUserJson.setDescriptionTerm("GLIESE TERMO");
        termoOfUserJson.setFlagAtualizacao(flag);
        termoOfUserJson.setLoginUser(user);
        termoOfUserJson.setSummaryTerm("TEST DE INTEGRAÇÃO");

        this.termOfUseService.save(termoOfUserJson);
        return termoOfUserJson;
    }


    private TermOfUser criarTermoVersao(boolean flagAtualizacao, StatusTermUse status) {

        termoOfUserJson.setSummaryTerm("test");
        termoOfUserJson.setLoginUser("gliese");
        termoOfUserJson.setFlagAtualizacao(flagAtualizacao);
        termoOfUserJson.setDescriptionTerm("termo");

        term.setDescriptionTerm(termoOfUserJson.getDescriptionTerm());
        term.setSummaryTerm(termoOfUserJson.getSummaryTerm());
        term.setLoginUser(termoOfUserJson.getLoginUser());
        term.setFlagAtualizacao(flagAtualizacao);
        term.setStatus(status);
        term.setVersion("v1");

        termOfUserRepository.save(term);

        return term;
    }


}