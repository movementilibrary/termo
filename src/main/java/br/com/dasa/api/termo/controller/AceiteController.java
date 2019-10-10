package br.com.dasa.api.termo.controller;

import br.com.dasa.api.termo.entity.AceiteTermoJson;
import br.com.dasa.api.termo.exceptions.ApiException;
import br.com.dasa.api.termo.exceptions.InternalServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.dasa.api.termo.service.AceiteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

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

    public ResponseEntity salvarTermoController(@RequestBody AceiteTermoJson aceiteTermo) throws Exception {

        try {

            this.aceiteService.salvarAceite(aceiteTermo);

            return ResponseEntity.ok(aceiteTermo);

        } catch (ApiException e) {
            return new ResponseEntity("Erro ao salvar termo", HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
