package br.com.dasa.api.termo.dao;

import java.util.List;

public interface AceiteDAO {

	boolean usuarioRespondeuAoTermo(String mdmId, String cip, List<Long> termosId); 
	
}
