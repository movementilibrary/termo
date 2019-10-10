package br.com.dasa.api.termo.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.service.TermOfUseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Serviços para cadastramento de termo de uso")
@RestController
@RequestMapping("/term")
public class TermOfUserEndPoint {

	private static final Logger LOG = LoggerFactory.getLogger(TermOfUserEndPoint.class);

	@Autowired
	TermOfUseService termOfUseService;
	
	@GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "Responsável por encontrar os termos de uso")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 404, message = "O resource requisitado não foi encontrado"),
			@ApiResponse(code = 500, message = "Um erro interno foi detectado")
	})
	public ResponseEntity<TermOfUser> findById(@PathVariable long id) {
		try {
			Optional<TermOfUser> optional = termOfUseService.findById(id);
			if (optional.isEmpty()) {
				return new ResponseEntity<TermOfUser>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<TermOfUser>(optional.get(), HttpStatus.OK);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<TermOfUser>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/user/registry", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Responsável por salvar e atualizar os termos de uso")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 404, message = "O resource requisitado não foi inserido"),
			@ApiResponse(code = 500, message = "Um erro interno foi detectado")
	})
	public ResponseEntity<TermOfUser> save(@RequestBody TermOfUser termOfUser) {
		try {
			TermOfUser termUser = termOfUseService.save(termOfUser);
			if (StringUtils.isEmpty(termUser)) {
				return new ResponseEntity<TermOfUser>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<TermOfUser>(termUser, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<TermOfUser>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
