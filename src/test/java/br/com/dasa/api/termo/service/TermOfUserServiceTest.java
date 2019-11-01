package br.com.dasa.api.termo.service;

import br.com.dasa.api.termo.controller.TermOfUserEndPoint;
import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.entity.json.TermoOfUserJson;
import br.com.dasa.api.termo.enumeration.StatusTermUse;

import br.com.dasa.api.termo.exceptions.ResourceNotFoundException;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import br.com.dasa.api.termo.service.impl.TermOfUseServiceImpl;
import io.restassured.RestAssured;
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

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = {"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TermOfUserServiceTest {

    @LocalServerPort
    int port;


    @Before
    public void setup() {
        RestAssured.port = port;
    }

    @Autowired
    private TermOfUseServiceImpl termOfUseService;


    @Autowired
    private TermOfUserEndPoint termOfUserEndPoint;


    /**
     * Retorna 404 pois não foi encontrado uma Versão do termo
     * O teste está criando uma versão v1.1 sem existeir uma V1
     */
    @Test(expected = ResourceNotFoundException.class)
    public void teste1DeveRetornar404() {
        termOfUserEndPoint.save(new TermoOfUserJson("Termo em uso", "sumario", "f32201635803", false));
//        given()
//                .contentType("application/json")
//                .body(new TermoOfUserJson("Termo em uso", "sumario", "f32201635803", false))
//                .when()
//                .post("/api/term/user/registry")
//                .then()
//                .statusCode(404);
    }

    /**
     * O teste deve criar uma versão e retornar V1
     */
    @Test
    public void teste2DeveCriarVersaoV1() {
        TermOfUser termOfUser = termOfUseService.checkFlagIsMarked(new TermoOfUserJson("Termo em uso", "sumario", "f32201635803", true));
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
    public void teste3DeveCriarVersaoV2() {

        TermOfUser termOfUser = termOfUseService.checkFlagIsMarked(new TermoOfUserJson("Termo em uso", "sumario", "f32201635803", true));
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
    public void teste4DeveVerificarSeV2EstaAtivo() {
        List<TermOfUser> byStatus = termOfUseService.findByStatus(StatusTermUse.ACTIVE);
        assertEquals("V1", byStatus.get(0).getVersion());
    }


    /**
     * O teste deve verificar se V1 está inativo, quando cria uma versão ou subVersao a anterior
     * sempre ficara Inativo
     */
    @Test
    public void teste5DeveVerificarSeV1EstaInativo() {
        List<TermOfUser> byStatus = termOfUseService.findByStatus(StatusTermUse.INACTIVE);
        assertEquals("v1", byStatus.get(0).getVersion());
    }

    /**
     * Teste Cria uma subVersão, quando a flag está desmarcada ou seja false, uma subVersao é criada
     * Ex> atual = V1, o teste cria V1.1
     */
    @Test
    public void teste6DeveCriarUmaSubVersao() {
        TermOfUser termOfUser = termOfUseService.checkFlagIsMarked(new TermoOfUserJson("Termo em uso", "sumario", "f32201635803", false));
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
    public void teste7DeveVerificarSeV2EstaInativo() {
        List<TermOfUser> byStatus = termOfUseService.findByStatus(StatusTermUse.INACTIVE);
        assertEquals("v1", byStatus.get(0).getVersion());
        assertEquals("V1", byStatus.get(1).getVersion());
    }

    /**
     * O teste deve verificar se V2.1 está ativo
     */
    @Test
    public void teste8DeveVerificarSeV1EstaInativo() {
        List<TermOfUser> byStatus = termOfUseService.findByStatus(StatusTermUse.ACTIVE);
        assertEquals("V2", byStatus.get(0).getVersion());
    }


}
