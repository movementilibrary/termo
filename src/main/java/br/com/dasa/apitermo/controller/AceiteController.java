package br.com.dasa.apitermo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.dasa.apitermo.exceptions.TermoPersistenciaExceptions;
import br.com.dasa.apitermo.model.AceiteTermo;
import br.com.dasa.apitermo.service.AceiteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class AceiteController {

    @Autowired
    private AceiteService aceiteService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AceiteController.class);

    @PostMapping(value = "api/aceite-termo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(httpMethod = "POST", value = "Responsável por fazer o aceite do termo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso"),
            @ApiResponse(code = 404, message = "O resource requisitado não foi encontrado"),
            @ApiResponse(code = 500, message = "Um erro interno foi detectado")
    })

    public boolean salvarTermoController(@RequestBody AceiteTermo aceiteTermo) {
     
        try {
            this.aceiteService.salvarAceite(aceiteTermo);

            return true;
        } catch (Exception e) {
            LOGGER.error(String.valueOf(new TermoPersistenciaExceptions(e.getMessage())));
            return false;
        }


    }
}
