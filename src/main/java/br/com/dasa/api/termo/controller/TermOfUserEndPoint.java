package br.com.dasa.api.termo.controller;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.entity.json.TermoOfUserJson;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(value = "Serviços para cadastramento de termo de uso")
@RestController
@RequestMapping("/api/term")
public class TermOfUserEndPoint {

    private static final Logger LOG = LoggerFactory.getLogger(TermOfUserEndPoint.class);

    @Autowired
    private TermOfUseService termOfUseService;

    @Autowired
    private TermOfUserRepository termOfUserRepository;


    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(httpMethod = "GET", value = "Responsável por encontrar os termos de uso")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Um erro interno foi detectado")

    })
    public ResponseEntity<TermOfUser> findById(@PathVariable long id) {
        LOG.info("Entrado no metodo findById");
        try {
            Optional<TermOfUser> optional = this.termOfUseService.findById(id);
            if (optional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<TermoOfUserJson> save(@RequestBody TermoOfUserJson termoOfUserJson) {
        LOG.info("Entrado no metodo save");

        try {

            TermOfUser termOfUser = new TermOfUser(termoOfUserJson.getLoginUser(), termoOfUserJson.getDescriptionTerm(),
                    termoOfUserJson.getSummaryTerm(), termoOfUserJson.getStatus(), termoOfUserJson.getFlagAtualizacao());

            this.termOfUseService.save(termOfUser);

            if (StringUtils.isEmpty(termoOfUserJson)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            LOG.info("Saindo do metodo save");
            return new ResponseEntity<>(termoOfUserJson, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
