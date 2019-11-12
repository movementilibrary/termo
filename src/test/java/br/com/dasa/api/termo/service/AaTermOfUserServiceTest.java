package br.com.dasa.api.termo.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.dasa.api.termo.controller.TermOfUserEndPoint;
import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.entity.json.TermoOfUserJson;
import br.com.dasa.api.termo.enumeration.StatusTermUse;
import br.com.dasa.api.termo.repository.AceiteRepository;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = {"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AaTermOfUserServiceTest {

    @LocalServerPort
    int port;

    @Autowired
    private AceiteRepository aceiteRepository;

    @Autowired
    private TermOfUserRepository termOfUserRepository;

    @Autowired
    private TermOfUseService termOfUseService;


    @Autowired
    private TermOfUserEndPoint termOfUserEndPoint;


    @Before
    public void setup() {
        RestAssured.port = port;
    }



    /**
     * Retorna 404 pois não foi encontrado uma Versão do termo
     * O teste está criando uma versão v1.1 sem existeir uma V1
     */
//    @Test(expected = ResourceNotFoundException.class)
//    public void teste1DeveRetornar404() {
//        termOfUserEndPoint.save(new TermoOfUserJson("Termo em uso", "sumario", "f32201635803", false));
//        given()
//                .contentType("application/json")
//                .body(new TermoOfUserJson("Termo em uso", "sumario", "f32201635803", false))
//                .when()
//                .post("/api/term/user/registry")
//                .then()
//                .statusCode(404);
//    }

    /**
     * O teste deve criar uma versão e retornar V1
     */
    @Test
    public void teste1DeveCriarVersaoV1() {
        TermOfUser termOfUser = termOfUseService.checkFlagIsMarked(new TermoOfUserJson("Termo 1", "sumario", "f32201635803", false));
        assertEquals("V1", termOfUser.getVersion());
        //        given()
//                .contentType("application/json")
//                .body(new TermoOfUserJson("Termo em uso", "sumario", "f32201635803", true))
//                .when()
//                .post("/api/term/user/registry")
//                .then()
//                .statusCode(201)
//                .body("version", equalTo("V1"));
    }

    /**
     * O teste deve criar uma nova versão e retornar V2
     */
    @Test
    public void teste2DeveCriarVersaoV2() {

        TermOfUser termOfUser = termOfUseService.checkFlagIsMarked(new TermoOfUserJson("Termo 2", "sumario 2", "f32201635802", false));
        assertEquals("V2", termOfUser.getVersion());

//        given()
//                .contentType("application/json")
//                .body(new TermoOfUserJson("Termo em uso", "sumario", "f32201635803", true))
//                .when()
//                .post("/api/term/user/registry")
//                .then()
//                .statusCode(201)
//                .body("version", equalTo("V2"));
    }

    /**
     * O teste deve verificar se a V2 está ativa
     */
    @Test
    public void teste3DeveVerificarSeV2EstaAtivo() {
        List<TermOfUser> byStatus = termOfUseService.findByStatus(StatusTermUse.ACTIVE);
        assertEquals("V2", byStatus.get(0).getVersion());
    }


    /**
     * O teste deve verificar se V1 está inativo, quando cria uma versão ou subVersao a anterior
     * sempre ficara Inativo
     */
    @Test
    public void teste4DeveVerificarSeV1EstaInativo() {
        List<TermOfUser> byStatus = termOfUseService.findByStatus(StatusTermUse.INACTIVE);
        assertEquals("V1", byStatus.get(0).getVersion());
    }

    /**
     * Teste Cria uma subVersão, quando a flag está desmarcada ou seja false, uma subVersao é criada
     * Ex> atual = V1, o teste cria V1.1
     */
    @Test
    public void teste5DeveCriarUmaSubVersao() {
        TermOfUser termOfUser = termOfUseService.checkFlagIsMarked(new TermoOfUserJson("Termo em uso", "sumario", "f32201635803", true));
        assertEquals("V2.1", termOfUser.getVersion());
//        given()
//                .contentType("application/json")
//                .body(new TermoOfUserJson("Termo em uso", "sumario", "f32201635803", false))
//                .when()
//                .post("/api/term/user/registry")
//                .then()
//                .statusCode(201)
//                .body("version", equalTo("V2.1"));
    }

    /**
     * O teste deve verificar se V2 está inativo, quando cria uma versão ou subVersao a anterior
     * sempre ficara Inativo
     */
    @Test
    public void teste6DeveVerificarSeV2EstaInativo() {
        List<TermOfUser> byStatus = termOfUseService.findByStatus(StatusTermUse.INACTIVE);
        assertEquals("V1", byStatus.get(0).getVersion());
        assertEquals("V2", byStatus.get(1).getVersion());
    }

    /**
     * O teste deve verificar se V2.1 está ativo
     */
    @Test
    public void teste7DeveVerificarSeV1EstaInativo() {
        List<TermOfUser> byStatus = termOfUseService.findByStatus(StatusTermUse.ACTIVE);
        assertEquals("V2.1", byStatus.get(0).getVersion());
    }



}
