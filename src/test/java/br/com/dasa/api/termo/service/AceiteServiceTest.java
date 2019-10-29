package br.com.dasa.api.termo.service;

import br.com.dasa.api.termo.controller.AceiteController;
import br.com.dasa.api.termo.entity.AceiteTermo;
import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.entity.json.AceiteTermoJson;
import br.com.dasa.api.termo.enumeration.StatusTermUse;
import br.com.dasa.api.termo.exceptions.AceiteExceptions;
import br.com.dasa.api.termo.exceptions.ApiException;
import br.com.dasa.api.termo.exceptions.enums.AceiteTermoEnums;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = {"test"})
public class AceiteServiceTest {

    @Autowired
    private TermOfUserRepository termOfUserRepository;
    @Autowired
    public AceiteService aceiteService;
    @Autowired
    public AceiteController aceiteController;

    private TermOfUser termOfUser;

    private AceiteTermoJson termoJson;


    @Before
    public void setUp() {
        aceiteService = new AceiteService();
        termOfUser = new TermOfUser();
        termoJson = new AceiteTermoJson();
    }


    @Test
    public void naoPodeMdmNulo() throws AceiteExceptions {
        TermOfUser termOfUser = getOfUserEsperado();

        AceiteTermo termo = new AceiteTermo(null, true, termOfUser, 9999);

        AceiteTermoJson aceiteTermoJson = new AceiteTermoJson(1L, termo.getMdmIdCliente(), termo.getCip(),
                termo.getRespostaCliente());

        Assert.assertNull(null, aceiteTermoJson.getMdmId());


    }

    @Test
    public void validaCipApenasNumero() throws AceiteExceptions {
        TermOfUser termOfUser = new TermOfUser();
        termOfUser.setId(1L);

        AceiteTermo termo = new AceiteTermo("HYUIII", true, termOfUser, 9999);

        AceiteTermoJson aceiteTermoJson = new AceiteTermoJson(1L, termo.getMdmIdCliente(), termo.getCip(),
                termo.getRespostaCliente());

        Assert.assertEquals(9999, aceiteTermoJson.getCip(), 0.001);
    }

    @Test
    public void validaCipSemCaracteres() throws AceiteExceptions {
        TermOfUser termOfUser = new TermOfUser();
        termOfUser.setId(1L);

        AceiteTermo termo = new AceiteTermo("HYUIII", true, termOfUser, 9999);

        AceiteTermoJson aceiteTermoJson = new AceiteTermoJson(1L, termo.getMdmIdCliente(), termo.getCip(),
                termo.getRespostaCliente());
        boolean cip;
        try {
            cip = termo.getCip().toString().matches("[a-z]");
            Assert.assertFalse(cip);
        } catch (RuntimeException e) {
            throw new AceiteExceptions(AceiteTermoEnums.CIP_INVALIDA);
        }


    }

    @Test
    public void verificaRespostaCLienteTrue() {
        TermOfUser termOfUser = new TermOfUser();
        termOfUser.setId(1L);

        AceiteTermo termo = new AceiteTermo("HYUIII", true, termOfUser, 9999);

        AceiteTermoJson aceiteTermoJson = new AceiteTermoJson(1L, termo.getMdmIdCliente(), termo.getCip(),
                termo.getRespostaCliente());

        Assert.assertTrue(termo.getRespostaCliente());
    }

    @Test
    public void verificaRespostaCLienteFalse() {

        termOfUser.setId(1L);

        AceiteTermo termo = new AceiteTermo();
        termo.setRespostaCliente(false);

        AceiteTermoJson aceiteTermoJson = new AceiteTermoJson();
        aceiteTermoJson.setRespostaCliente(termo.getRespostaCliente());


        Assert.assertFalse(termo.getRespostaCliente());
    }



    private AceiteTermoJson getAceiteTermo() {

        termoJson.setRespostaCliente(getEsperado().getRespostaCliente());
        termoJson.setIdTermo(getEsperado().getTermOfUser().getId());
        termoJson.setCip(getEsperado().getCip());
        termoJson.setMdmId(getEsperado().getMdmIdCliente());
        return termoJson;
    }

    private AceiteTermo getEsperado() {

        termOfUser.setId(1L);
        AceiteTermo esperado = new AceiteTermo();
        esperado.setRespostaCliente(true);
        esperado.setMdmIdCliente("99999");
        esperado.setTermOfUser(termOfUser);
        esperado.setDataAceite(new Date());
        esperado.setCip(921222);
        return esperado;
    }

    private TermOfUser getOfUserEsperado() {
        termOfUser.setId(1L);
        termOfUser.setLoginUser("GLIESE");
        termOfUser.setStatus(StatusTermUse.ACTIVE);
        termOfUser.setSummaryTerm("TEST-ESPERADO");
        termOfUser.setDescriptionTerm("GLIESE TERMO");
        termOfUser.setVersion("v1");

        termOfUserRepository.save(termOfUser);
        return termOfUser;
    }

    @Test
    public void testIdTermo() throws ApiException {

        TermOfUser termOfUser = new TermOfUser();
        termOfUser.setId(1L);

        Optional<TermOfUser> termOfUserOptional = termOfUserRepository.findById(termOfUser.getId());
        if (!termOfUserOptional.isEmpty()) {
            Assert.assertNotNull(termOfUserOptional.isPresent());

        }





    }

}