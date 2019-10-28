package br.com.dasa.api.termo.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import br.com.dasa.api.termo.entity.json.AceiteTermoJson;
import br.com.dasa.api.termo.entity.json.BuscaAceiteTermoJson;
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
	public void testBuscarTermoUsuarioRespondidoSim() {
		
		String cip = "34445"; 
		String mdmId = "12345"; 
		
		TermOfUser term = criarTermoVersao("V-1.0", false);
		criarAceiteTermo(term.getId(), cip, mdmId, true);
		
		BuscaAceiteTermoJson json = aceiteService.buscarAceiteTermo(mdmId, cip); 
		
		assertTrue(json.isTermoAceite());
		
		
	}
	
	@Test
	public void testApenasTermoV1RespondidoV2NaoRespondido() {
		
		String cip = "34445"; 
		String mdmId = "12345"; 
		
		TermOfUser term = criarTermoVersao("V-1.0", false);
		criarAceiteTermo(term.getId(), cip, mdmId, true);
		criarTermoVersao("V-2.0", false);
		
		BuscaAceiteTermoJson json = aceiteService.buscarAceiteTermo(mdmId, cip); 
		
		assertFalse(json.isTermoAceite());
		
		
	}
	
	@Test
	public void testApenasRespondidoV2NaoRespondido() {
		
		String cip = "34445"; 
		String mdmId = "12345"; 
		
		criarTermoVersao("V-1.0", false);
		TermOfUser term = criarTermoVersao("V-2.0", false);
		criarAceiteTermo(term.getId(), cip, mdmId, true);
		
		BuscaAceiteTermoJson json = aceiteService.buscarAceiteTermo(mdmId, cip); 
		
		assertTrue(json.isTermoAceite());
		
		
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
	
	private void criarAceiteTermo(Long idTermo, String cip, String mdmId, boolean respostaCliente) {
		
		AceiteTermoJson json = new AceiteTermoJson(); 
		json.setCip(cip);
		json.setIdTermo(idTermo);
		json.setRespostaCliente(respostaCliente);
		json.setMdmId(mdmId);
		aceiteService.salvarAceite(json); 
	}
	

}
