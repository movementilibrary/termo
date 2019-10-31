package br.com.dasa.api.termo.service;

import static org.junit.Assert.*;

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
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = { "test" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TermOfUseServiceTest {

	@Autowired
	private TermOfUserRepository termOfUserRepository; 
	@Autowired
	private TermOfUseService termOfUseService;  
	
	@Value("${local.server.port}")
	protected int porta;
	
	@Before
	public void setUp() throws Exception {
		RestAssured.port = porta;
		termOfUserRepository.deleteAll();
	}
	
	@Test
	public void testBuscaUltimoTermo() {
		criarTermoVersao("V-1", false); 
		criarTermoVersao("V-2", false); 
		criarTermoVersao("V-3", false); 
		criarTermoVersao("V-3.1", true); 
	
		TermOfUser term = termOfUseService.buscarUltimoTermo(); 
		
		assertEquals("V-3.1", term.getVersion());
	}
	
	@Test
	public void testBuscaUltimoTermoBaseVasia() {
		TermOfUser term = termOfUseService.buscarUltimoTermo();
		assertNull(term);
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
