package br.com.dasa.api.termo.controller;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.entity.json.TermoOfUserJson;
import br.com.dasa.api.termo.service.TermOfUseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(value = "Serviços para cadastramento de termo de uso")
@RestController
@RequestMapping("/api/term")
public class TermOfUserEndPoint {

    private static final Logger LOG = LoggerFactory.getLogger(TermOfUserEndPoint.class);

    @Autowired
    private TermOfUseService termOfUseService;


    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(httpMethod = "GET", value = "Responsável por encontrar os termos de uso")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Um erro interno foi detectado")

    })
    public ResponseEntity findById(@PathVariable long id) {
        LOG.info("Entrado no metodo findById");
        try {
            Optional<TermOfUser> optional = this.termOfUseService.findById(id);
            if (optional.isEmpty()) {
                LOG.info("Termo com o ID " + id + " nao encontrado na base de dados ");
                return new ResponseEntity("Termo com o ID " + id + " nao encontrado na base de dados ",
                        HttpStatus.BAD_REQUEST);

            }
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/user/registry", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(httpMethod = "POST", value = "Responsável por salvar e atualizar os termos de uso")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Um erro interno foi detectado")
    })
    public ResponseEntity save(@RequestBody TermoOfUserJson termoOfUserJson) {
        LOG.info("Entrado no metodo save");
        try {
            TermOfUser termoOfUser = termOfUseService.checkFlagIsMarked(termoOfUserJson);
            LOG.info("Termo salvo com sucesso");
            return new ResponseEntity<>(termoOfUser, HttpStatus.CREATED);
        } catch (Exception e) {
            LOG.info(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


}
