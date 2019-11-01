package br.com.dasa.api.termo.controller;


import br.com.dasa.api.termo.entity.json.AceiteTermoJson;
import br.com.dasa.api.termo.entity.json.BuscaAceiteTermoJson;
import br.com.dasa.api.termo.exceptions.ApiException;
import br.com.dasa.api.termo.service.AceiteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/term")
@RestController
public class AceiteController {

    @Autowired
    private AceiteService aceiteService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AceiteController.class);

    @PostMapping(value = "/aceite-termo", produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
    @ApiOperation(httpMethod = "POST", value = "Responsável por fazer o aceite do termo")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Sucesso"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Um erro interno foi detectado")})

    public ResponseEntity<AceiteTermoJson> salvarTermoController(@RequestBody AceiteTermoJson aceiteTermo) {
        LOGGER.info("Entrando no metodo de aceite de termo");
        aceiteService.buscaIdTermo(aceiteTermo.getIdTermo());
        try {
            aceiteService.salvarAceite(aceiteTermo);
            return new ResponseEntity(aceiteTermo, HttpStatus.OK);

        } catch (ApiException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/aceite-termo/busca", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Responsável por buscar aceite de termo passando o MDM ID ou a CIP", response = BuscaAceiteTermoJson.class)
    public ResponseEntity buscarAceiteTermo(@RequestParam(required = false) String mdmId, @RequestParam(required = false) Integer cip) {

        if ((!StringUtils.isEmpty(mdmId)) || (cip != null && cip > 0)) {
            BuscaAceiteTermoJson json = aceiteService.buscarAceiteTermo(mdmId, cip);
            return new ResponseEntity(json, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

}
