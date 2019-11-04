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
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = {"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AceiteTestUnitarios {


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
        term = criarTermoVersao(true, StatusTermUse.ACTIVE);
        termOfUserRepository.save(term);
    }

    @After
    public void before() {
        termOfUserRepository.deleteAll();
        aceiteRepository.deleteAll();
    }

//    @Test
//    public void validaExistsById() {
//        Optional<TermOfUser> termOfUser = termOfUserRepository.findById(term.getId());
//        System.out.println(termOfUser.get().getId());
//        if (termOfUser.isPresent()) {
//            Assert.assertTrue(termOfUser.isPresent());
//        } else {
//            Assert.fail();
//        }
//
//    }

    @Test
    public void findFirstByNome() {
        Optional<TermOfUser> termOfUser = this.termOfUserRepository.findFirstByLoginUser("t34945589810");
        if (termOfUser.isPresent()) {
            Assert.assertTrue(termOfUser.isPresent());
        }
       else Assert.fail("USUARIO NÃO ENCONTRADO");


    }

    @Test(expected = AceiteException.class)
    public void naoDeixaSalvarComStatusInativoAceiteTermo() {
        TermOfUser term = criarTermoVersao(true, StatusTermUse.INACTIVE);

        AceiteTermoJson json = salvarAceiteTermo("15", true, term.getId(), 9999);


        aceiteService.salvarAceite(json);


    }

    @Test(expected = AceiteException.class)
    public void naoPodeSalvarComMdmIdNullo() {
        TermOfUser term = criarTermoVersao(true, StatusTermUse.ACTIVE);

        AceiteTermoJson json = salvarAceiteTermo(null, true, term.getId(), 9999);
        Assert.assertNull(json.getMdmId());

        aceiteService.salvarAceite(json);
    }


    private AceiteTermoJson salvarAceiteTermo(String mdmId, boolean resposta, Long id, Integer cip) {
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


}