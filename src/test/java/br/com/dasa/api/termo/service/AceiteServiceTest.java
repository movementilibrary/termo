package br.com.dasa.api.termo.service;

import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.enumeration.StatusTermUse;
import br.com.dasa.api.termo.repository.AceiteRepository;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = { "test" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AceiteServiceTest {

	@Value("${local.server.port}")
	protected int porta;
	
	@Autowired
	private AceiteService aceiteService; 
	@Autowired
	private AceiteRepository aceiteRepository; 
	@Autowired
	private TermOfUserRepository termOfUserRepository; 
	
	@Before
	public void setUp() throws Exception {
		RestAssured.port = porta;
		aceiteRepository.deleteAll();
		termOfUserRepository.deleteAll();
	}
	
	
	@Test
	public void buscarTermoUsuarioNaoRespondido() {
		
		TermOfUser term = criarTermoVersao("V-1.0", false);
		
		
	}
	
	private TermOfUser criarTermoVersao(String version, boolean flagAtualizacao) {
		TermOfUser term = new TermOfUser();
		term.setCurrentDate(new Date());
		term.setDescriptionTerm("teste");
		term.setFlagAtualizacao(flagAtualizacao);
		term.setLoginUser("t34945589810");
		term.setStatus(StatusTermUse.ACTIVE);
		term.setSummaryTerm("teste");
		term.setVersion(version);
		
		termOfUserRepository.save(term);
		
		return term; 
	}
	

}
