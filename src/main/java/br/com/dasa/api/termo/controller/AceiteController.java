package br.com.dasa.api.termo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dasa.api.termo.entity.json.AceiteTermoJson;
import br.com.dasa.api.termo.exceptions.ApiException;
import br.com.dasa.api.termo.service.AceiteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/api/term")
@RestController
public class AceiteController {

	@Autowired
	private AceiteService aceiteService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AceiteController.class);

	@PostMapping(value = "/aceite-termo", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "Responsável por fazer o aceite do termo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Um erro interno foi detectado") })
	public ResponseEntity<AceiteTermoJson> salvarTermoController(@RequestBody AceiteTermoJson aceiteTermo) {
		try {
			this.aceiteService.salvarAceite(aceiteTermo);
			return new ResponseEntity<AceiteTermoJson>(aceiteTermo, HttpStatus.OK);
		} catch (ApiException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<AceiteTermoJson>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
