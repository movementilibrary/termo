package br.com.dasa.api.termo.service;

import br.com.dasa.api.termo.entity.AceiteTermo;
import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.entity.json.AceiteTermoJson;
import br.com.dasa.api.termo.entity.json.TermoOfUserJson;
import br.com.dasa.api.termo.enumeration.StatusTermUse;
import br.com.dasa.api.termo.exceptions.AceiteException;
import br.com.dasa.api.termo.repository.AceiteRepository;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import io.restassured.RestAssured;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = {"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AceiteServiceTestIntegracao {


    @Autowired
    private TermOfUserRepository termOfUserRepository;

    @Autowired
    private AceiteRepository aceiteRepository;

    @Autowired
    private AceiteService aceiteService;

    private TermOfUser term;

    private TermoOfUserJson termoOfUserJson;

    private AceiteTermo aceiteTermo;

    private AceiteTermoJson aceiteTermoJson;
    @Value("${local.server.port}")
    protected int porta;


    @Before
    public void setUp() {
        RestAssured.port = porta;
        term = new TermOfUser();
        aceiteTermo = new AceiteTermo();
        term = criarTermoVersao(true, StatusTermUse.ACTIVE);
        termOfUserRepository.save(term);
        aceiteRepository.deleteAll();
        termOfUserRepository.deleteAll();
    }

    @After
    public void delete() {
        aceiteRepository.deleteAll();
        termOfUserRepository.deleteAll();
    }

//
//    @Test
//    public void salvarAceiteTermo() {
//        TermOfUser term = criarTermoVersao(true, StatusTermUse.ACTIVE);
//        aceiteTermo = getEsperado();
//        aceiteRepository.save(aceiteTermo);
//        Optional<AceiteTermo> byId = aceiteRepository.findById(1l);
//        System.out.println(aceiteTermo.getId());
//        if (byId.isPresent()) {
//            Assert.assertTrue(byId.isPresent());
//        } else {
//            Assert.fail("Codigo não encontrado");
//        }
//
//
//    }

    @Test(expected = AceiteException.class)
    public void naoDeixaSalvarComStatusInativoAceiteTermo() {
        TermOfUser term = criarTermoVersao(true, StatusTermUse.INACTIVE);

        AceiteTermoJson json = salvarAceiteTermo("15", true, term.getId(), 9999l);


        aceiteService.salvarAceite(json);


    }

    @Test(expected = AceiteException.class)
    public void naoPodeSalvarComMdmIdNullo() {
        TermOfUser term = criarTermoVersao(true, StatusTermUse.ACTIVE);

        AceiteTermoJson json = salvarAceiteTermo(null, true, term.getId(), 9999l);
        Assert.assertNull(json.getMdmId());

        aceiteService.salvarAceite(json);
    }


    private AceiteTermoJson salvarAceiteTermo(String mdmId, boolean resposta, Long id, Long cip) {
        AceiteTermoJson json = new AceiteTermoJson();
        json.setIdTermo(id);
        json.setRespostaCliente(resposta);
        json.setCip(cip);
        json.setMdmId(mdmId);
        return json;
    }

    private TermOfUser criarTermoVersao(boolean flagAtualizacao, StatusTermUse statusTermUse) {
        term.setCurrentDate(new Date());
        term.setDescriptionTerm("teste");
        term.setFlagAtualizacao(flagAtualizacao);
        term.setLoginUser("t34945589810");
        term.setStatus(statusTermUse);
        term.setSummaryTerm("teste");
        term.setVersion("v-1");
        termOfUserRepository.save(term);

        return term;
    }

    private AceiteTermo getEsperado() {
        aceiteTermo.setCip(9999l);
        aceiteTermo.setDataAceite(new Date());
        aceiteTermo.setTermOfUser(term);
        aceiteTermo.setMdmIdCliente("8585");
        aceiteTermo.setRespostaCliente(true);
        return aceiteTermo;
    }




}