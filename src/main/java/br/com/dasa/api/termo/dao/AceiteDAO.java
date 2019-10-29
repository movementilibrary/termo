package br.com.dasa.api.termo.dao;

import java.util.List;

public interface AceiteDAO {

	boolean usuarioRespondeuAoTermo(String mdmId, Integer cip, List<Long> termosId); 
	
}
