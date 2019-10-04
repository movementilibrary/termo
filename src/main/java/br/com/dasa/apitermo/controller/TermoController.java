package br.com.dasa.apitermo.controller;

import br.com.dasa.apitermo.exceptions.ApiExceptions;
import br.com.dasa.apitermo.model.Termo;
import br.com.dasa.apitermo.service.TermoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TermoController {

    @Autowired
    private TermoService termoService;


    private static final Logger LOGGER = LoggerFactory.getLogger(TermoController.class);
//
//    @PostMapping(value = "/termo", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(httpMethod = "POST", value = "Responsável por salvar o termo")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Sucesso"),
//            @ApiResponse(code = 404, message = "O resource requisitado não foi encontrado"),
//            @ApiResponse(code = 500, message = "Um erro interno foi detectado")
//    })


//    public List<Termo> save(@PathVariable String descricaoTermo, @PathVariable String versao) {
//        List<Termo> termoList = null;
//        try {
//            this.termoService.salvarTermo(descricaoTermo, versao);
//
//            return termoList;
//        } catch (ApiExceptions e) {
//            LOGGER.error(e.getMessage());
//        }
//        return termoList;
//    }
//
}
